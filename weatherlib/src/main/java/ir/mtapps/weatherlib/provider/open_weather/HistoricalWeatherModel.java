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
import ir.mtapps.weatherlib.model.HistoricalWeather;

class HistoricalWeatherModel {

    @SerializedName("message")
    String message;

    @SerializedName("cod")
    String cod;

//    @SerializedName("city_id")
//    public int cityId;

//    @SerializedName("calctime")
//    public float calctime;

//    @SerializedName("cnt")
//    public int cnt;

    @SerializedName("name")
    String name;

    @SerializedName("list")
    List<DataList> list = null;


    static class DataList {

        @SerializedName("dt")
        long dt;

        @SerializedName("weather")
        List<Weather> weather;

        @SerializedName("main")
        Main main;

        @SerializedName("wind")
        Wind wind;

        @SerializedName("clouds")
        Cloud clouds;

        @SerializedName("rain")
        Rain rain;

        @SerializedName("snow")
        Snow snow;

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

        static class Main {

            @SerializedName("temp")
            float temp;

            @SerializedName("pressure")
            float pressure;

            @SerializedName("humidity")
            float humidity;

//            @SerializedName("temp_min")
//            float temp_min;

//            @SerializedName("temp_max")
//            float temp_max;

        }

        static class Wind {

            @SerializedName("speed")
            float speed;

            @SerializedName("deg")
            float deg;

        }

        static class Cloud {

            @SerializedName("all")
            float all;

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

    List<HistoricalWeather> createModel(Context context, WeatherConfig config) {

        List<HistoricalWeather> weatherList = new ArrayList<>();

        for (DataList item : this.list) {

            HistoricalWeather model = new HistoricalWeather();

            model.date = item.dt * 1000;

            model.icon = Util.getIcon(item.weather.get(0).id, item.weather.get(0).icon.contains("d"));
            model.description = Util.getDescription(context, config.getLanguage(),
                    item.weather.get(0).description,
                    item.weather.get(0).id);

            model.temperature = TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), item.main.temp);
            model.pressure = PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), item.main.pressure);
            model.humidity = Math.round(item.main.humidity);

            model.windSpeed = SPEED.convert(SPEED.m_s, config.getSpeedUnit(), item.wind.speed);
            model.windDegree = item.wind.deg;

            model.cloudsCover = Math.round(item.clouds.all);

            float precipitation = 0;

            if (item.snow != null && item.snow._3h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), item.snow._3h);
            } else if (item.rain != null && item.rain._3h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), item.rain._3h);
            }

            model.precipitationAmount = precipitation;

            weatherList.add(model);
        }

        return weatherList;

    }

    City getCity() {

        return new City.Builder()
                .setName(name)
//                .setCountry(sys.country)
//                .setCoordinate(coord.lat, coord.lon)
                .build();
    }

}
