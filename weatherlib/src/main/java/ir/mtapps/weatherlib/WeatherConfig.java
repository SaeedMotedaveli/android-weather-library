package ir.mtapps.weatherlib;

import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.enums.UNIT_SYSTEM;

public class WeatherConfig {

    public static Builder edit() {
        return new Builder();
    }

    public static WeatherConfig create() {
        return new Builder().create();
    }

    private String language = "en";

    // Units
    private TEMPERATURE temperatureUnit = TEMPERATURE.C;
    private SPEED speedUnit = SPEED.Km_h;
    private AMOUNT precipitationAmountUnit = AMOUNT.mm;
    private PRESSURE pressureUnit = PRESSURE.mmHg;
    private LENGTH visibilityUnit = LENGTH.Km;

    // Cache
    private boolean useCache = false;
    private int cacheValidity = 3600000;

    // Limit hourly and daily items
    private int limitNextHours = -1;
    private int limitNextDays = -1;

    // Air quality valid radius
    private int airQualityValidRadius = 5;

    private WeatherConfig() {}

    public String getLanguage() {
        return language;
    }

    public TEMPERATURE getTemperatureUnit() {
        return temperatureUnit;
    }

    public SPEED getSpeedUnit() {
        return speedUnit;
    }

    public PRESSURE getPressureUnit() {
        return pressureUnit;
    }

    public LENGTH getVisibilityUnit() {
        return visibilityUnit;
    }

    public boolean isCacheEnable() {
        return useCache;
    }

    public int getCacheValidity() {
        return cacheValidity;
    }

    public AMOUNT getPrecipitationAmountUnit() {
        return precipitationAmountUnit;
    }

    public int getLimitNextHours() {
        return limitNextHours;
    }

    public int getLimitNextDays() {
        return limitNextDays;
    }

    public int getAirQualityValidRadius() {
        return airQualityValidRadius;
    }

    void disableCache() {
        this.useCache = false;
    }

    public final static class Builder {

        private final WeatherConfig config = new WeatherConfig();

        /**
         * Set language of weather data (Default: en [English]).
         *
         * @param language {@link String}
         */
        public Builder language(String language) {
            config.language = language;
            return this;
        }

        /**
         * This option enable caching weather data that received from providers.
         * Default cache validity time is 60 minutes (1 hour).
         */
        public Builder enableCache() {
            config.useCache = true;
            config.cacheValidity = 3600000;
            return this;
        }

        /**
         * This option enable caching weather data that received from providers.
         * You can set time that cache is valid by minutes.
         *
         * @param validityTimeInMinutes cache validity time by minutes
         */
        public Builder enableCache(int validityTimeInMinutes) {

            if (validityTimeInMinutes <= 0) {
                return disableCache();
            }

            config.useCache = true;
            config.cacheValidity = validityTimeInMinutes * 60000;
            return this;
        }

        /**
         * Disable caching weather data. Every time that weather requested, its received from providers.
         */
        public Builder disableCache() {
            config.useCache = false;
            return this;
        }

        /**
         * Set unit system to metric or imperial (Default: Metric).
         * <br/>
         * NOTE: You can also change every units like temperature unit or speed unit.
         *
         * @param unitSystem {@link UNIT_SYSTEM}.Metric or {@link UNIT_SYSTEM}.Imperial
         */
        public Builder unitSystem(UNIT_SYSTEM unitSystem) {

            if (unitSystem == UNIT_SYSTEM.METRIC) {
                config.temperatureUnit = TEMPERATURE.C;
                config.speedUnit = SPEED.Km_h;
                config.precipitationAmountUnit = AMOUNT.mm;
                config.pressureUnit = PRESSURE.mmHg;
                config.visibilityUnit = LENGTH.Km;
            } else {
                config.temperatureUnit = TEMPERATURE.F;
                config.speedUnit = SPEED.mph;
                config.precipitationAmountUnit = AMOUNT.inch;
                config.pressureUnit = PRESSURE.mmHg;
                config.visibilityUnit = LENGTH.miles;
            }

            return this;
        }

        public Builder unitOfTemperature(TEMPERATURE temperatureUnit) {
            config.temperatureUnit = temperatureUnit;
            return this;
        }

        public Builder unitOfSpeed(SPEED speedUnit) {
            config.speedUnit = speedUnit;
            return this;
        }

        public Builder unitOfPressure(PRESSURE pressureUnit) {
            config.pressureUnit = pressureUnit;
            return this;
        }

        public Builder unitOfVisibility(LENGTH visibilityUnit) {
            config.visibilityUnit = visibilityUnit;
            return this;
        }

        public Builder unitOfPrecipitationAmount(AMOUNT precipitationAmountUnit) {
            config.precipitationAmountUnit = precipitationAmountUnit;
            return this;
        }

        /**
         * This option limit hourly weather list by next hours. For example if it's set by 6 hours, only
         * weather of next 6 hours received.
         *
         * <p>NOTE 1: The size of received list maybe different for different providers.</p>
         * <p>NOTE 2: If parameters <= 0 then limiting ignored.</p>
         *
         * @param limitByHours int hours (must >= 1 or ignored)
         */
        public Builder limitHourlyWeather(int limitByHours) {
            config.limitNextHours = limitByHours;
            return this;
        }

        /**
         * This option limit daily weather list by next days. For example if it's set by 2 days, only
         * weather of next 2 days received.
         *
         * <p>NOTE 2: If parameters <= 0 then limiting ignored.</p>
         *
         * @param limitByDays int days (must >= 1 or ignored)
         */
        public Builder limitDailyWeather(int limitByDays) {
            config.limitNextDays = limitByDays;
            return this;
        }

        /**
         * Valid radius of air quality.
         * @param radius length (Kilometers)
         */
        public Builder validRadiusOfAirQuality(int radius) {
            config.airQualityValidRadius = radius;
            return this;
        }

        public WeatherConfig create() {
            return config;
        }

    }
}
