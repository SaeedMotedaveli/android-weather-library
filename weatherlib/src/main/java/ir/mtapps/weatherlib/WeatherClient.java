package ir.mtapps.weatherlib;

import android.content.Context;

import androidx.annotation.NonNull;

import ir.mtapps.weatherlib.enums.PROVIDER;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.provider.WeatherProvider;

import static ir.mtapps.weatherlib.Loging.*;

public abstract class WeatherClient {

    public abstract void allWeather(@NonNull Context context, @NonNull AllWeatherListener listener);

    public abstract void currentCondition(@NonNull Context context, @NonNull CurrentWeatherListener listener);

    public abstract void todayAstronomy(@NonNull Context context, @NonNull AstronomyListener listener);

    public abstract void hourlyWeather(@NonNull Context context, @NonNull HourlyWeatherListener listener);

    public abstract void dailyWeather(@NonNull Context context, @NonNull DailyWeatherListener listener);

    // ********************************************************************************************
    // *                                Builder
    // ********************************************************************************************

    public static final class Builder {

        private PROVIDER mProvider = PROVIDER.OPEN_WEATHER;
        private boolean mAutoCoordinate = true;
        private double mLatitude;
        private double mLongitude;
        private String mApiKey;
        private WeatherConfig mConfig = null;

        /**
         * Choose provider (Default: Open Weather).
         */
        public Builder provider(@NonNull PROVIDER provider) {
            this.mProvider = provider;
            return this;
        }

        /**
         * Set latitude and longitude. If this values not initialize, used auto coordinate if possible.
         * <br/>
         * <b>Note: </b> If lat and lng not initialize and access to auto coordinate denied, it's
         * throw exception.
         */
        public Builder coordinate(double latitude, double longitude) {
            this.mAutoCoordinate = false;
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            return this;
        }

        /**
         * Set API Key for all provider except AerisWeather. <b style="color: red;">Required</b>
         */
        public Builder apiKey(@NonNull String apiKey) {
            this.mApiKey = apiKey;
            return this;
        }

        /**
         * Set client id and client secret Key just for AerisWeather. <b style="color: red;">Required</b>
         */
        public Builder apiKey(@NonNull String clientId, @NonNull String clientSecret) {
            this.mApiKey = clientId + "," + clientSecret;
            return this;
        }

        /**
         * Set {@link WeatherConfig}. If it's not initialize, used default value;
         */
        public Builder config(@NonNull WeatherConfig config) {
            this.mConfig = config;
            return this;
        }

        /**
         * This class must be used to obtain a valid instance of WeatherClient. It accepts several config params
         * and at the end you should call build() to create the client.
         * <br/>
         * Ex:
         * <br/>
         * <code>WeatherClient.Builder builder = new WeatherClient.Builder()
         * <br/>.attach(this)
         * <br/>.provider(PROVIDER.WEATHER_UNDERGROUND)
         * <br/>.apiKey(key)
         * <br/>.config(config)
         * <br/>.build();</code>
         */
        public WeatherClient build() {

            if (mApiKey == null) {
                log_e("API Key is null. Weather can't receive from provider.");
            }

            if (mConfig == null) {
                mConfig = WeatherConfig.create();
            }

            WeatherProvider.Params params = new WeatherProvider.Params();
            params.apiKey = mApiKey;
            params.latitude = mLatitude;
            params.longitude = mLongitude;
            params.config = mConfig;

            return HttpClient.getInstance(mProvider, mAutoCoordinate, params);
        }

    }
}
