package ir.mtapps.weatherlib.provider.tomorrow;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.TimeZone;

import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public class Provider extends WeatherProvider {

    // URL
    private final static String BASE_URL = "https://api.tomorrow.io/v4/";
    private final static String URL_CURRENTLY = BASE_URL + "timelines?location={lat},{lng}" +
            "&fields=temperature,temperatureApparent,dewPoint,humidity,windSpeed,windDirection,pressureSurfaceLevel,precipitationIntensity,precipitationProbability,visibility,cloudCover,uvIndex,weatherCode" +
            "&timesteps=current&units=metric&apikey={key}";

    private final static String URL_HOURLY = BASE_URL + "timelines?location={lat},{lng}" +
            "&fields=temperature,temperatureApparent,dewPoint,humidity,windSpeed,windDirection,pressureSurfaceLevel,precipitationIntensity,precipitationProbability,visibility,cloudCover,uvIndex,weatherCode" +
            "&timesteps=1h&units=metric&apikey={key}";

    private final static String URL_DAILY = BASE_URL + "timelines?location={lat},{lng}" +
            "&fields=temperature,temperatureApparent,dewPoint,humidity,windSpeed,windDirection,pressureSurfaceLevel,precipitationIntensity,precipitationProbability,visibility,cloudCover,uvIndex,weatherCode,moonPhase,sunriseTime,sunsetTime" +
            "&timesteps=1d&units=metric&apikey={key}";

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

        CurrentWeatherModel currentlyModel = gson.fromJson(currently, CurrentWeatherModel.class);

        DailyWeatherModel dailyModel = gson.fromJson(daily, DailyWeatherModel.class);

        HourlyWeatherModel hourlyModel = gson.fromJson(hourly, HourlyWeatherModel.class);

        listener.onSuccessful(currentlyModel.getCity(getParams().latitude, getParams().longitude),
                currentlyModel.createModel(context, getParams().config),
                dailyModel.createTodayAstronomyModel(),
                hourlyModel.createHourlyModel(context, getParams().config, getParams().latitude, getParams().longitude),
                dailyModel.createModel(context, getParams().config));

    }


    @Override
    public void currentCondition(@NonNull Context context,
                                 @NonNull String json,
                                 String geo,
                                 @NonNull CurrentWeatherListener listener) {

        // Parsing current weather json response
        CurrentWeatherModel model = gson.fromJson(json, CurrentWeatherModel.class);

        // Pass data
        listener.onSuccessful(model.getCity(getParams().latitude, getParams().longitude),
                model.createModel(context, getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull Context context,
                               @NonNull String json,
                               String geo,
                               @NonNull AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        listener.onSuccessful(model.getCity(getParams().latitude, getParams().longitude),
                model.createTodayAstronomyModel());

    }

    @Override
    public void hourlyWeather(@NonNull Context context,
                              @NonNull String json,
                              String geo,
                              @NonNull HourlyWeatherListener listener) {

        HourlyWeatherModel model = gson.fromJson(json, HourlyWeatherModel.class);

        listener.onSuccessful(model.getCity(getParams().latitude, getParams().longitude),
                model.createHourlyModel(context, getParams().config, getParams().latitude, getParams().longitude));

    }

    @Override
    public void dailyWeather(@NonNull Context context,
                             @NonNull String json,
                             String geo,
                             @NonNull DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(getParams().latitude, getParams().longitude),
                model.createModel(context, getParams().config));

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
        }

        return url + "&timezone=" + TimeZone.getDefault().getID();

    }

}