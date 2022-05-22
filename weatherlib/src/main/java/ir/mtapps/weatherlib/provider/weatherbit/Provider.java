package ir.mtapps.weatherlib.provider.weatherbit;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public final class Provider extends WeatherProvider {

    // URL
    private final static String BASE_URL = "https://api.weatherbit.io/v2.0/";
    private final static String URL_CURRENTLY = BASE_URL + "current?key={key}&lang={lang}&lat={lat}&lon={lng}";
    private final static String URL_HOURLY = BASE_URL + "forecast/hourly?key={key}&lang={lang}&lat={lat}&lon={lng}";
    private final static String URL_DAILY = BASE_URL + "forecast/daily?key={key}&lang={lang}&lat={lat}&lon={lng}";

    private final Gson gson = new Gson();

    @Override
    public void allWeather(@NonNull String json, String geo, AllWeatherListener listener) {}

    @Override
    public void allWeather(@NonNull String currently,
                           @NonNull String hourly,
                           @NonNull String daily,
                           String geo,
                           AllWeatherListener listener) {

        CurrentWeatherModel currentlyModel = gson.fromJson(currently, CurrentWeatherModel.class);

        DailyWeatherModel dailyModel = gson.fromJson(daily, DailyWeatherModel.class);

        HourlyWeatherModel hourlyModel = gson.fromJson(hourly, HourlyWeatherModel.class);

        listener.onSuccessful(currentlyModel.getCity(),
                currentlyModel.createModel(getContext(), getParams().config),
                dailyModel.createAstronomyModel(),
                hourlyModel.createModel(getContext(), getParams().config),
                dailyModel.createModel(getContext(), getParams().config));

    }


    @Override
    public void currentCondition(@NonNull String json, String geo, CurrentWeatherListener listener) {

        // Parsing current weather json response
        CurrentWeatherModel model = gson.fromJson(json, CurrentWeatherModel.class);

        // Pass data
        listener.onSuccessful(model.getCity(), model.createModel(getContext(), getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull String json, String geo, AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        listener.onSuccessful(model.getCity(), model.createModel());

    }

    @Override
    public void hourlyWeather(@NonNull String json, String geo, HourlyWeatherListener listener) {

        HourlyWeatherModel model = gson.fromJson(json, HourlyWeatherModel.class);

        if (model.error == null) {
            listener.onSuccessful(model.getCity(), model.createModel(getContext(), getParams().config));
        } else {
            listener.onError(Error.PREMIUM_API_KEY_REQUIRE, model.error);
        }

    }

    @Override
    public void dailyWeather(@NonNull String json, String geo, DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createModel(getContext(), getParams().config));

    }

    @Override
    public String getUrl(URL urlType) {

        String url = null;

        switch (urlType) {

            case CURRENTLY:
                url = URL_CURRENTLY;
                break;

            case HOURLY:
                url = URL_HOURLY;
                break;

            case ASTRONOMY:
            case DAILY:
                url = URL_DAILY;
                break;

        }

        if (url != null) {
            url = url.replace("{key}", getParams().apiKey);
            url = url.replace("{lat}", Double.toString(getParams().latitude));
            url = url.replace("{lng}", Double.toString(getParams().longitude));
            url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));
        }

        return url;

    }

    @Override
    public String getJsonFromCache(Cache cache, URL urlType) {

        if (cache == null) {
            return null;
        }

        String json = null;
        long cacheUpdatedTime = 0;

        switch (urlType) {

            case GEO:

                if (cache.getGeoCache() != null) {
                    json = cache.getGeoCache().getCache();
                    cacheUpdatedTime = cache.getGeoCache().getUpdatedAt();
                }

                break;

            case CURRENTLY:

                if (cache.getWeatherCache() != null) {
                    json = cache.getWeatherCache().getCache();
                    cacheUpdatedTime = cache.getWeatherCache().getUpdatedAt();
                }

                break;

            case HOURLY:

                if (cache.getHourlyWeatherCache() != null) {
                    json = cache.getHourlyWeatherCache().getCache();
                    cacheUpdatedTime = cache.getHourlyWeatherCache().getUpdatedAt();
                }

                break;

            case ASTRONOMY:
            case DAILY:

                if (cache.getDailyWeatherCache() != null) {
                    json = cache.getDailyWeatherCache().getCache();
                    cacheUpdatedTime = cache.getDailyWeatherCache().getUpdatedAt();
                }

                break;

        }

        if (json != null && !json.isEmpty()) {

            long current = System.currentTimeMillis();
            long diffs = current - cacheUpdatedTime;

            if (diffs <= getParams().config.getCacheValidity()) {

                return json;

            }

        }

        return null;

    }

    @NonNull
    @Override
    public Cache updateCache(@NonNull Cache cache, @NonNull URL urlType, @NonNull String json) {

        switch (urlType) {

            case GEO:
                cache.setGeoCache(json, System.currentTimeMillis());
                break;

            case CURRENTLY:
                cache.setWeatherCache(json, System.currentTimeMillis());
                break;

            case HOURLY:
                cache.setHourlyWeatherCache(json, System.currentTimeMillis());
                break;

            case ASTRONOMY:
            case DAILY:
                cache.setDailyWeatherCache(json, System.currentTimeMillis());
                break;

        }

        return cache;

    }
}
