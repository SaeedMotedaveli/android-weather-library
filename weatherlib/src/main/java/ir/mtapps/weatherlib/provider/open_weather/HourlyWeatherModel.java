package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

class HourlyWeatherModel {

    @SerializedName("city")
    City city;

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

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

        @SerializedName("main")
        Main main;

        @SerializedName("weather")
        List<Weather> weather = null;

        @SerializedName("clouds")
        Clouds clouds;

        @SerializedName("wind")
        Wind wind;

        @SerializedName("sys")
        Sys sys;

        @SerializedName("rain")
        Rain rain;

        @SerializedName("snow")
        Snow snow;

//        @SerializedName("dt_txt")
//        String dtTxt;

        static class Main {

            @SerializedName("temp")
            float temp;

//            @SerializedName("temp_min")
//            float tempMin;

//            @SerializedName("temp_max")
//            float tempMax;

            @SerializedName("pressure")
            float pressure;

//            @SerializedName("sea_level")
//            float seaLevel;

//            @SerializedName("grnd_level")
//            float grndLevel;

            @SerializedName("humidity")
            int humidity;

//            @SerializedName("temp_kf")
//            float tempKf;

        }

        static class Weather {

            @SerializedName("id")
            int id;

//            @SerializedName("main")
//            String main;

            @SerializedName("description")
            String description;

//            @SerializedName("icon")
//            String icon;

        }

        static class Clouds {

            @SerializedName("all")
            int all;

        }

        static class Wind {

            @SerializedName("speed")
            float speed;

            @SerializedName("deg")
            float deg;

        }

        static class Sys {

            @SerializedName("pod")
            String pod;

        }

        static class Rain {

            @SerializedName("3h")
            float _3h;

        }

        static class Snow {

            @SerializedName("3h")
            float _3h;

        }

    }

    List<HourlyWeather> createModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = list.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours = (int) (limitNextHours / 3f) + 1;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (HourlyWeatherModel.DataList hourly : this.list) {

            if (count > limitNextHours) {
                break;
            }

            // Precipitation
            float precipitation = 0;
            if (hourly.snow != null && hourly.snow._3h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.snow._3h);
            } else if (hourly.rain != null && hourly.rain._3h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.rain._3h);
            }

            weatherList.add(new HourlyWeather.Builder()

                    .setDate(hourly.dt * 1000)

                    .setCondition(Util.getIcon(hourly.weather.get(0).id, hourly.sys.pod.contentEquals("d")),
                            Util.getDescription(context, config.getLanguage(),
                                    hourly.weather.get(0).description,
                                    hourly.weather.get(0).id))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.main.temp),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.main.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(hourly.main.humidity))

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), hourly.wind.speed),
                            hourly.wind.deg,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(hourly.clouds.all))

                    .setPrecipitation(precipitation, config.getPrecipitationAmountUnit().str())

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
