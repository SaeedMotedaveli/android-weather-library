package ir.mtapps.weatherlib.provider.accuweather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.HourlyWeather;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public final class Provider extends WeatherProvider {

    // URLs
    private final static String BASE_URL = "https://dataservice.accuweather.com/";
    private final static String URL_GEO = BASE_URL + "locations/v1/cities/geoposition/search?apikey={key}&q={lat},{lng}&language={lang}";
    private final static String URL_CURRENTLY = BASE_URL + "currentconditions/v1/{city}?apikey={key}&language={lang}&details=true";
    private final static String URL_DAILY = BASE_URL + "forecasts/v1/daily/5day/{city}?apikey={key}&language={lang}&details=true&metric=true";
    private final static String URL_HOURLY = BASE_URL + "forecasts/v1/hourly/12hour/{city}?apikey={key}&language={lang}&details=true&metric=true";

    private final Gson gson = new Gson();

    @Override
    public void allWeather(@NonNull String json, String geo, AllWeatherListener listener) {}

    @Override
    public void allWeather(@NonNull String currently,
                           @NonNull String hourly,
                           @NonNull String daily,
                           String geo,
                           AllWeatherListener listener) {

        // Current Weather
        Type currentlyListType = new TypeToken<List<CurrentWeatherModel>>() {}.getType();
        List<CurrentWeatherModel> currentlyModelList = gson.fromJson(currently, currentlyListType);

        // Check for not null model
        if (currentlyModelList == null || currentlyModelList.isEmpty()) {
            listener.onError(Error.RESPONSE_IS_NULL, getResources().getString(R.string.error_response_is_null));
            return;
        }

        // Hourly Weather
        Type hourlyListType = new TypeToken<List<HourlyWeatherModel>>() {}.getType();
        List<HourlyWeatherModel> hourlyModelList = gson.fromJson(hourly, hourlyListType);

        // Check for not null model
        if (hourlyModelList == null || hourlyModelList.isEmpty()) {
            listener.onError(Error.RESPONSE_IS_NULL, getResources().getString(R.string.error_response_is_null));
            return;
        }

        List<HourlyWeather> hourlyWeatherList = new ArrayList<>();

        int limitNextHours = getParams().config.getLimitNextHours();

        if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        int count = 1;
        for (HourlyWeatherModel hourlyItem : hourlyModelList) {

            if (limitNextHours > 0 && count > limitNextHours) {
                break;
            }

            hourlyWeatherList.add(hourlyItem.createModel(getContext(), getParams().config));

            count += 1;
        }

        // Daily and Astronomy
        DailyWeatherModel dailyModel = gson.fromJson(daily, DailyWeatherModel.class);

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(),
                currentlyModelList.get(0).createModel(getContext(), getParams().config),
                dailyModel.createAstronomyModel(),
                hourlyWeatherList,
                dailyModel.createModel(getContext(), getParams().config));
    }


    @Override
    public void currentCondition(@NonNull String json, String geo, CurrentWeatherListener listener) {

        // Current Weather
        Type listType = new TypeToken<List<CurrentWeatherModel>>() {}.getType();
        List<CurrentWeatherModel> modelList = gson.fromJson(json, listType);

        // Check for not null model
        if (modelList == null || modelList.isEmpty()) {
            listener.onError(Error.RESPONSE_IS_NULL, getResources().getString(R.string.error_response_is_null));
            return;
        }

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(),
                modelList.get(0).createModel(getContext(), getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull String json, String geo, AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        listener.onSuccessful(geoPositionModel.getCity(), model.createModel());
    }

    @Override
    public void hourlyWeather(@NonNull String json, String geo, HourlyWeatherListener listener) {

        // Parsing Hourly weather json response
        Type listType = new TypeToken<List<HourlyWeatherModel>>() {}.getType();
        List<HourlyWeatherModel> modelList = gson.fromJson(json, listType);

        // Check for not null model
        if (modelList == null || modelList.isEmpty()) {
            listener.onError(Error.RESPONSE_IS_NULL, getResources().getString(R.string.error_response_is_null));
            return;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int limitNextHours = getParams().config.getLimitNextHours();

        if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        int count = 1;
        for (HourlyWeatherModel hourly : modelList) {

            if (limitNextHours > 0 && count > limitNextHours) {
                break;
            }

            weatherList.add(hourly.createModel(getContext(), getParams().config));

            count += 1;
        }

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(), weatherList);

    }

    @Override
    public void dailyWeather(@NonNull String json, String geo, DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        listener.onSuccessful(geoPositionModel.getCity(), model.createModel(getContext(), getParams().config));

    }

    @Override
    public String getUrl(URL urlType) {

        if (urlType == URL.GEO) {

            String url = URL_GEO;
            url = url.replace("{key}", getParams().apiKey);
            url = url.replace("{lat}", Double.toString(getParams().latitude));
            url = url.replace("{lng}", Double.toString(getParams().longitude));
            url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));

            return url;

        }

        return null;
    }

    @Override
    public String getUrl(URL urlType, @NonNull String geoResponse) {

        String city;

        GeoPositionModel model = gson.fromJson(geoResponse, GeoPositionModel.class);

        if (model == null) {
            return null;
        }

        city = model.key;

        if (city == null || city.isEmpty()) {
            return null;
        }

        String url = null;

        switch (urlType) {

            case GEO:
                url = URL_GEO;
                url = url.replace("{key}", getParams().apiKey);
                url = url.replace("{lat}", Double.toString(getParams().latitude));
                url = url.replace("{lng}", Double.toString(getParams().longitude));
                url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));
                break;

            case CURRENTLY:
                url = URL_CURRENTLY;
                url = url.replace("{key}", getParams().apiKey);
                url = url.replace("{city}", city);
                url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));
                break;

            case HOURLY:
                url = URL_HOURLY;
                url = url.replace("{key}", getParams().apiKey);
                url = url.replace("{city}", city);
                url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));
                break;

            case ASTRONOMY:
            case DAILY:
                url = URL_DAILY;
                url = url.replace("{key}", getParams().apiKey);
                url = url.replace("{city}", city);
                url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));
                break;

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

    @Override
    public boolean isGeoRequestRequired() {
        return true;
    }

}
