package ir.mtapps.weatherlib.provider.aeris_weather;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class HourlyWeatherModel {

    @SerializedName("success")
    public boolean success;

    @SerializedName("response")
    public List<ResponseItem> response;

    @SerializedName("error")
    public Object error;

    public static class ResponseItem {

        @SerializedName("loc")
        public Loc loc;

//        @SerializedName("profile")
//        public Profile profile;

        @SerializedName("periods")
        public List<PeriodsItem> periods;

        @SerializedName("interval")
        public String interval;
    }

    public static class Loc {

        @SerializedName("long")
        public double longitude;

        @SerializedName("lat")
        public double latitude;
    }

    public static class PeriodsItem {

//        @SerializedName("dateTimeISO")
//        public String dateTimeISO;

//        @SerializedName("windDirMin80m")
//        public String windDirMin80m;

//        @SerializedName("windDirMin80mDEG")
//        public float windDirMin80mDEG;

        @SerializedName("feelslikeC")
        public float feelslikeC;

//        @SerializedName("visibilityMI")
//        public float visibilityMI;

//        @SerializedName("windSpeedMaxMPH")
//        public float windSpeedMaxMPH;

//        @SerializedName("solrad")
//        public Solrad solrad;

        @SerializedName("windDirDEG")
        public float windDirDEG;

//        @SerializedName("altimeterMB")
//        public float altimeterMB;

//        @SerializedName("windDir")
//        public String windDir;

//        @SerializedName("iceaccumMM")
//        public Object iceaccumMM;

//        @SerializedName("windSpeedMaxKTS")
//        public float windSpeedMaxKTS;

//        @SerializedName("iceaccumIN")
//        public Object iceaccumIN;

//        @SerializedName("minTempF")
//        public float minTempF;

//        @SerializedName("snowIN")
//        public float snowIN;

        @SerializedName("weather")
        public String weather;

//        @SerializedName("maxFeelslikeC")
//        public float maxFeelslikeC;

        @SerializedName("humidity")
        public float humidity;

//        @SerializedName("windDir80m")
//        public String windDir80m;

//        @SerializedName("maxFeelslikeF")
//        public float maxFeelslikeF;

        @SerializedName("precipMM")
        public float precipMM;

        @SerializedName("sky")
        public float sky;

//        @SerializedName("spressureIN")
//        public float spressureIN;

//        @SerializedName("windGust80mMPH")
//        public float windGust80mMPH;

//        @SerializedName("windSpeedMax80mMPH")
//        public float windSpeedMax80mMPH;

        @SerializedName("weatherPrimary")
        public String weatherPrimary;

//        @SerializedName("windGust80mKPH")
//        public float windGust80mKPH;

//        @SerializedName("avgDewpointF")
//        public float avgDewpointF;

//        @SerializedName("windSpeedMax80mKPH")
//        public float windSpeedMax80mKPH;

//        @SerializedName("windGust80mKTS")
//        public float windGust80mKTS;

//        @SerializedName("avgDewpointC")
//        public float avgDewpointC;

//        @SerializedName("precipIN")
//        public float precipIN;

//        @SerializedName("windSpeedMax80mKTS")
//        public float windSpeedMax80mKTS;

//        @SerializedName("spressureMB")
//        public float spressureMB;

//        @SerializedName("windDirMinDEG")
//        public float windDirMinDEG;

//        @SerializedName("windSpeedMaxKPH")
//        public float windSpeedMaxKPH;

//        @SerializedName("windSpeedMin80mKTS")
//        public float windSpeedMin80mKTS;

//        @SerializedName("feelslikeF")
//        public float feelslikeF;

//        @SerializedName("validTime")
//        public String validTime;

//        @SerializedName("windSpeedMin80mMPH")
//        public float windSpeedMin80mMPH;

//        @SerializedName("solradMaxWM2")
//        public float solradMaxWM2;

//        @SerializedName("avgTempC")
//        public float avgTempC;

//        @SerializedName("windSpeedMin80mKPH")
//        public float windSpeedMin80mKPH;

        @SerializedName("weatherPrimaryCoded")
        public String weatherPrimaryCoded;

//        @SerializedName("avgTempF")
//        public float avgTempF;

//        @SerializedName("windDirMin")
//        public String windDirMin;

//        @SerializedName("maxCoverage")
//        public String maxCoverage;

        @SerializedName("icon")
        public String icon;

//        @SerializedName("minFeelslikeC")
//        public float minFeelslikeC;

        @SerializedName("dewpointC")
        public float dewpointC;

//        @SerializedName("cloudsCoded")
//        public String cloudsCoded;

//        @SerializedName("minFeelslikeF")
//        public float minFeelslikeF;

//        @SerializedName("minHumidity")
//        public float minHumidity;

//        @SerializedName("dewpointF")
//        public float dewpointF;

//        @SerializedName("windSpeed80mKTS")
//        public float windSpeed80mKTS;

        @SerializedName("pop")
        public float pop;

        @SerializedName("snowCM")
        public float snowCM;

//        @SerializedName("windDirMax")
//        public String windDirMax;

//        @SerializedName("windSpeed80mMPH")
//        public float windSpeed80mMPH;

//        @SerializedName("windSpeed80mKPH")
//        public float windSpeed80mKPH;

//        @SerializedName("windDir80mDEG")
//        public float windDir80mDEG;

//        @SerializedName("maxTempC")
//        public float maxTempC;

        @SerializedName("pressureMB")
        public float pressureMB;

        @SerializedName("visibilityKM")
        public float visibilityKM;

        @SerializedName("timestamp")
        public long timestamp;

//        @SerializedName("maxTempF")
//        public float maxTempF;

//        @SerializedName("tempF")
//        public float tempF;

//        @SerializedName("minDewpointC")
//        public float minDewpointC;

//        @SerializedName("solradMinWM2")
//        public float solradMinWM2;

//        @SerializedName("windSpeedMinKTS")
//        public float windSpeedMinKTS;

//        @SerializedName("windDirMax80mDEG")
//        public float windDirMax80mDEG;

//        @SerializedName("weatherCoded")
//        public List<Object> weatherCoded;

//        @SerializedName("windGustKTS")
//        public float windGustKTS;

//        @SerializedName("windSpeedMinKPH")
//        public float windSpeedMinKPH;

//        @SerializedName("maxDewpointF")
//        public float maxDewpointF;

//        @SerializedName("windSpeedMinMPH")
//        public float windSpeedMinMPH;

//        @SerializedName("avgFeelslikeC")
//        public float avgFeelslikeC;

        @SerializedName("uvi")
        public float uvi;

//        @SerializedName("windDirMax80m")
//        public String windDirMax80m;

//        @SerializedName("maxDewpointC")
//        public float maxDewpointC;

//        @SerializedName("pressureIN")
//        public float pressureIN;

//        @SerializedName("avgFeelslikeF")
//        public float avgFeelslikeF;

//        @SerializedName("iceaccum")
//        public Object iceaccum;

        @SerializedName("isDay")
        public boolean isDay;

//        @SerializedName("minTempC")
//        public float minTempC;

//        @SerializedName("minDewpointF")
//        public float minDewpointF;

//        @SerializedName("windSpeedKTS")
//        public float windSpeedKTS;

//        @SerializedName("solradWM2")
//        public float solradWM2;

        @SerializedName("windSpeedKPH")
        public float windSpeedKPH;

//        @SerializedName("windGustMPH")
//        public float windGustMPH;

//        @SerializedName("altimeterIN")
//        public float altimeterIN;

//        @SerializedName("maxHumidity")
//        public float maxHumidity;

//        @SerializedName("windSpeedMPH")
//        public float windSpeedMPH;

//        @SerializedName("windGustKPH")
//        public float windGustKPH;

//        @SerializedName("windDirMaxDEG")
//        public float windDirMaxDEG;

        @SerializedName("tempC")
        public float tempC;
    }

//    public static class Profile {
//
//        @SerializedName("elevM")
//        public float elevM;
//
//        @SerializedName("tz")
//        public String tz;
//
//        @SerializedName("elevFT")
//        public float elevFT;
//    }

//    public static class Solrad {
//
//        @SerializedName("azimuthDEG")
//        public float azimuthDEG;
//
//        @SerializedName("dhiWM2")
//        public float dhiWM2;
//
//        @SerializedName("ghiWM2")
//        public float ghiWM2;
//
//        @SerializedName("zenithDEG")
//        public float zenithDEG;
//
//        @SerializedName("dniWM2")
//        public float dniWM2;
//    }

    List<HourlyWeather> createHourlyModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = response.get(0).periods.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (PeriodsItem hourly : response.get(0).periods) {

            if (count > limitNextHours) {
                break;
            }

            weatherList.add(new HourlyWeather.Builder()

                    .setDate(hourly.timestamp * 1000)

                    .setCondition(Util.getIcon(hourly.weatherPrimaryCoded, hourly.sky, hourly.isDay),
							Util.getDescription(context, config.getLanguage(), hourly.weather, hourly.weatherPrimaryCoded))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.tempC),
                            config.getTemperatureUnit().str())
                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.feelslikeC),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewpointC),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), hourly.pressureMB),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(hourly.humidity))
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibilityKM),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), hourly.windSpeedKPH),
                            hourly.windDirDEG,
                            config.getSpeedUnit().str())

                    .setUvIndex(hourly.uvi)

                    .setCloudCover(Math.round(hourly.sky))

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(),
                                    Math.max(hourly.precipMM, hourly.snowCM * 10)),
                            Math.round(hourly.pop),
                            config.getPrecipitationAmountUnit().str())

                    .build());

            count += 1;

        }

        return weatherList;
    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(response.get(0).loc.latitude, response.get(0).loc.longitude)
                .build();

    }
}