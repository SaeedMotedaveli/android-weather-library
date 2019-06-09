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
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

class AllWeatherModel {

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

            @SerializedName("astronomy")
            List<Astronomy> astronomy = null;

            @SerializedName("maxtempC")
            float maxtempC;

//            @SerializedName("maxtempF")
//            float maxtempF;

            @SerializedName("mintempC")
            float mintempC;

//            @SerializedName("mintempF")
//            float mintempF;

            @SerializedName("totalSnow_cm")
            float totalSnowCm;

//            @SerializedName("sunHour")
//            float sunHour;

            @SerializedName("uvIndex")
            float uvIndex;

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

                @SerializedName("moon_phase")
                String moonPhase;

                @SerializedName("moon_illumination")
                int moonIllumination;

            }

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

    CurrentWeather createCurrentWeatherModel(Context context, WeatherConfig config) {

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

    Astronomy createTodayAstronomyModel() {

        Data.Weather today = data.weather.get(0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);

        long sunrise = 0;
        long sunset = 0;
        long moonrise = 0;
        long moonset = 0;

        try {

            sunrise = format.parse(today.date + " " + today.astronomy.get(0).sunrise).getTime();
            sunset = format.parse(today.date + " " + today.astronomy.get(0).sunset).getTime();
            moonrise = format.parse(today.date + " " + today.astronomy.get(0).moonrise).getTime();
            moonset = format.parse(today.date + " " + today.astronomy.get(0).moonset).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new Astronomy.Builder()

                .setSunAstronomy(sunrise, sunset)
                .setMoonAstronomy(moonrise, moonset)
                .setMoonPhase(Util.getMoonPhase(today.astronomy.get(0).moonIllumination, today.astronomy.get(0).moonPhase))

                .build();

    }

    List<HourlyWeather> createHourlyModel(Context context, WeatherConfig config) {

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

    List<DailyWeather> createDailyModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = data.weather.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);

        List<DailyWeather> weatherList = new ArrayList<>();

        long sunrise = 0;
        long sunset = 0;
        long moonrise = 0;
        long moonset = 0;
        int count = 1;

        for (Data.Weather daily : this.data.weather) {

            if (count > dayCount) {
                break;
            }

            Data.Weather.Hourly avr24h = daily.hourly.get(0);

            try {

                sunrise = timeFormat.parse(daily.date + " "
                        + daily.astronomy.get(0).sunrise).getTime();

                sunset = timeFormat.parse(daily.date + " "
                        + daily.astronomy.get(0).sunset).getTime();

                if (!daily.astronomy.get(0).moonrise.contentEquals("No moonrise")) {
                    moonrise = timeFormat.parse(daily.date + " "
                            + daily.astronomy.get(0).moonrise).getTime();
                }

                if (!daily.astronomy.get(0).moonset.contentEquals("No moonset")) {
                    moonset = timeFormat.parse(daily.date + " "
                            + daily.astronomy.get(0).moonset).getTime();
                }

                int precipitationProbably = 0;
                if (avr24h.chanceofsnow > 0) {
                    precipitationProbably = avr24h.chanceofsnow;
                } else if (avr24h.chanceofrain > 0) {
                    precipitationProbably = avr24h.chanceofrain;
                }

                weatherList.add(new DailyWeather.Builder()

                        .setDate(format.parse(daily.date).getTime())

                        .setCondition(Util.getIcon(avr24h.weatherCode, true),
                                Util.getDescription(context, config.getLanguage(),
                                        avr24h.weatherCode,
                                        avr24h.weatherDesc.get(0).value))

                        .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.mintempC),
                                TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.maxtempC),
                                config.getTemperatureUnit().str())

                        .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), avr24h.dewPointC),
                                config.getTemperatureUnit().str())
                        .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), avr24h.pressure),
                                config.getPressureUnit().str())
                        .setHumidity(Math.round(avr24h.humidity))
                        .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), avr24h.visibility),
                                config.getVisibilityUnit().str())

                        .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), avr24h.windspeedKmph),
                                avr24h.winddirDegree,
                                config.getSpeedUnit().str())

                        .setCloudCover(Math.round(avr24h.cloudcover))

                        .setUvIndex(daily.uvIndex)

                        .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), avr24h.precipMM),
                                precipitationProbably,
                                config.getPrecipitationAmountUnit().str())

                        .setSunAstronomy(sunrise, sunset)
                        .setMoonAstronomy(moonrise, moonset)
                        .setMoonPhase(daily.astronomy.get(0).moonIllumination / 100f)

                        .build());

            } catch (ParseException e) {
                e.printStackTrace();

                return null;
            }

            count += 1;

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
