package ir.mtapps.weatherlib.provider.weatherbit;

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

class CurrentWeatherModel {

    @SerializedName("data")
    List<Datum> data = null;

    @SerializedName("count")
    int count;

    static class Datum {

//        @SerializedName("wind_cdir")
//        String windCdir;

        @SerializedName("rh")
        float humidity;

        @SerializedName("pod")
        String pod;

        @SerializedName("lon")
        double lon;

        @SerializedName("pres")
        float pressure;

//        @SerializedName("slp")
//        float seeLevelPressure;

//        @SerializedName("timezone")
//        String timezone;

//        @SerializedName("ob_time")
//        String obTime;

        @SerializedName("country_code")
        String countryCode;

        @SerializedName("clouds")
        float clouds;

        @SerializedName("vis")
        float visibility;

        @SerializedName("solar_rad")
        float solarRad;

        @SerializedName("state_code")
        String stateCode;

        @SerializedName("wind_spd")
        float windSpd;

        @SerializedName("lat")
        double lat;

//        @SerializedName("wind_cdir_full")
//        String windCdirFull;

        @SerializedName("slp")
        float seeLevelPressure;

//        @SerializedName("datetime")
//        String datetime;

//        @SerializedName("ts")
//        float ts;

//        @SerializedName("station")
//        String station;

//        @SerializedName("h_angle")
//        int hAngle;

        @SerializedName("dewpt")
        float dewPoint;

        @SerializedName("uv")
        float uvIndex;

//        @SerializedName("dni")
//        float dni;

        @SerializedName("wind_dir")
        int windDir;

//        @SerializedName("elev_angle")
//        float elevAngle;

//        @SerializedName("ghi")
//        float ghi;

//        @SerializedName("dhi")
//        float dhi;

        @SerializedName("precip")
        float rain;

        @SerializedName("snow")
        float snow;

        @SerializedName("city_name")
        String cityName;

        @SerializedName("weather")
        Weather weather;

//        @SerializedName("sunset")
//        String sunsetStr;

        @SerializedName("temp")
        float temp;

//        @SerializedName("sunrise")
//        String sunriseStr;

        @SerializedName("app_temp")
        float appTemp;

//        @SerializedName("aqi")
//        float airQuality;

        static class Weather {

            @SerializedName("icon")
            String icon;
            
            @SerializedName("code")
            int code;
            
            @SerializedName("description")
            String description;

        }
    }


    CurrentWeather createModel(Context context, WeatherConfig config) {

        Datum current = data.get(0);

        float precipitation = 0;

        if (current.snow > 0) {
            precipitation =  current.snow;
        } else if (current.rain > 0) {
            precipitation = current.rain;
        }

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(current.weather.code, current.pod),
                        Util.getDescription(context, config.getLanguage(), current.weather.description, current.weather.code))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.temp),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.appTemp),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.dewPoint),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), current.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(current.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), current.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), current.windSpd),
                        current.windDir,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(current.clouds))

                .setUvIndex(current.uvIndex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity() {

        Datum current = data.get(0);

        return new City.Builder()
                .setName(current.cityName)
                .setCountry(current.countryCode)
                .setCoordinate(current.lat, current.lon)
                .build();

    }

}
