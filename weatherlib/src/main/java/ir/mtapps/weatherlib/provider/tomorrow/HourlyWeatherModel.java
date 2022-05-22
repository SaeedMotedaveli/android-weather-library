package ir.mtapps.weatherlib.provider.tomorrow;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class HourlyWeatherModel {

    @SerializedName("data")
    public Data data;

    public static class Data {

        @SerializedName("timelines")
        public List<Timeline> timelines = null;

    }

    public static class Interval {

        @SerializedName("startTime")
        public String startTime;

        @SerializedName("values")
        public Values values;

    }

    public static class Timeline {

//        @SerializedName("timestep")
//        public String timestep;

//        @SerializedName("endTime")
//        public String endTime;

//        @SerializedName("startTime")
//        public String startTime;

        @SerializedName("intervals")
        public List<Interval> intervals = null;

    }

    public static class Values {

        @SerializedName("cloudCover")
        public float cloudCover;

        @SerializedName("dewPoint")
        public float dewPoint;

        @SerializedName("humidity")
        public float humidity;

        @SerializedName("precipitationIntensity")
        public float precipitationIntensity;

        @SerializedName("precipitationProbability")
        public float precipitationProbability;

        @SerializedName("pressureSurfaceLevel")
        public float pressureSurfaceLevel;

        @SerializedName("temperature")
        public float temperature;

        @SerializedName("temperatureApparent")
        public float temperatureApparent;

        @SerializedName("uvIndex")
        public float uvIndex;

        @SerializedName("visibility")
        public float visibility;

        @SerializedName("weatherCode")
        public int weatherCode;

        @SerializedName("windDirection")
        public float windDirection;

        @SerializedName("windSpeed")
        public float windSpeed;

    }

    List<HourlyWeather> createHourlyModel(Context context, WeatherConfig config, double latitude, double longitude) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = data.timelines.get(0).intervals.size();
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        List<HourlyWeather> weatherList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Location location = new Location(latitude, longitude);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getDefault());
        long sunrise = calculator.getOfficialSunriseCalendarForDate(calendar).getTimeInMillis();
        long sunset = calculator.getOfficialSunsetCalendarForDate(calendar).getTimeInMillis();

        int count = 1;
        Values hourly;
        long date;
        boolean isDay;
        int weatherCode;
        String day = data.timelines.get(0).intervals.get(0).startTime.substring(0, 10);

        for (Interval interval : data.timelines.get(0).intervals) {

            if (count > limitNextHours) {
                break;
            }

            hourly = interval.values;

            try {

                date = format.parse(interval.startTime.substring(0, 19)).getTime();
                isDay = date >= sunrise && date < sunset;
                weatherCode = hourly.weatherCode * 10;
                weatherCode += isDay ? 0 : 1;

                if (!interval.startTime.startsWith(day)) {
                    sunrise += 86400000L;
                    sunset += 86400000L;
                    day = interval.startTime.substring(0, 10);
                }

                weatherList.add(new HourlyWeather.Builder()

                        .setDate(date)

                        .setCondition(Util.getIcon(weatherCode),
                                Util.getDescription(context, config.getLanguage(), hourly.weatherCode))

                        .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temperature),
                                config.getTemperatureUnit().str())
                        .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temperatureApparent),
                                config.getTemperatureUnit().str())

                        .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dewPoint),
                                config.getTemperatureUnit().str())
                        .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.pressureSurfaceLevel),
                                config.getPressureUnit().str())
                        .setHumidity(Math.round(hourly.humidity))
                        .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibility),
                                config.getVisibilityUnit().str())

                        .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), hourly.windSpeed),
                                hourly.windDirection,
                                config.getSpeedUnit().str())

                        .setUvIndex(hourly.uvIndex)

                        .setCloudCover(Math.round(hourly.cloudCover))

                        .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), hourly.precipitationIntensity),
                                Math.round(hourly.precipitationProbability),
                                config.getPrecipitationAmountUnit().str())

                        .build());
            } catch (ParseException e) {
                e.printStackTrace();

                return null;
            }

            count += 1;
        }

        return weatherList;
    }

    City getCity(double latitude, double longitude) {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
