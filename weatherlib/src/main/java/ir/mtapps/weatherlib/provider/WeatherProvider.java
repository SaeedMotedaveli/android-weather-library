package ir.mtapps.weatherlib.provider;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.Util;
import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.database.Cache;
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

    private Context mContext;
    private Params mParams;

    public final void setContext(Context context) {
        this.mContext = context;
    }

    public final void setParams(@NonNull Params params) {
        this.mParams = params;
    }

    protected final Context getContext() {
        return mContext;
    }

    protected final Resources getResources() {
        return Util.getLocalizedResources(mContext, new Locale(mParams.config.getLanguage()));
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

    public abstract void allWeather(@NonNull String json,
                                    String geo,
                                    AllWeatherListener listener);

    public abstract void allWeather(@NonNull String currently,
                                    @NonNull String hourly,
                                    @NonNull String daily,
                                    String geo,
                                    AllWeatherListener listener);

    public abstract void currentCondition(@NonNull String json, String geo, CurrentWeatherListener listener);

    public abstract void todayAstronomy(@NonNull String json, String geo, AstronomyListener listener);

    public abstract void hourlyWeather(@NonNull String json, String geo, HourlyWeatherListener listener);

    public abstract void dailyWeather(@NonNull String json, String geo, DailyWeatherListener listener);


    public abstract String getUrl(URL urlType);

    public String getUrl(URL urlType, @NonNull String geoResponse) {
        return null;
    }

    public boolean isGeoRequestRequired() {
        return false;
    }

    public abstract String getJsonFromCache(Cache cache, URL urlType);

    @NonNull
    public abstract Cache updateCache(@NonNull Cache cache, @NonNull URL urlType, @NonNull String json);

    public ResponseResult checkForJsonValidity(@NonNull String json) {
        return new ResponseResult.Builder().isSuccessful(true).build();
    }

    public boolean isSupportAllInOneUrl() {
        return false;
    }
}
