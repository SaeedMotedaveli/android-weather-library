package ir.mtapps.weatherlib.provider.weatherbit;

import android.content.Context;

import com.google.gson.Gson;

import androidx.annotation.NonNull;

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

        listener.onSuccessful(currentlyModel.getCity(),
                currentlyModel.createModel(context, getParams().config),
                dailyModel.createAstronomyModel(),
                hourlyModel.createModel(context, getParams().config),
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
        listener.onSuccessful(model.getCity(), model.createModel(context, getParams().config));

    }

    @Override
    public void todayAstronomy(@NonNull Context context,
                               @NonNull String json,
                               String geo,
                               @NonNull AstronomyListener listener) {

        AstronomyModel model = gson.fromJson(json, AstronomyModel.class);

        listener.onSuccessful(model.getCity(), model.createModel());

    }

    @Override
    public void hourlyWeather(@NonNull Context context,
                              @NonNull String json,
                              String geo,
                              @NonNull HourlyWeatherListener listener) {

        HourlyWeatherModel model = gson.fromJson(json, HourlyWeatherModel.class);

        if (model.error == null) {
            listener.onSuccessful(model.getCity(), model.createModel(context, getParams().config));
        } else {
            listener.onFailure(Error.PREMIUM_API_KEY_REQUIRE, model.error);
        }

    }

    @Override
    public void dailyWeather(@NonNull Context context,
                             @NonNull String json,
                             String geo,
                             @NonNull DailyWeatherListener listener) {

        DailyWeatherModel model = gson.fromJson(json, DailyWeatherModel.class);

        listener.onSuccessful(model.getCity(), model.createModel(context, getParams().config));

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
}
