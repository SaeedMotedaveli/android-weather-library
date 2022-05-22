package ir.mtapps.weatherlib.provider.tomorrow;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;

public class CurrentWeatherModel {

    @SerializedName("data")
    public Data data;

    public static class Data {

        @SerializedName("timelines")
        public List<Timeline> timelines = null;

    }

    public static class Interval {

//        @SerializedName("startTime")
//        public String startTime;

        @SerializedName("values")
        public Values values;

    }

    public static class Timeline {

//        @SerializedName("timestep")
//        public String timestep;

//        @SerializedName("endTime")
//        public String endTime;

//        @SerializedName("startTime")
//        public String startTime;

        @SerializedName("intervals")
        public List<Interval> intervals = null;

    }

    public static class Values {

        @SerializedName("cloudCover")
        public float cloudCover;

        @SerializedName("dewPoint")
        public float dewPoint;

        @SerializedName("humidity")
        public float humidity;

        @SerializedName("precipitationIntensity")
        public float precipitationIntensity;

        @SerializedName("precipitationProbability")
        public int precipitationProbability;

        @SerializedName("pressureSurfaceLevel")
        public float pressureSurfaceLevel;

        @SerializedName("temperature")
        public float temperature;

        @SerializedName("temperatureApparent")
        public float temperatureApparent;

        @SerializedName("uvIndex")
        public float uvIndex;

        @SerializedName("visibility")
        public float visibility;

        @SerializedName("weatherCode")
        public int weatherCode;

        @SerializedName("windDirection")
        public float windDirection;

        @SerializedName("windSpeed")
        public float windSpeed;

    }

    CurrentWeather createModel(Context context, WeatherConfig config) {

        Values currently = data.timelines.get(0).intervals.get(0).values;

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(currently.weatherCode),
                        Util.getDescription(context, config.getLanguage(), currently.weatherCode))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.temperature),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.temperatureApparent),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.dewPoint),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), currently.pressureSurfaceLevel),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(currently.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), currently.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), currently.windSpeed),
                        currently.windDirection,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(currently.cloudCover))

                .setUvIndex(currently.uvIndex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), currently.precipitationIntensity),
                        Math.round(currently.precipitationProbability),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity(double latitude, double longitude) {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
