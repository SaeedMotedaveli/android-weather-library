package ir.mtapps.weatherlib.provider.weatherbit;

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
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

class HourlyWeatherModel {

    @SerializedName("error")
    String error;

    @SerializedName("data")
    List<Datum> data = null;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("lon")
    double lon;

//    @SerializedName("timezone")
//    String timezone;

    @SerializedName("lat")
    double lat;

    @SerializedName("country_code")
    String countryCode;

//    @SerializedName("state_code")
//    String stateCode;

    static class Datum {

//        @SerializedName("wind_cdir")
//        String windCdir;

        @SerializedName("rh")
        int humidity;

        @SerializedName("pres")
        float pressure;

        @SerializedName("clouds")
        int clouds;

        @SerializedName("vis")
        float visibility;

        @SerializedName("wind_spd")
        float windSpd;

//        @SerializedName("wind_cdir_full")
//        String windCdirFull;

//        @SerializedName("slp")
//        float seeLevelPressure;

//        @SerializedName("datetime")
//        String datetime;

        @SerializedName("ts")
        long ts;

        @SerializedName("dewpt")
        float dewPoint;

        @SerializedName("uv")
        float uvIndex;

        @SerializedName("wind_dir")
        int windDir;

        @SerializedName("precip")
        float precip;

        @SerializedName("weather")
        Weather weather;

        @SerializedName("temp")
        float temp;

//        @SerializedName("ozone")
//        float ozone;

//        @SerializedName("wind_gust_spd")
//        float windGustSpd;

//        @SerializedName("snow_depth")
//        float snowDepth;

        @SerializedName("pop")
        int precipitationProbably;

        @SerializedName("snow")
        float snow;

//        @SerializedName("clouds_hi")
//        int cloudsHi;

//        @SerializedName("clouds_mid")
//        int cloudsMid;

//        @SerializedName("clouds_low")
//        int cloudsLow;

        @SerializedName("pod")
        String pod;

//        @SerializedName("timestamp_utc")
//        String timestampUtc;

//        @SerializedName("solar_rad")
//        float solarRad;

//        @SerializedName("timestamp_local")
//        String timestampLocal;

//        @SerializedName("dni")
//        float dni;

//        @SerializedName("dhi")
//        float dhi;

        @SerializedName("app_temp")
        float appTemp;

//        @SerializedName("ghi")
//        float ghi;

        static class Weather {

            @SerializedName("icon")
            String icon;

            @SerializedName("code")
            int code;

            @SerializedName("description")
            String description;

        }

    }

    List<HourlyWeather> createModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = data.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Datum hourly : data) {

            if (count > limitNextHours) {
                break;
            }

            float precipitation = 0;
            if (hourly.snow > 0) {
                precipitation = hourly.snow;
            } else if (hourly.precip > 0) {
                precipitation = hourly.precip;
            }

            weatherList.add(new HourlyWeather.Builder()

                    .setDate(hourly.ts * 1000)

                    .setCondition(Util.getIcon(hourly.weather.code, hourly.pod),
                            Util.getDescription(context, config.getLanguage(), hourly.weather.description, hourly.weather.code))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temp),
                            config.getTemperatureUnit().str())
                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.appTemp),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPoint),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), hourly.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(hourly.humidity)
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibility),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), hourly.windSpd),
                            hourly.windDir,
                            config.getSpeedUnit().str())

                    .setUvIndex(hourly.uvIndex)

                    .setCloudCover(hourly.clouds)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                            hourly.precipitationProbably,
                            config.getPrecipitationAmountUnit().str())

                    .build());

            count += 1;

        }

        return weatherList;
    }

    City getCity() {

        return new City.Builder()
                .setName(cityName)
                .setCountry(countryCode)
                .setCoordinate(lat, lon)
                .build();

    }

}
