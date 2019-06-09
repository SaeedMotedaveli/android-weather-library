package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;

class CurrentWeatherModel {

    @SerializedName("coord")
    Coord coord;

    @SerializedName("weather")
    List<Weather> weather = null;

//    @SerializedName("base")
//    public String base;

    @SerializedName("main")
    Main main;

    @SerializedName("wind")
    Wind wind;

    @SerializedName("clouds")
    Clouds clouds;

    @SerializedName("rain")
    Rain rain;

    @SerializedName("snow")
    Snow snow;

//    @SerializedName("dt")
//    public int dt;

    @SerializedName("sys")
    Sys sys;

//    @SerializedName("id")
//    public int id;

    @SerializedName("name")
    String name;

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

    static class Coord {

        @SerializedName("lon")
        double lon;

        @SerializedName("lat")
        double lat;

    }

    static class Weather {

        @SerializedName("id")
        int id;

        @SerializedName("main")
        String main;

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
        int humidity;

//        @SerializedName("temp_min")
//        float tempMin;

//        @SerializedName("temp_max")
//        float tempMax;

//        @SerializedName("sea_level")
//        private float seaLevel;

//        @SerializedName("grnd_level")
//        public float grndLevel;

    }

    static class Wind {

        @SerializedName("speed")
        float speed;

        @SerializedName("deg")
        float deg;

    }

    static class Clouds {

        @SerializedName("all")
        int all;

    }

    static class Rain {

        @SerializedName("3h")
        float _3h;

    }

    static class Snow {

        @SerializedName("3h")
        float _3h;

    }

    static class Sys {

//        @SerializedName("type")
//        public int type;

//        @SerializedName("id")
//        public int id;

//        @SerializedName("message")
//        public float message;

        @SerializedName("country")
        String country;

        @SerializedName("sunrise")
        long sunrise;

        @SerializedName("sunset")
        long sunset;

    }

    CurrentWeather createModel(Context context, WeatherConfig config) {

        // Condition
        boolean isDay = weather.get(0).icon.contains("d");
        int iconCode = weather.get(0).id;

        // Precipitation
        float precipitation = 0;
        if (snow != null && snow._3h > 0) {
            precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), snow._3h);
        } else if (rain != null && rain._3h > 0) {
            precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), rain._3h);
        }

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(iconCode, isDay),
                        Util.getDescription(context, config.getLanguage(), weather.get(0).description, iconCode))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), main.temp),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), main.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(main.humidity))

                .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), wind.speed),
                        wind.deg,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(clouds.all))

                .setPrecipitation(precipitation, config.getPrecipitationAmountUnit().str())

                .build();

    }

    Astronomy createAstronomyModel() {

        return new Astronomy.Builder()

                .setSunAstronomy(sys.sunrise * 1000, sys.sunset * 1000)

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(name)
                .setCountry(sys.country)
                .setCoordinate(coord.lat, coord.lon)
                .build();

    }
}
