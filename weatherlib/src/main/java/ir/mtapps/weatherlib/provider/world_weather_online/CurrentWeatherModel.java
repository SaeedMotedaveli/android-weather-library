package ir.mtapps.weatherlib.provider.world_weather_online;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
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

    @SerializedName("data")
    Data data;

    static class Data {

        @SerializedName("error")
        List<Error> error = null;

        @SerializedName("nearest_area")
        List<NearestArea> nearestArea = null;

        @SerializedName("current_condition")
        List<CurrentCondition> currentCondition = null;

        @SerializedName("weather")
        List<Weather> weather = null;

        static class Error {
            @SerializedName("msg")
            String message;
        }

        static class NearestArea {

            @SerializedName("areaName")
            List<AreaName> areaName = null;

            @SerializedName("country")
            List<Country> country = null;

            @SerializedName("region")
            List<Region> region = null;

            @SerializedName("latitude")
            double latitude;

            @SerializedName("longitude")
            double longitude;

//            @SerializedName("population")
//            int population;

//            @SerializedName("weatherUrl")
//            List<WeatherUrl> weatherUrl = null;

            static class AreaName {

                @SerializedName("value")
                String value;

            }

            static class Country {

                @SerializedName("value")
                String value;

            }

            static class Region {

                @SerializedName("value")
                String value;

            }

        }

        static class CurrentCondition {

//            @SerializedName("observation_time")
//            String observationTime;

            @SerializedName("isdaytime")
            String isdaytime;

            @SerializedName("temp_C")
            float tempC;

//            @SerializedName("temp_F")
//            float tempF;

            @SerializedName("weatherCode")
            int weatherCode;

//            @SerializedName("weatherIconUrl")
//            List<WeatherIconUrl> weatherIconUrl = null;

            @SerializedName("weatherDesc")
            List<WeatherDesc> weatherDesc = null;

//            @SerializedName("windspeedMiles")
//            float windspeedMiles;

            @SerializedName("windspeedKmph")
            float windspeedKmph;

            @SerializedName("winddirDegree")
            int winddirDegree;

//            @SerializedName("winddir16Point")
//            String winddir16Point;

            @SerializedName("precipMM")
            float precipMM;

            @SerializedName("humidity")
            int humidity;

            @SerializedName("visibility")
            float visibility;

            @SerializedName("pressure")
            float pressure;

            @SerializedName("cloudcover")
            int cloudcover;

            @SerializedName("FeelsLikeC")
            float feelsLikeC;

//            @SerializedName("FeelsLikeF")
//            float feelsLikeF;

            static class WeatherIconUrl {

                @SerializedName("value")
                String value;

            }

            static class WeatherDesc {

                @SerializedName("value")
                String value;

            }
        }

        static class Weather {

            @SerializedName("date")
            String date;

            @SerializedName("uvIndex")
            float uvIndex;

//            @SerializedName("astronomy")
//            List<Astronomy> astronomy = null;

            @SerializedName("hourly")
            List<Hourly> hourly = null;

            static class Astronomy {

                @SerializedName("sunrise")
                String sunrise;

                @SerializedName("sunset")
                String sunset;

                @SerializedName("moonrise")
                String moonrise;

                @SerializedName("moonset")
                String moonset;

//                @SerializedName("moon_phase")
//                String moonPhase;

                @SerializedName("moon_illumination")
                int moonIllumination;

            }

            static class Hourly {

                @SerializedName("DewPointC")
                float dewPointC;

                @SerializedName("chanceofrain")
                int chanceofrain;

                @SerializedName("chanceofsnow")
                int chanceofsnow;
            }

        }

    }

    CurrentWeather createModel(Context context, WeatherConfig config) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        Data.Weather.Hourly hourly = data.weather.get(0).hourly.get(hour + 1);

        int precipitationProbably = 0;
        if (hourly.chanceofsnow > 0) {
            precipitationProbably = hourly.chanceofsnow;
        } else if (hourly.chanceofrain > 0) {
            precipitationProbably = hourly.chanceofrain;
        }

        boolean isDay = data.currentCondition.get(0).isdaytime.contentEquals("yes");

        Data.CurrentCondition current = data.currentCondition.get(0);

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(current.weatherCode, isDay),
                        Util.getDescription(context,
                                config.getLanguage(),
                                current.weatherCode,
                                current.weatherDesc.get(0).value))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.tempC),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), current.feelsLikeC),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPointC),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), current.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(current.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), current.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), current.windspeedKmph),
                        current.winddirDegree,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(current.cloudcover))

                .setUvIndex(data.weather.get(0).uvIndex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), current.precipMM),
                        precipitationProbably,
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(data.nearestArea.get(0).areaName.get(0).value)
                .setCountry(data.nearestArea.get(0).country.get(0).value)
                .setCoordinate(data.nearestArea.get(0).latitude,
                        data.nearestArea.get(0).longitude)
                .build();

    }

}
