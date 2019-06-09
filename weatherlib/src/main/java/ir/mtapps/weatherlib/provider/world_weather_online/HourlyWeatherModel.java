package ir.mtapps.weatherlib.provider.world_weather_online;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

class HourlyWeatherModel {

    @SerializedName("data")
    Data data;

    static class Data {

        @SerializedName("error")
        List<Error> error = null;

        @SerializedName("nearest_area")
        List<NearestArea> nearestArea = null;

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

        static class Weather {

            @SerializedName("date")
            String date;

            @SerializedName("uvIndex")
            float uvIndex;

            @SerializedName("hourly")
            List<Hourly> hourly = null;

            static class Hourly {

                @SerializedName("time")
                long time;

                @SerializedName("isdaytime")
                String isdaytime;

                @SerializedName("tempC")
                float tempC;

//                @SerializedName("tempF")
//                float tempF;

//                @SerializedName("windspeedMiles")
//                float windspeedMiles;

                @SerializedName("windspeedKmph")
                float windspeedKmph;

                @SerializedName("winddirDegree")
                int winddirDegree;

//                @SerializedName("winddir16Point")
//                String winddir16Point;

                @SerializedName("weatherCode")
                int weatherCode;

//                @SerializedName("weatherIconUrl")
//                List<WeatherIconUrl> weatherIconUrl = null;

                @SerializedName("weatherDesc")
                List<WeatherDesc> weatherDesc = null;

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

//                @SerializedName("HeatIndexC")
//                float heatIndexC;

//                @SerializedName("HeatIndexF")
//                float heatIndexF;

                @SerializedName("DewPointC")
                float dewPointC;

//                @SerializedName("DewPointF")
//                float dewPointF;

//                @SerializedName("WindChillC")
//                float windChillC;

//                @SerializedName("WindChillF")
//                float windChillF;

//                @SerializedName("WindGustMiles")
//                float windGustMiles;

//                @SerializedName("WindGustKmph")
//                float windGustKmph;

                @SerializedName("FeelsLikeC")
                float feelsLikeC;

//                @SerializedName("FeelsLikeF")
//                float feelsLikeF;

                @SerializedName("chanceofrain")
                int chanceofrain;

//                @SerializedName("chanceofremdry")
//                int chanceofremdry;

//                @SerializedName("chanceofwindy")
//                int chanceofwindy;

//                @SerializedName("chanceofovercast")
//                int chanceofovercast;

//                @SerializedName("chanceofsunshine")
//                int chanceofsunshine;

//                @SerializedName("chanceoffrost")
//                int chanceoffrost;

//                @SerializedName("chanceofhightemp")
//                int chanceofhightemp;

//                @SerializedName("chanceoffog")
//                int chanceoffog;

                @SerializedName("chanceofsnow")
                int chanceofsnow;

//                @SerializedName("chanceofthunder")
//                int chanceofthunder;


                static class WeatherDesc {

                    @SerializedName("value")
                    String value;

                }

            }

        }
    }

    List<HourlyWeather> createModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = data.weather.size() * 24;
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        int current = calendar.get(Calendar.HOUR_OF_DAY) * 100 + calendar.get(Calendar.MINUTE);

        List<HourlyWeather> weatherList = new ArrayList<>();

        int precipitationProbably;
        int count = 1;
        int dayNum = 1;

        for (Data.Weather day : this.data.weather) {

            for (Data.Weather.Hourly hourly : day.hourly) {

                if (hourly.time == 24) {
                    continue;
                }

                if (dayNum == 1 && hourly.time <= current) {
                    continue;
                }

                if (count > limitNextHours) {
                    break;
                }

                try {

                    precipitationProbably = 0;
                    if (hourly.chanceofsnow > 0) {
                        precipitationProbably = hourly.chanceofsnow;
                    } else if (hourly.chanceofrain > 0) {
                        precipitationProbably = hourly.chanceofrain;
                    }

                    weatherList.add(new HourlyWeather.Builder()

                            .setDate(format.parse(day.date + " " + (hourly.time / 100) + ":" + (hourly.time % 100)).getTime())

                            .setCondition(Util.getIcon(hourly.weatherCode, hourly.isdaytime.contentEquals("yes")),
                                    Util.getDescription(context, config.getLanguage(),
                                            hourly.weatherCode, hourly.weatherDesc.get(0).value))

                            .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.tempC),
                                    config.getTemperatureUnit().str())
                            .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.feelsLikeC),
                                    config.getTemperatureUnit().str())

                            .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPointC),
                                    config.getTemperatureUnit().str())
                            .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.pressure),
                                    config.getPressureUnit().str())
                            .setHumidity(hourly.humidity)
                            .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibility),
                                    config.getVisibilityUnit().str())

                            .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), hourly.windspeedKmph),
                                    hourly.winddirDegree,
                                    config.getSpeedUnit().str())

                            .setUvIndex(day.uvIndex)

                            .setCloudCover(hourly.cloudcover)

                            .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.precipMM),
                                    precipitationProbably,
                                    config.getPrecipitationAmountUnit().str())

                            .build());
                } catch (ParseException e) {
                    e.printStackTrace();

                    return null;
                }

                count += 1;

            }

            dayNum += 1;
        }

        return weatherList;
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
