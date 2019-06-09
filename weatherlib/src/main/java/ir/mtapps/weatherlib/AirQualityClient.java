package ir.mtapps.weatherlib;

import android.content.Context;

import androidx.annotation.NonNull;

import ir.mtapps.weatherlib.enums.AIR_QUALITY_PROVIDER;
import ir.mtapps.weatherlib.interfaces.AirQualityListener;
import ir.mtapps.weatherlib.provider.AirQualityProvider;

import static ir.mtapps.weatherlib.Loging.log_e;
import static ir.mtapps.weatherlib.Loging.log_w;

public abstract class AirQualityClient {

    public abstract void airQuality(@NonNull AirQualityListener listener);

    // ********************************************************************************************
    // *                                Builder
    // ********************************************************************************************

    public static final class Builder {

        private Context mContext;
        private AIR_QUALITY_PROVIDER mProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
        private boolean mAutoCoordinate = true;
        private double mLatitude;
        private double mLongitude;
        private String mApiKey;
        private WeatherConfig mConfig = null;

        public Builder attach(Context context) {
            this.mContext = context;
            return this;
        }

        /**
         * Choose provider (Default: Open Weather).
         */
        public Builder provider(@NonNull AIR_QUALITY_PROVIDER provider) {
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
         * Set API Key. <b style="color: red;">Required</b>
         */
        public Builder apiKey(@NonNull String apiKey) {
            this.mApiKey = apiKey;
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
        public AirQualityClient build() {

            if (mContext == null) {
                log_e("Context is null. Please attach Context.");
            }

            if (mApiKey == null) {
                log_e("API Key is null. Weather can't receive from provider.");
            }

            if (mConfig == null) {
                mConfig = WeatherConfig.create();
            }

            if (mAutoCoordinate) {
                mConfig.disableCache();
                log_w("In 'Auto Coordinate' mode, caching data turn off for saving resources.");
            }

            AirQualityProvider.Params params = new AirQualityProvider.Params();
            params.apiKey = mApiKey;
            params.latitude = mLatitude;
            params.longitude = mLongitude;
            params.config = mConfig;

            return AirQualityHttpClient.getInstance(mContext, mProvider, mAutoCoordinate, params);
        }

    }
}
