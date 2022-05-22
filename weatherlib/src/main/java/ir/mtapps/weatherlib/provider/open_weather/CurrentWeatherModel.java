package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.Expose;
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

    @SerializedName("lat")
    @Expose
    float lat;

    @SerializedName("lon")
    @Expose
    float lon;

//    @SerializedName("timezone")
//    @Expose
//    String timezone;

//    @SerializedName("timezone_offset")
//    @Expose
//    int timezoneOffset;

    @SerializedName("current")
    @Expose
    Current current;

    public static class Current {

        @SerializedName("dt")
        @Expose
        long dt;

        @SerializedName("sunrise")
        @Expose
        long sunrise;

        @SerializedName("sunset")
        @Expose
        long sunset;

        @SerializedName("temp")
        @Expose
        float temp;

        @SerializedName("feels_like")
        @Expose
        float feelsLike;

        @SerializedName("pressure")
        @Expose
        float pressure;

        @SerializedName("humidity")
        @Expose
        float humidity;

        @SerializedName("dew_point")
        @Expose
        float dewPoint;

        @SerializedName("uvi")
        @Expose
        float uvi;

        @SerializedName("clouds")
        @Expose
        float clouds;

        @SerializedName("visibility")
        @Expose
        float visibility;

        @SerializedName("wind_speed")
        @Expose
        float windSpeed;

        @SerializedName("wind_deg")
        @Expose
        float windDeg;

//        @SerializedName("wind_gust")
//        @Expose
//        float windGust;

        @SerializedName("weather")
        @Expose
        List<Weather> weather = null;

        @SerializedName("rain")
        Rain rain;

        @SerializedName("snow")
        Snow snow;
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

    static class Rain {

        @SerializedName("1h")
        float _1h;

    }

    static class Snow {

        @SerializedName("1h")
        float _1h;

    }

    // ---------------------------------------------------------------------------------------------

    CurrentWeather createCurrentWeatherModel(Context context, WeatherConfig config) {

        // Condition
        long ct = System.currentTimeMillis() / 1000L;
        boolean isDay = ct >= current.sunrise && ct < current.sunset;
        int iconCode = current.weather.get(0).id;

        // Precipitation
        float precipitation = 0;
        if (current.snow != null && current.snow._1h > 0) {
            precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), current.snow._1h);
        } else if (current.rain != null && current.rain._1h > 0) {
            precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), current.rain._1h);
        }

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(iconCode, isDay),
                        Util.getDescription(context, config.getLanguage(), current.weather.get(0).description, iconCode))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.temp),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.feelsLike),
                        config.getTemperatureUnit().str())


                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), current.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(current.humidity))

                .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), current.windSpeed),
                        current.windDeg,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(current.clouds))

                .setPrecipitation(precipitation, config.getPrecipitationAmountUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.dewPoint),
                        config.getTemperatureUnit().str())

                .setUvIndex(current.uvi)

                .setVisibility(LENGTH.convert(LENGTH.m, config.getVisibilityUnit(), current.visibility),
                        config.getVisibilityUnit().str())

                .build();

    }

    City getCity() {

        return new City.Builder()
//                .setName(name)
//                .setCountry(sys.country)
                .setCoordinate(lat, lon)
                .build();

    }
}
