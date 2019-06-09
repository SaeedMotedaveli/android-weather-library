package ir.mtapps.weatherlib.provider.world_weather_online;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.ResponseResult;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public final class Provider extends WeatherProvider {

    // URLs
    private final static String URL_ALL = "https://api.worldweatheronline.com/premium/v1/weather.ashx?"
            + "q={lat},{lng}&format=json&extra=isDayTime&num_of_days=10&fx24=yes"
            + "&includelocation=yes&tp=1&mca=no&lang={lang}&key={key}";

    private Gson gson = new Gson();

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
                           AllWeatherListener listener) {}


    @Override
    public void currentCondition(@NonNull String json, String geo, CurrentWeatherListener listener) {

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

        listener.onSuccessful(model.getCity(), model.createModel(getContext(), getParams().config));

    }

    @Override
    public void dailyWeather(@NonNull String json, String geo, DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createModel(getContext(), getParams().config));

    }

    @Override
    public String getUrl(URL urlType) {

        String url = URL_ALL;
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
    public ResponseResult checkForJsonValidity(@NonNull String json) {

        ErrorModel model = gson.fromJson(json, ErrorModel.class);

        // Check for not null model
        if (model == null) {
            return new ResponseResult.Builder()
                    .isSuccessful(false)
                    .setCode(Error.RESPONSE_IS_NULL)
                    .setMessage(getContext().getString(R.string.error_response_is_null))
                    .build();
        }

        // Check for successful response
        if (model.data.error != null) {
            return new ResponseResult.Builder()
                    .isSuccessful(false)
                    .setCode(Error.PROVIDER_RETURN_ERROR)
                    .setMessage(model.data.error.get(0).message)
                    .build();
        }


        return new ResponseResult.Builder().isSuccessful(true).build();
    }

    @Override
    public boolean isSupportAllInOneUrl() {
        return true;
    }

}
