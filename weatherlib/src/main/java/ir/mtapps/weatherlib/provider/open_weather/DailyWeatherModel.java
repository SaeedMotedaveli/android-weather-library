package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

    @SerializedName("city")
    City city;

//    @SerializedName("cnt")
//    int cnt;

    @SerializedName("list")
    List<DataList> list = null;

    static class City {

        @SerializedName("id")
        int id;

        @SerializedName("name")
        String name;

        @SerializedName("coord")
        Coord coord;

        @SerializedName("country")
        String country;

        static class Coord {

            @SerializedName("lon")
            double lon;

            @SerializedName("lat")
            double lat;

        }

    }

    static class DataList {

        @SerializedName("dt")
        long dt;

        @SerializedName("temp")
        Temp temp;

        @SerializedName("pressure")
        float pressure;

        @SerializedName("humidity")
        int humidity;

        @SerializedName("weather")
        List<Weather> weather = null;

        @SerializedName("speed")
        float speed;

        @SerializedName("deg")
        float deg;

        @SerializedName("clouds")
        int clouds;

        static class Temp {

            @SerializedName("day")
            float day;

            @SerializedName("min")
            float min;

            @SerializedName("max")
            float max;

//            @SerializedName("night")
//            float night;

//            @SerializedName("eve")
//            float eve;

//            @SerializedName("morn")
//            float morn;

        }

        static class Weather {

            @SerializedName("id")
            int id;

//            @SerializedName("main")
//            String main;

            @SerializedName("description")
            String description;

            @SerializedName("icon")
            String icon;

        }
    }

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = list.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (DataList daily : this.list) {

            if (count > dayCount) {
                break;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.dt * 1000)

                    .setCondition(Util.getIcon(daily.weather.get(0).id, true),
                            Util.getDescription(context, config.getLanguage(),
                                    daily.weather.get(0).description,
                                    daily.weather.get(0).id))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.min),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.max),
                            config.getTemperatureUnit().str())

                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity))

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), daily.speed),
                            daily.deg,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.clouds))

                    .build());

            count += 1;

        }

        return weatherList;
    }

    ir.mtapps.weatherlib.model.City getCity() {

        return new ir.mtapps.weatherlib.model.City.Builder()
                .setName(city.name)
                .setCountry(city.country)
                .setCoordinate(city.coord.lat, city.coord.lon)
                .build();

    }
}
