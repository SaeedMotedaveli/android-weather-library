package ir.mtapps.weatherlib.provider.visualcrossing;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;

public class CurrentWeatherModel {

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("currentConditions")
    public CurrentConditions currentConditions;

    public static class CurrentConditions {

        @SerializedName("temp")
        public float temp;

        @SerializedName("feelslike")
        public float feelslike;

        @SerializedName("humidity")
        public float humidity;

        @SerializedName("dew")
        public float dew;

        @SerializedName("precip")
        public float precip;

        @SerializedName("precipprob")
        public float precipprob;

        @SerializedName("snow")
        public float snow;

        @SerializedName("windspeed")
        public float windspeed;

        @SerializedName("winddir")
        public float winddir;

        @SerializedName("pressure")
        public float pressure;

        @SerializedName("visibility")
        public float visibility;

        @SerializedName("cloudcover")
        public float cloudcover;

        @SerializedName("uvindex")
        public float uvindex;

        @SerializedName("conditions")
        public String conditions;

        @SerializedName("icon")
        public String icon;

        @SerializedName("sunriseEpoch")
        public long sunriseEpoch;

        @SerializedName("sunsetEpoch")
        public long sunsetEpoch;

        @SerializedName("moonphase")
        public float moonphase;

    }

    // --------------------------------------------------------------------------------------------

    CurrentWeather createCurrentWeatherModel(Context context, WeatherConfig config) {

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(currentConditions.icon),
                        Util.getDescription(context, config.getLanguage(), currentConditions.conditions))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.temp),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.feelslike),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.dew),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), currentConditions.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(currentConditions.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), currentConditions.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), currentConditions.windspeed),
                        currentConditions.winddir,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(currentConditions.cloudcover))

                .setUvIndex(currentConditions.uvindex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(),
                                Math.max(currentConditions.precip, currentConditions.snow)),
                        Math.round(currentConditions.precipprob),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
