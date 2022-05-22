package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.Expose;
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

    @SerializedName("lat")
    @Expose
    float lat;

    @SerializedName("lon")
    @Expose
    float lon;
    @SerializedName("hourly")
    @Expose
    List<Hourly> hourly = null;

    static class Hourly {

        @SerializedName("dt")
        @Expose
        long dt;

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

        @SerializedName("wind_gust")
        @Expose
        float windGust;

        @SerializedName("weather")
        @Expose
        List<Weather> weather = null;

        @SerializedName("pop")
        @Expose
        float pop;

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

    List<HourlyWeather> createHourlyModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = hourly.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours = (int) (limitNextHours / 3f) + 1;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Hourly hourly : hourly) {

            if (count > limitNextHours) {
                break;
            }

            // Precipitation
            float precipitation = 0;
            if (hourly.snow != null && hourly.snow._1h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.snow._1h);
            } else if (hourly.rain != null && hourly.rain._1h > 0) {
                precipitation = AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.rain._1h);
            }

            weatherList.add(new HourlyWeather.Builder()

                    .setDate(hourly.dt * 1000)

                    .setCondition(Util.getIcon(hourly.weather.get(0).id, hourly.weather.get(0).icon.contains("d")),
                            Util.getDescription(context, config.getLanguage(),
                                    hourly.weather.get(0).description,
                                    hourly.weather.get(0).id))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temp),
                            config.getTemperatureUnit().str())
                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.feelsLike),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPoint),
                            config.getTemperatureUnit().str())

                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.pressure),
                            config.getPressureUnit().str())

                    .setHumidity(Math.round(hourly.humidity))

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), hourly.windSpeed),
                            hourly.windDeg,
                            config.getSpeedUnit().str())

                    .setVisibility(LENGTH.convert(LENGTH.m, config.getVisibilityUnit(), hourly.visibility),
                            config.getVisibilityUnit().str())

                    .setCloudCover(Math.round(hourly.clouds))

                    .setPrecipitation(precipitation, Math.round(hourly.pop), config.getPrecipitationAmountUnit().str())

                    .setUvIndex(hourly.uvi)

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
