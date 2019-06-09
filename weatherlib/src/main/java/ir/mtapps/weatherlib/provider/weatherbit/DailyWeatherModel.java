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
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {

    @SerializedName("data")
    List<Datum> data = null;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("lon")
    double lon;

    @SerializedName("timezone")
    String timezone;

    @SerializedName("lat")
    double lat;

    @SerializedName("country_code")
    String countryCode;

    @SerializedName("state_code")
    String stateCode;

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

        @SerializedName("moonrise_ts")
        long moonriseTs;

        @SerializedName("sunset_ts")
        long sunsetTs;

//        @SerializedName("ozone")
//        float ozone;

        @SerializedName("moon_phase")
        float moonPhase;

//        @SerializedName("wind_gust_spd")
//        float windGustSpd;

//        @SerializedName("snow_depth")
//        float snowDepth;

        @SerializedName("sunrise_ts")
        long sunriseTs;

        @SerializedName("app_min_temp")
        float appMinTemp;

        @SerializedName("pop")
        int precipitationProbably;

        @SerializedName("app_max_temp")
        float appMaxTemp;

        @SerializedName("snow")
        float snow;

//        @SerializedName("valid_date")
//        String validDate;

//        @SerializedName("max_dhi")
//        Object maxDhi;

//        @SerializedName("clouds_hi")
//        int cloudsHi;

        @SerializedName("max_temp")
        float maxTemp;

        @SerializedName("moonset_ts")
        long moonsetTs;

        @SerializedName("min_temp")
        float minTemp;

//        @SerializedName("clouds_mid")
//        int cloudsMid;

//        @SerializedName("clouds_low")
//        int cloudsLow;

        static class Weather {

            @SerializedName("icon")
            String icon;

            @SerializedName("code")
            int code;

            @SerializedName("description")
            String description;

        }
    }

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = data.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Datum daily : data) {

            if (count > dayCount) {
                break;
            }

            float precipitation = 0;
            if (daily.snow > 0) {
                precipitation = daily.snow;
            } else if (daily.precip > 0) {
                precipitation = daily.precip;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.ts * 1000)

                    .setCondition(Util.getIcon(daily.weather.code, "d"),
                            Util.getDescription(context, config.getLanguage(), daily.weather.description, daily.weather.code))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.minTemp),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.maxTemp),
                            config.getTemperatureUnit().str())
                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.appMinTemp),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.appMaxTemp),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dewPoint),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.mb, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(daily.humidity)
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), daily.visibility),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), daily.windSpd),
                            daily.windDir,
                            config.getSpeedUnit().str())

                    .setCloudCover(daily.clouds)

                    .setUvIndex(daily.uvIndex)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                            daily.precipitationProbably,
                            config.getPrecipitationAmountUnit().str())

                    .setSunAstronomy(daily.sunriseTs * 1000, daily.sunsetTs * 1000)
                    .setMoonAstronomy(daily.moonriseTs * 1000, daily.moonsetTs * 1000)
                    .setMoonPhase(daily.moonPhase)

                    .build());

            count += 1;

        }

        return weatherList;

    }

    Astronomy createAstronomyModel() {

        Datum today = data.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(today.sunriseTs * 1000, today.sunsetTs * 1000)
                .setMoonAstronomy(today.moonriseTs * 1000, today.moonsetTs * 1000)
                .setMoonPhase(today.moonPhase / 2f)

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(cityName)
                .setCountry(countryCode)
                .setCoordinate(lat, lon)
                .build();

    }
}
