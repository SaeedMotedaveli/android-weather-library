package ir.mtapps.weatherlib.provider.aeris_weather;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.ResponseResult;
import ir.mtapps.weatherlib.provider.WeatherProvider;

public class Provider extends WeatherProvider {

    // URL
    private final static String BASE_URL = "https://api.aerisapi.com/";
    private final static String URL_CURRENTLY = BASE_URL + "conditions/{lat},{lng}?client_secret={client_secret}&client_id={client_id}&filter=mdnt2mdnt2";
    private final static String URL_HOURLY = BASE_URL + "forecasts/{lat},{lng}?client_secret={client_secret}&client_id={client_id}&filter=1hr";
    private final static String URL_DAILY = BASE_URL + "forecasts/{lat},{lng}?client_secret={client_secret}&client_id={client_id}&filter=mdnt2mdnt2";

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
                dailyModel.createTodayAstronomyModel(),
                hourlyModel.createHourlyModel(context, getParams().config),
                dailyModel.createModel(context, getParams().config));

    }


    @Override
    public void currentCondition(@NonNull Context context,
                                 @NonNull String json,
                                 String geo,
                                 @NonNull  CurrentWeatherListener listener) {

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

            String[] args = getParams().apiKey.split(",");
            String clientId = args[0];
            String clientSecret = args[1];

            url = url.replace("{client_id}", clientId);
            url = url.replace("{client_secret}", clientSecret);
            url = url.replace("{lat}", Double.toString(getParams().latitude));
            url = url.replace("{lng}", Double.toString(getParams().longitude));
        }

        return url;

    }

    @Override
    public ResponseResult checkForJsonValidity(@NonNull Context context, @NonNull String json) {

        JsonValidityModel model = gson.fromJson(json, JsonValidityModel.class);

        if (model.success) {
            return new ResponseResult.Builder().isSuccessful(true).build();
        } else {
            return new ResponseResult.Builder()
                    .isSuccessful(false)
                    .setCode(Error.PROVIDER_RETURN_ERROR)
                    .setMessage(model.error)
                    .build();
        }
    }
}