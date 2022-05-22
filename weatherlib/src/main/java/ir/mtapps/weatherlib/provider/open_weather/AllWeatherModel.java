package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

public class AllWeatherModel {

    @SerializedName("lat")
    @Expose
    float lat;

    @SerializedName("lon")
    @Expose
    float lon;

    @SerializedName("timezone")
    @Expose
    String timezone;

    @SerializedName("timezone_offset")
    @Expose
    int timezoneOffset;

    @SerializedName("current")
    @Expose
    Current current;

    @SerializedName("hourly")
    @Expose
    List<Hourly> hourly = null;

    @SerializedName("daily")
    @Expose
    List<Daily> daily = null;

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

        @SerializedName("wind_gust")
        @Expose
        float windGust;

        @SerializedName("weather")
        @Expose
        List<Weather> weather = null;

        @SerializedName("rain")
        Rain rain;

        @SerializedName("snow")
        Snow snow;
    }

    public static class Daily {

        @SerializedName("dt")
        @Expose
        long dt;

        @SerializedName("sunrise")
        @Expose
        long sunrise;

        @SerializedName("sunset")
        @Expose
        long sunset;

        @SerializedName("moonrise")
        @Expose
        long moonrise;

        @SerializedName("moonset")
        @Expose
        long moonset;

        @SerializedName("moon_phase")
        @Expose
        float moonPhase;

        @SerializedName("temp")
        @Expose
        Temp temp;

        @SerializedName("feels_like")
        @Expose
        FeelsLike feelsLike;

        @SerializedName("pressure")
        @Expose
        float pressure;

        @SerializedName("humidity")
        @Expose
        float humidity;

        @SerializedName("dew_point")
        @Expose
        float dewPoint;

        @SerializedName("wind_speed")
        @Expose
        float windSpeed;

        @SerializedName("wind_deg")
        @Expose
        float windDeg;

        @SerializedName("wind_gust")
        @Expose
        float windGust;

        @SerializedName("rain")
        @Expose
        float rain;

        @SerializedName("snow")
        @Expose
        float snow;

        @SerializedName("weather")
        @Expose
        List<Weather> weather = null;

        @SerializedName("clouds")
        @Expose
        float clouds;

        @SerializedName("pop")
        @Expose
        float pop;

        @SerializedName("uvi")
        @Expose
        float uvi;
    }

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

    static class Temp {

        @SerializedName("day")
        @Expose
        float day;

        @SerializedName("min")
        @Expose
        float min;

        @SerializedName("max")
        @Expose
        float max;

        @SerializedName("night")
        @Expose
        float night;

        @SerializedName("eve")
        @Expose
        float eve;

        @SerializedName("morn")
        @Expose
        float morn;
    }

    static class FeelsLike {

        @SerializedName("day")
        @Expose
        float day;

        @SerializedName("night")
        @Expose
        float night;

        @SerializedName("eve")
        @Expose
        float eve;

        @SerializedName("morn")
        @Expose
        float morn;

        float getMin() {
            float val = day;
            val = Math.min(val, night);
            val = Math.min(val, eve);
            val = Math.min(val, morn);
            return val;
        }

        float getMax() {
            float val = day;
            val = Math.max(val, night);
            val = Math.max(val, eve);
            val = Math.max(val, morn);
            return val;
        }
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

    List<DailyWeather> createDailyModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = daily.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Daily daily : daily) {

            if (count > dayCount) {
                break;
            }

            // Precipitation
            float precipitation = 0;
            if (daily.snow > 0) {
                precipitation = daily.snow;
            } else if (daily.rain > 0) {
                precipitation = daily.rain;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.dt * 1000)

                    .setSunAstronomy(daily.sunrise * 1000, daily.sunset * 1000)
                    .setMoonAstronomy(daily.moonrise * 1000, daily.moonset * 1000)
                    .setMoonPhase(daily.moonPhase)

                    .setCondition(Util.getIcon(daily.weather.get(0).id, true),
                            Util.getDescription(context, config.getLanguage(),
                                    daily.weather.get(0).description,
                                    daily.weather.get(0).id))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.min),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temp.max),
                            config.getTemperatureUnit().str())

                    .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelsLike.getMin()),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelsLike.getMax()),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dewPoint),
                            config.getTemperatureUnit().str())

                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity))

                    .setWind(SPEED.convert(SPEED.m_s, config.getSpeedUnit(), daily.windSpeed),
                            daily.windDeg,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.clouds))

                    .setUvIndex(daily.uvi)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                            Math.round(daily.pop),
                            config.getPrecipitationAmountUnit().str())

                    .build());

            count += 1;

        }

        return weatherList;
    }

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

    Astronomy createTodayAstronomyModel() {

        Daily currentDay = daily.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(currentDay.sunrise * 1000, currentDay.sunset * 1000)
                .setMoonAstronomy(currentDay.moonrise * 1000, currentDay.moonset * 1000)
                .setMoonPhase(currentDay.moonPhase)

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
