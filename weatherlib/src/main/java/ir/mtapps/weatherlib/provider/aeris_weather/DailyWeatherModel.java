package ir.mtapps.weatherlib.provider.aeris_weather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

public class DailyWeatherModel {

    @SerializedName("success")
    public boolean success;

    @SerializedName("response")
    public List<ResponseItem> response;

    @SerializedName("error")
    public String error;

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

//        @SerializedName("feelslikeC")
//        public float feelslikeC;

//        @SerializedName("visibilityMI")
//        public float visibilityMI;

//        @SerializedName("windSpeedMaxMPH")
//        public float windSpeedMaxMPH;

        @SerializedName("windDirDEG")
        public float windDirDEG;

//        @SerializedName("windDir")
//        public String windDir;

//        @SerializedName("sunriseISO")
//        public String sunriseISO;

//        @SerializedName("iceaccumMM")
//        public Object iceaccumMM;

//        @SerializedName("windSpeedMaxKTS")
//        public float windSpeedMaxKTS;

//        @SerializedName("iceaccumIN")
//        public Object iceaccumIN;

//        @SerializedName("mintempF")
//        public float mintempF;

//        @SerializedName("snowIN")
//        public float snowIN;

        @SerializedName("weather")
        public String weather;

//        @SerializedName("sunsetISO")
//        public String sunsetISO;

        @SerializedName("maxFeelslikeC")
        public float maxFeelslikeC;

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

//        @SerializedName("windGust80mMPH")
//        public float windGust80mMPH;

//        @SerializedName("windSpeedMax80mMPH")
//        public float windSpeedMax80mMPH;

//        @SerializedName("weatherPrimary")
//        public String weatherPrimary;

//        @SerializedName("windGust80mKPH")
//        public float windGust80mKPH;

//        @SerializedName("avgDewpointF")
//        public float avgDewpointF;

//        @SerializedName("windSpeedMax80mKPH")
//        public float windSpeedMax80mKPH;

//        @SerializedName("windGust80mKTS")
//        public float windGust80mKTS;

        @SerializedName("avgDewpointC")
        public float avgDewpointC;

//        @SerializedName("precipIN")
//        public float precipIN;

//        @SerializedName("windSpeedMax80mKTS")
//        public float windSpeedMax80mKTS;

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

        @SerializedName("sunrise")
        public long sunrise;

//        @SerializedName("avgTempF")
//        public float avgTempF;

//        @SerializedName("windDirMin")
//        public String windDirMin;

//        @SerializedName("maxCoverage")
//        public String maxCoverage;

//        @SerializedName("icon")
//        public String icon;

        @SerializedName("minFeelslikeC")
        public float minFeelslikeC;

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

        @SerializedName("minTempC")
        public float minTempC;

        @SerializedName("maxTempC")
        public float maxTempC;

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

//        @SerializedName("minDewpointF")
//        public float minDewpointF;

//        @SerializedName("windSpeedKTS")
//        public float windSpeedKTS;

        @SerializedName("sunset")
        public long sunset;

//        @SerializedName("solradWM2")
//        public float solradWM2;

        @SerializedName("windSpeedKPH")
        public float windSpeedKPH;

//        @SerializedName("windGustMPH")
//        public float windGustMPH;

//        @SerializedName("maxHumidity")
//        public float maxHumidity;

//        @SerializedName("windSpeedMPH")
//        public float windSpeedMPH;

        //        @SerializedName("windGustKPH")
        public float windGustKPH;

//        @SerializedName("windDirMaxDEG")
//        public float windDirMaxDEG;

//        @SerializedName("tempC")
//        public float tempC;
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

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = response.get(0).periods.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (PeriodsItem daily : response.get(0).periods) {

            if (count > dayCount) {
                break;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.timestamp * 1000)

                    .setCondition(Util.getIcon(daily.weatherPrimaryCoded, daily.sky, daily.isDay),
                            Util.getDescription(context, config.getLanguage(), daily.weather, daily.weatherPrimaryCoded))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.minTempC),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.maxTempC),
                            config.getTemperatureUnit().str())

                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.minFeelslikeC),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.maxFeelslikeC),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.avgDewpointC),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), daily.pressureMB),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity))
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), daily.visibilityKM),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), daily.windSpeedKPH),
                            daily.windDirDEG,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.sky))

                    .setUvIndex(daily.uvi)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), Math.max(daily.snowCM * 10, daily.precipMM)),
                            Math.round(daily.pop),
                            config.getPrecipitationAmountUnit().str())

                    .setSunAstronomy(daily.sunrise * 1000, daily.sunset * 1000)

                    .build());

            count += 1;

        }

        return weatherList;
    }

    Astronomy createTodayAstronomyModel() {

        PeriodsItem today = response.get(0).periods.get(0);

        return new Astronomy.Builder()
                .setSunAstronomy(today.sunrise * 1000, today.sunset * 1000)
                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(response.get(0).loc.latitude, response.get(0).loc.longitude)
                .build();

    }
}