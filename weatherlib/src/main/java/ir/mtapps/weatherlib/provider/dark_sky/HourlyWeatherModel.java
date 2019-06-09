package ir.mtapps.weatherlib.provider.dark_sky;

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

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;

//    @SerializedName("timezone")
//    String timezone;

    @SerializedName("hourly")
    Hourly hourly;

    static class Hourly {

//        @SerializedName("summary")
//        String summary;

//        @SerializedName("icon")
//        String icon;

        @SerializedName("data")
        List<Datum> data = null;

        static class Datum {

            @SerializedName("time")
            long time;

            @SerializedName("summary")
            String summary;

            @SerializedName("icon")
            String icon;

            @SerializedName("precipIntensity")
            float precipIntensity;

            @SerializedName("precipProbability")
            float precipProbability;

            @SerializedName("precipType")
            String precipType;

            @SerializedName("temperature")
            float temperature;

            @SerializedName("apparentTemperature")
            float apparentTemperature;

            @SerializedName("dewPoint")
            float dewPoint;

            @SerializedName("humidity")
            float humidity;

            @SerializedName("pressure")
            float pressure;

            @SerializedName("windSpeed")
            float windSpeed;

//            @SerializedName("windGust")
//            float windGust;

            @SerializedName("windBearing")
            int windBearing;

            @SerializedName("cloudCover")
            float cloudCover;

            @SerializedName("uvIndex")
            float uvIndex;

//            @SerializedName("ozone")
//            float ozone;

            @SerializedName("visibility")
            float visibility;
        }
    }

    List<HourlyWeather> createModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = hourly.data.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Hourly.Datum hourly : this.hourly.data) {

            if (count > limitNextHours) {
                break;
            }

            weatherList.add(new HourlyWeather.Builder()

                    .setDate(hourly.time * 1000)

                    .setCondition(Util.getIcon(hourly.icon),
                            (Util.getDescription(context, config.getLanguage(), hourly.summary)))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temperature),
                            config.getTemperatureUnit().str())
                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.apparentTemperature),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPoint),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(hourly.humidity * 100))
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibility),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), hourly.windSpeed),
                            hourly.windBearing,
                            config.getSpeedUnit().str())

                    .setUvIndex(hourly.uvIndex)

                    .setCloudCover(Math.round(hourly.cloudCover * 100))

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.precipIntensity),
                            Math.round(hourly.precipProbability),
                            config.getPrecipitationAmountUnit().str())

                    .build());

            count += 1;

        }

        return weatherList;
    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
