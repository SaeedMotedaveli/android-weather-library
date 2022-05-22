package ir.mtapps.weatherlib.provider.open_weather;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public final class Provider extends WeatherProvider {

    // URLs
    private final static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
//    private final static String URL_CURRENTLY = BASE_URL + "weather?mode=json&lat={lat}&lon={lng}&units=metric&lang={lang}&appid={key}";
//    private final static String URL_UV = BASE_URL + "uvi?lat={lat}&lon={lng}&appid={key}";
//    private final static String URL_HOURLY = BASE_URL + "forecast?mode=json&lat={lat}&lon={lng}&units=metric&lang={lang}&appid={key}";
//    private final static String URL_DAILY = BASE_URL + "forecast/daily?mode=json&cnt=16&lat={lat}&lon={lng}&units=metric&lang={lang}&appid={key}";

    private final static String URL_ONE_CALL = BASE_URL + "onecall?lat={lat}&lon={lng}&units=metric&lang={lang}&appid={key}";

    private final Gson gson = new Gson();

    @Override
    public void allWeather(@NonNull String json, String geo, AllWeatherListener listener) {

        AllWeatherModel model = gson.fromJson(json, AllWeatherModel.class);

        listener.onSuccessful(model.getCity(),
                model.createCurrentWeatherModel(getContext(), getParams().config),
                model.createTodayAstronomyModel(),
                model.createHourlyModel(getContext(), getParams().config),
                model.createDailyModel(getContext(), getParams().config));

    }

    @Override
    public void allWeather(@NonNull String currently,
                           @NonNull String hourly,
                           @NonNull String daily,
                           String geo,
                           AllWeatherListener listener) {
    }


    @Override
    public void currentCondition(@NonNull String json, String geo, CurrentWeatherListener listener) {

        CurrentWeatherModel model = gson.fromJson(json, CurrentWeatherModel.class);

        // Pass data
        listener.onSuccessful(model.getCity(), model.createCurrentWeatherModel(getContext(), getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull String json, String geo, AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        listener.onSuccessful(model.getCity(), model.createTodayAstronomyModel());
    }

    @Override
    public void hourlyWeather(@NonNull String json, String geo, HourlyWeatherListener listener) {

        HourlyWeatherModel model = gson.fromJson(json, HourlyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createHourlyModel(getContext(), getParams().config));
    }

    @Override
    public void dailyWeather(@NonNull String json, String geo, DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createDailyModel(getContext(), getParams().config));
    }

    @Override
    public String getUrl(URL urlType) {

        String url = URL_ONE_CALL;

        url = url.replace("{key}", getParams().apiKey);
        url = url.replace("{lat}", Double.toString(getParams().latitude));
        url = url.replace("{lng}", Double.toString(getParams().longitude));
        url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));

        return url;
    }

    @Override
    public String getJsonFromCache(Cache cache, URL urlType) {

        if (cache != null && cache.getWeatherCache() != null) {

            long current = System.currentTimeMillis();
            long cacheUpdatedTime = cache.getWeatherCache().getUpdatedAt();
            long diffs = current - cacheUpdatedTime;

            if (diffs <= getParams().config.getCacheValidity()) {

                return cache.getWeatherCache().getCache();

            }

        }

        return null;
    }

    @NonNull
    @Override
    public Cache updateCache(@NonNull Cache cache, @NonNull URL urlType, @NonNull String json) {

        if (urlType == URL.GEO) {
            cache.setGeoCache(json, System.currentTimeMillis());
        } else {
            cache.setWeatherCache(json, System.currentTimeMillis());
        }

        return cache;
    }

    @Override
    public boolean isSupportAllInOneUrl() {
        return true;
    }

}
