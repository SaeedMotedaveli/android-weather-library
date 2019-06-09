package ir.mtapps.weatherlib.provider;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.Locale;

import ir.mtapps.weatherlib.Util;
import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.interfaces.AirQualityListener;

public abstract class AirQualityProvider {

    public static class Params {

        public String apiKey;
        public double latitude;
        public double longitude;
        public WeatherConfig config;

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

    public abstract void airQuality(String json, AirQualityListener listener);
    public abstract String getAirQualityUrl();

    public final boolean isDistanceValid(double lat1, double lon1, double lat2, double lon2) {
        return Util.getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) < getParams().config.getAirQualityValidRadius();
    }

}
