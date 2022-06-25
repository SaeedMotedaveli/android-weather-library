package ir.mtapps.weatherlib.provider.visualcrossing;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public class Provider extends WeatherProvider {

    private final static String URL_ALL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/" +
            "timeline/{lat},{lng}/?unitGroup=metric&lang={lang}&options=nonulls&key={key}";

    private final Gson gson = new Gson();

    @Override
    public void allWeather(@NonNull Context context,
                           @NonNull String json,
                           String geo,
                           @NonNull AllWeatherListener listener) {

        AllWeatherModel model = gson.fromJson(json, AllWeatherModel.class);

        listener.onSuccessful(model.getCity(),
                model.createCurrentWeatherModel(context, getParams().config),
                model.createTodayAstronomyModel(),
                model.createHourlyModel(context, getParams().config),
                model.createDailyModel(context, getParams().config));

    }

    @Override
    public void allWeather(@NonNull Context context,
                           @NonNull String currently,
                           @NonNull String hourly,
                           @NonNull String daily,
                           String geo,
                           @NonNull AllWeatherListener listener) {}


    @Override
    public void currentCondition(@NonNull Context context,
                                 @NonNull String json,
                                 String geo,
                                 @NonNull CurrentWeatherListener listener) {

        CurrentWeatherModel model = gson.fromJson(json, CurrentWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createCurrentWeatherModel(context, getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull Context context,
                               @NonNull String json,
                               String geo,
                               @NonNull AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        listener.onSuccessful(model.getCity(), model.createTodayAstronomyModel());

    }

    @Override
    public void hourlyWeather(@NonNull Context context,
                              @NonNull String json,
                              String geo,
                              @NonNull HourlyWeatherListener listener) {

        HourlyWeatherModel model = gson.fromJson(json, HourlyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createHourlyModel(context, getParams().config));
    }

    @Override
    public void dailyWeather(@NonNull Context context,
                             @NonNull String json,
                             String geo,
                             @NonNull DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createDailyModel(context, getParams().config));

    }

    @Override
    public String getUrl(WeatherProvider.URL urlType) {

        String url = URL_ALL;
        url = url.replace("{key}", getParams().apiKey);
        url = url.replace("{lat}", Double.toString(getParams().latitude));
        url = url.replace("{lng}", Double.toString(getParams().longitude));
        url = url.replace("{lang}", Util.getLanguageForUrl(getParams().config.getLanguage()));

        return url;
    }

    @Override
    public boolean isSupportAllInOneUrl() {
        return true;
    }
}
