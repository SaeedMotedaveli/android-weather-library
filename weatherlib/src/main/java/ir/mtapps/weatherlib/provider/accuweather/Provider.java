package ir.mtapps.weatherlib.provider.accuweather;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.R;
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
    public void allWeather(@NonNull Context context, @NonNull String json, String geo, @NonNull AllWeatherListener listener) {}

    @Override
    public void allWeather(@NonNull Context context,
                           @NonNull String currently,
                           @NonNull String hourly,
                           @NonNull String daily,
                           String geo,
                           @NonNull AllWeatherListener listener) {

        // Current Weather
        Type currentlyListType = new TypeToken<List<CurrentWeatherModel>>() {}.getType();
        List<CurrentWeatherModel> currentlyModelList = gson.fromJson(currently, currentlyListType);

        // Check for not null model
        if (currentlyModelList == null || currentlyModelList.isEmpty()) {
            listener.onFailure(Error.RESPONSE_IS_NULL, getResources(context).getString(R.string.error_response_is_null));
            return;
        }

        // Hourly Weather
        Type hourlyListType = new TypeToken<List<HourlyWeatherModel>>() {}.getType();
        List<HourlyWeatherModel> hourlyModelList = gson.fromJson(hourly, hourlyListType);

        // Check for not null model
        if (hourlyModelList == null || hourlyModelList.isEmpty()) {
            listener.onFailure(Error.RESPONSE_IS_NULL, getResources(context).getString(R.string.error_response_is_null));
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

            hourlyWeatherList.add(hourlyItem.createModel(context, getParams().config));

            count += 1;
        }

        // Daily and Astronomy
        DailyWeatherModel dailyModel = gson.fromJson(daily, DailyWeatherModel.class);

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(),
                currentlyModelList.get(0).createModel(context, getParams().config),
                dailyModel.createAstronomyModel(),
                hourlyWeatherList,
                dailyModel.createModel(context, getParams().config));
    }


    @Override
    public void currentCondition(@NonNull Context context,
                                 @NonNull String json,
                                 String geo,
                                 @NonNull CurrentWeatherListener listener) {

        // Current Weather
        Type listType = new TypeToken<List<CurrentWeatherModel>>() {}.getType();
        List<CurrentWeatherModel> modelList = gson.fromJson(json, listType);

        // Check for not null model
        if (modelList == null || modelList.isEmpty()) {
            listener.onFailure(Error.RESPONSE_IS_NULL, getResources(context).getString(R.string.error_response_is_null));
            return;
        }

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(),
                modelList.get(0).createModel(context, getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull Context context,
                               @NonNull String json,
                               String geo,
                               @NonNull AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        listener.onSuccessful(geoPositionModel.getCity(), model.createModel());
    }

    @Override
    public void hourlyWeather(@NonNull Context context,
                              @NonNull String json,
                              String geo,
                              @NonNull HourlyWeatherListener listener) {

        // Parsing Hourly weather json response
        Type listType = new TypeToken<List<HourlyWeatherModel>>() {}.getType();
        List<HourlyWeatherModel> modelList = gson.fromJson(json, listType);

        // Check for not null model
        if (modelList == null || modelList.isEmpty()) {
            listener.onFailure(Error.RESPONSE_IS_NULL, getResources(context).getString(R.string.error_response_is_null));
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

            weatherList.add(hourly.createModel(context, getParams().config));

            count += 1;
        }

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        // Pass data
        listener.onSuccessful(geoPositionModel.getCity(), weatherList);

    }

    @Override
    public void dailyWeather(@NonNull Context context,
                             @NonNull String json,
                             String geo,
                             @NonNull DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        // Geo Location
        GeoPositionModel geoPositionModel = gson.fromJson(geo, GeoPositionModel.class);

        listener.onSuccessful(geoPositionModel.getCity(), model.createModel(context, getParams().config));

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
    public boolean isGeoRequestRequired() {
        return true;
    }

}
