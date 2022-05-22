package ir.mtapps.weatherlib.provider.aeris_weather;

import android.content.Context;

import java.util.List;

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

    @SerializedName("success")
    public boolean success;

    @SerializedName("response")
    public List<ResponseItem> response;

    @SerializedName("error")
    public String error;

    public static class Loc {

        @SerializedName("lat")
        public double latitude;

        @SerializedName("long")
        public double longitude;
    }

    public static class Place {

        @SerializedName("country")
        public String country;

        @SerializedName("name")
        public String name;

        @SerializedName("state")
        public String state;
    }

    public static class PeriodsItem {

//        @SerializedName("dateTimeISO")
//        public long dateTimeISO;

        @SerializedName("weatherPrimaryCoded")
        public String weatherPrimaryCoded;

        @SerializedName("feelslikeC")
        public float feelslikeC;

//        @SerializedName("visibilityMI")
//        public double visibilityMI;

        @SerializedName("icon")
        public String icon;

//        @SerializedName("solrad")
//        public Solrad solrad;

        @SerializedName("dewpointC")
        public float dewpointC;

        @SerializedName("windDirDEG")
        public float windDirDEG;

//        @SerializedName("cloudsCoded")
//        public String cloudsCoded;

//        @SerializedName("altimeterMB")
//        public double altimeterMB;

//        @SerializedName("windDir")
//        public String windDir;

//        @SerializedName("dewpointF")
//        public float dewpointF;

        @SerializedName("pop")
        public float pop;

        @SerializedName("snowCM")
        public float snowCM;

//        @SerializedName("snowIN")
//        public float snowIN;

        @SerializedName("weather")
        public String weather;

        @SerializedName("humidity")
        public float humidity;

//        @SerializedName("pressureMB")
//        public float pressureMB;

        @SerializedName("visibilityKM")
        public float visibilityKM;

//        @SerializedName("timestamp")
//        public int timestamp;

//        @SerializedName("tempF")
//        public float tempF;

        @SerializedName("precipMM")
        public float precipMM;

//        @SerializedName("snowRateIN")
//        public float snowRateIN;

        @SerializedName("sky")
        public float sky;

//        @SerializedName("spressureIN")
//        public float spressureIN;

//        @SerializedName("weatherPrimary")
//        public String weatherPrimary;

        @SerializedName("weatherCoded")
        public String weatherCoded;

//        @SerializedName("windGustKTS")
//        public float windGustKTS;

//        @SerializedName("snowRateCM")
//        public float snowRateCM;

        @SerializedName("uvi")
        public float uvi;

        @SerializedName("pressureIN")
        public double pressureIN;

//        @SerializedName("precipIN")
//        public float precipIN;

        @SerializedName("isDay")
        public boolean isDay;

        @SerializedName("spressureMB")
        public float spressureMB;

//        @SerializedName("windSpeedKTS")
//        public float windSpeedKTS;

//        @SerializedName("precipRateMM")
//        public float precipRateMM;

//        @SerializedName("solradWM2")
//        public int solradWM2;

//        @SerializedName("feelslikeF")
//        public float feelslikeF;

        @SerializedName("windSpeedKPH")
        public float windSpeedKPH;

//        @SerializedName("windGustMPH")
//        public float windGustMPH;

//        @SerializedName("altimeterIN")
//        public double altimeterIN;

//        @SerializedName("windSpeedMPH")
//        public float windSpeedMPH;

//        @SerializedName("windGustKPH")
//        public double windGustKPH;

//        @SerializedName("precipRateIN")
//        public int precipRateIN;

        @SerializedName("tempC")
        public float tempC;
    }

//    public static class Profile {
//
//        @SerializedName("elevM")
//        public int elevM;
//
//        @SerializedName("tz")
//        public String tz;
//
//        @SerializedName("isDST")
//        public boolean isDST;
//
//        @SerializedName("tzname")
//        public String tzname;
//
//        @SerializedName("elevFT")
//        public int elevFT;
//
//        @SerializedName("tzoffset")
//        public int tzoffset;
//    }

    public static class ResponseItem {

        @SerializedName("loc")
        public Loc loc;

//        @SerializedName("profile")
//        public Profile profile;

        @SerializedName("periods")
        public List<PeriodsItem> periods;

        @SerializedName("place")
        public Place place;
    }

//    public static class Solrad {
//
//        @SerializedName("azimuthDEG")
//        public double azimuthDEG;
//
//        @SerializedName("dhiWM2")
//        public double dhiWM2;
//
//        @SerializedName("ghiWM2")
//        public double ghiWM2;
//
//        @SerializedName("zenithDEG")
//        public double zenithDEG;
//
//        @SerializedName("dniWM2")
//        public double dniWM2;
//    }

    CurrentWeather createModel(Context context, WeatherConfig config) {

        PeriodsItem currently = response.get(0).periods.get(0);

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(currently.weatherCoded, currently.sky, currently.isDay),
                        Util.getDescription(context, config.getLanguage(), currently.weather, currently.weatherCoded))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.tempC),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.feelslikeC),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.dewpointC),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), currently.spressureMB),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(currently.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), currently.visibilityKM),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), currently.windSpeedKPH),
                        currently.windDirDEG,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(currently.sky))

                .setUvIndex(currently.uvi)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), Math.max(currently.snowCM * 10, currently.precipMM)),
                        Math.round(currently.pop),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(response.get(0).place.name)
                .setCountry(response.get(0).place.country)
                .setCoordinate(response.get(0).loc.latitude, response.get(0).loc.longitude)
                .build();

    }
}