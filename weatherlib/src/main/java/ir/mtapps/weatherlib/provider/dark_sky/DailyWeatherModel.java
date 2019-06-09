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
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;

//    @SerializedName("timezone")
//    String timezone;

    @SerializedName("daily")
    Daily daily;

    static class Daily {

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

            @SerializedName("sunriseTime")
            long sunriseTime;

            @SerializedName("sunsetTime")
            long sunsetTime;

            @SerializedName("moonPhase")
            float moonPhase;

            @SerializedName("precipIntensity")
            float precipIntensity;

            @SerializedName("precipIntensityMax")
            float precipIntensityMax;

            @SerializedName("precipProbability")
            float precipProbability;

//            @SerializedName("precipIntensityMaxTime")
//            int precipIntensityMaxTime;

//            @SerializedName("precipType")
//            String precipType;

//           @SerializedName("temperatureHigh")
//           float temperatureHigh;

//           @SerializedName("temperatureHighTime")
//           int temperatureHighTime;

//           @SerializedName("temperatureLow")
//           float temperatureLow;

//           @SerializedName("temperatureLowTime")
//           int temperatureLowTime;

//           @SerializedName("apparentTemperatureHigh")
//           float apparentTemperatureHigh;

//           @SerializedName("apparentTemperatureHighTime")
//           int apparentTemperatureHighTime;

//           @SerializedName("apparentTemperatureLow")
//           float apparentTemperatureLow;

//           @SerializedName("apparentTemperatureLowTime")
//           int apparentTemperatureLowTime;

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

//            @SerializedName("windGustTime")
//            int windGustTime;

            @SerializedName("windBearing")
            int windBearing;

            @SerializedName("cloudCover")
            float cloudCover;

            @SerializedName("uvIndex")
            float uvIndex;

//            @SerializedName("uvIndexTime")
//            int uvIndexTime;

//            @SerializedName("ozone")
//            float ozone;

            @SerializedName("temperatureMin")
            float temperatureMin;

//            @SerializedName("temperatureMinTime")
//            int temperatureMinTime;

            @SerializedName("temperatureMax")
            float temperatureMax;

//            @SerializedName("temperatureMaxTime")
//            int temperatureMaxTime;

            @SerializedName("apparentTemperatureMin")
            float apparentTemperatureMin;

//            @SerializedName("apparentTemperatureMinTime")
//            int apparentTemperatureMinTime;

            @SerializedName("apparentTemperatureMax")
            float apparentTemperatureMax;

//            @SerializedName("apparentTemperatureMaxTime")
//            int apparentTemperatureMaxTime;


            @SerializedName("visibility")
            float visibility;

        }
    }

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = daily.data.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Daily.Datum daily : this.daily.data) {

            if (count > dayCount) {
                break;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.time * 1000)

                    .setCondition(Util.getIcon(daily.icon),
                            Util.getDailyDescription(context, config.getLanguage(), daily.summary))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperatureMin),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperatureMax),
                            config.getTemperatureUnit().str())

                    .setApparentTemperature(
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.apparentTemperatureMin),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.apparentTemperatureMax),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dewPoint),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity * 100))
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), daily.visibility),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), daily.windSpeed),
                            daily.windBearing,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.cloudCover * 100))

                    .setUvIndex(daily.uvIndex)

                    .setPrecipitation(
                            AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), daily.precipIntensity) * 24,
                            Math.round(daily.precipProbability * 100),
                            config.getPrecipitationAmountUnit().str())

                    .setSunAstronomy(daily.sunriseTime * 1000, daily.sunsetTime * 1000)
                    .setMoonPhase(daily.moonPhase)

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
