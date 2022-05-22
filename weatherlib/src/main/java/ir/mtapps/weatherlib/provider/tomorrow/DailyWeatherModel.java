package ir.mtapps.weatherlib.provider.tomorrow;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

public class DailyWeatherModel {

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

        @SerializedName("timestep")
        public String timestep;

        @SerializedName("endTime")
        public String endTime;

        @SerializedName("startTime")
        public String startTime;

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

        @SerializedName("moonPhase")
        public int moonPhase;

        @SerializedName("precipitationIntensity")
        public float precipitationIntensity;

        @SerializedName("precipitationProbability")
        public float precipitationProbability;

        @SerializedName("pressureSurfaceLevel")
        public float pressureSurfaceLevel;

        @SerializedName("sunriseTime")
        public String sunriseTime;

        @SerializedName("sunsetTime")
        public String sunsetTime;

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

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = data.timelines.get(0).intervals.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        TimeZone timeZone = TimeZone.getDefault();
        int dts = timeZone.getRawOffset() + timeZone.getDSTSavings();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;
        Values daily;

        for (Interval interval : data.timelines.get(0).intervals) {

            if (count > dayCount) {
                break;
            }

            daily = interval.values;

            try {

                weatherList.add(new DailyWeather.Builder()

                        .setDate(format.parse(interval.startTime.substring(0, 10)).getTime())

                        .setCondition(Util.getIcon(daily.weatherCode),
                                Util.getDescription(context, config.getLanguage(), daily.weatherCode))

                        .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperature),
                                TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperature),
                                config.getTemperatureUnit().str())

                        .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperatureApparent),
                                TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.temperatureApparent),
                                config.getTemperatureUnit().str())

                        .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dewPoint),
                                config.getTemperatureUnit().str())
                        .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressureSurfaceLevel),
                                config.getPressureUnit().str())
                        .setHumidity(Math.round(daily.humidity))
                        .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), daily.visibility),
                                config.getVisibilityUnit().str())

                        .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), daily.windSpeed),
                                daily.windDirection,
                                config.getSpeedUnit().str())

                        .setCloudCover(Math.round(daily.cloudCover))

                        .setUvIndex(daily.uvIndex)

                        .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), daily.precipitationIntensity),
                                Math.round(daily.precipitationProbability),
                                config.getPrecipitationAmountUnit().str())

                        .setSunAstronomy(sf.parse(daily.sunriseTime.substring(0, 19)).getTime() + dts,
                                sf.parse(daily.sunsetTime.substring(0, 19)).getTime() + dts)
                        .setMoonPhase(daily.moonPhase)

                        .build());

            } catch (ParseException e) {
                e.printStackTrace();

                return null;
            }

            count += 1;

        }

        return weatherList;
    }

    Astronomy createTodayAstronomyModel() {

        Values today = data.timelines.get(0).intervals.get(0).values;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

        TimeZone timeZone = TimeZone.getDefault();
        int dts = timeZone.getRawOffset() + timeZone.getDSTSavings();

        long sunrise = 0;
        long sunset = 0;

        try {
            sunrise = sf.parse(today.sunriseTime.substring(0, 19)).getTime() + dts;
            sunset = sf.parse(today.sunsetTime.substring(0, 19)).getTime() + dts;
        } catch (ParseException ignored) {}

        return new Astronomy.Builder()
                .setSunAstronomy(sunrise, sunset)
                .setMoonPhase(today.moonPhase)

                .build();

    }

    City getCity(double latitude, double longitude) {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
