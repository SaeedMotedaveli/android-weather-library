package ir.mtapps.weatherlib.provider;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.Util;
import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.ResponseResult;

public abstract class WeatherProvider {

    public static class Params {

        public String apiKey;
        public double latitude;
        public double longitude;
        public WeatherConfig config;

    }

    public enum URL {
        GEO, CURRENTLY, DAILY, HOURLY, ASTRONOMY, ALL
    }

    private Params mParams;

    public final void setParams(@NonNull Params params) {
        this.mParams = params;
    }

    protected final Resources getResources(Context context) {
        return Util.getLocalizedResources(context, new Locale(mParams.config.getLanguage()));
    }

    protected final Params getParams() {
        return mParams;
    }

    public final void setCoordinate(double latitude, double longitude) {
        mParams.latitude = latitude;
        mParams.longitude = longitude;
    }


    // ********************************************************************************************
    // *                                Getter Method
    // ********************************************************************************************

    public abstract void allWeather(@NonNull Context context,
                                    @NonNull String json,
                                    String geo,
                                    @NonNull AllWeatherListener listener);

    public abstract void allWeather(@NonNull Context context,
                                    @NonNull String currently,
                                    @NonNull String hourly,
                                    @NonNull String daily,
                                    String geo,
                                    @NonNull AllWeatherListener listener);

    public abstract void currentCondition(@NonNull Context context,
                                          @NonNull String json,
                                          String geo,
                                          @NonNull CurrentWeatherListener listener);

    public abstract void todayAstronomy(@NonNull Context context,
                                        @NonNull String json,
                                        String geo,
                                        @NonNull AstronomyListener listener);

    public abstract void hourlyWeather(@NonNull Context context,
                                       @NonNull String json,
                                       String geo,
                                       @NonNull HourlyWeatherListener listener);

    public abstract void dailyWeather(@NonNull Context context,
                                      @NonNull String json,
                                      String geo,
                                      @NonNull DailyWeatherListener listener);


    public abstract String getUrl(URL urlType);

    public String getUrl(URL urlType, @NonNull String geoResponse) {
        return null;
    }

    public boolean isGeoRequestRequired() {
        return false;
    }

    public ResponseResult checkForJsonValidity(@NonNull Context context, @NonNull String json) {
        return new ResponseResult.Builder().isSuccessful(true).build();
    }

    public boolean isSupportAllInOneUrl() {
        return false;
    }
}
