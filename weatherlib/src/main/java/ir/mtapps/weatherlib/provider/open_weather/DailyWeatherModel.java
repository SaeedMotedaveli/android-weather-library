package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {
    @SerializedName("lat")
    @Expose
    float lat;

    @SerializedName("lon")
    @Expose
    float lon;

    @SerializedName("daily")
    @Expose
    List<Daily> daily = null;

    public static class Daily {

        @SerializedName("dt")
        @Expose
        long dt;

        @SerializedName("sunrise")
        @Expose
        long sunrise;

        @SerializedName("sunset")
        @Expose
        long sunset;

        @SerializedName("moonrise")
        @Expose
        long moonrise;

        @SerializedName("moonset")
        @Expose
        long moonset;

        @SerializedName("moon_phase")
        @Expose
        float moonPhase;

        @SerializedName("temp")
        @Expose
        Temp temp;

        @SerializedName("feels_like")
        @Expose
        FeelsLike feelsLike;

        @SerializedName("pressure")
        @Expose
        float pressure;

        @SerializedName("humidity")
        @Expose
        float humidity;

        @SerializedName("dew_point")
        @Expose
        float dewPoint;

        @SerializedName("wind_speed")
        @Expose
        float windSpeed;

        @SerializedName("wind_deg")
        @Expose
        float windDeg;

        @SerializedName("wind_gust")
        @Expose
        float windGust;

        @SerializedName("rain")
        @Expose
        float rain;

        @SerializedName("snow")
        @Expose
        float snow;

        @SerializedName("weather")
        @Expose
        List<Weather> weather = null;

        @SerializedName("clouds")
        @Expose
        float clouds;

        @SerializedName("pop")
        @Expose
        float pop;

        @SerializedName("uvi")
        @Expose
        float uvi;
    }

    static class Temp {

        @SerializedName("day")
        @Expose
        float day;

        @SerializedName("min")
        @Expose
        float min;

        @SerializedName("max")
        @Expose
        float max;

        @SerializedName("night")
        @Expose
        float night;

        @SerializedName("eve")
        @Expose
        float eve;

        @SerializedName("morn")
        @Expose
        float morn;
    }

    static class FeelsLike {

        @SerializedName("day")
        @Expose
        float day;

        @SerializedName("night")
        @Expose
        float night;

        @SerializedName("eve")
        @Expose
        float eve;

        @SerializedName("morn")
        @Expose
        float morn;

        float getMin() {
            float val = day;
            val = Math.min(val, night);
            val = Math.min(val, eve);
            val = Math.min(val, morn);
            return val;
        }

        float getMax() {
            float val = day;
            val = Math.max(val, night);
            val = Math.max(val, eve);
            val = Math.max(val, morn);
            return val;
        }
    }

    static class Weather {

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("main")
        @Expose
        String main;

        @SerializedName("description")
        @Expose
        String description;

        @SerializedName("icon")
        @Expose
        String icon;
    }

    // ---------------------------------------------------------------------------------------------

    List<DailyWeather> createDailyModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = daily.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Daily daily : daily) {

            if (count > dayCount) {
                break;
            }

            // Precipitation
            float precipitation = 0;
            if (daily.snow > 0) {
                precipitation = daily.snow;
            } else if (daily.rain > 0) {
                precipitation = daily.rain;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.dt * 1000)

                    .setSunAstronomy(daily.sunrise * 1000, daily.sunset * 1000)
                    .setMoonAstronomy(daily.moonrise * 1000, daily.moonset * 1000)
                    .setMoonPhase(daily.moonPhase)

                    .setCondition(Util.getIcon(daily.weather.get(0).id, true),
                            Util.getDescription(context, config.getLanguage(),
                                    daily.weather.get(0).description,
                                    daily.weather.get(0).id))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.min),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.max),
                            config.getTemperatureUnit().str())

                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelsLike.getMin()),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelsLike.getMax()),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dewPoint),
                            config.getTemperatureUnit().str())

                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity))

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), daily.windSpeed),
                            daily.windDeg,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.clouds))

                    .setUvIndex(daily.uvi)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                            Math.round(daily.pop),
                            config.getPrecipitationAmountUnit().str())

                    .build());

            count += 1;

        }

        return weatherList;
    }

    City getCity() {

        return new City.Builder()
//                .setName(name)
//                .setCountry(sys.country)
                .setCoordinate(lat, lon)
                .build();

    }
}
