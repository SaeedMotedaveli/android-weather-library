package ir.mtapps.weatherlib.provider.visualcrossing;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class HourlyWeatherModel {

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("days")
    public List<Day> days = null;

    public static class Day {

        @SerializedName("hours")
        public List<Hour> hours = null;

    }

    public static class Hour {

        @SerializedName("datetimeEpoch")
        public long datetimeEpoch;

        @SerializedName("temp")
        public float temp;

        @SerializedName("feelslike")
        public float feelslike;

        @SerializedName("humidity")
        public float humidity;

        @SerializedName("dew")
        public float dew;

        @SerializedName("precip")
        public float precip;

        @SerializedName("precipprob")
        public float precipprob;

        @SerializedName("snow")
        public float snow;

        @SerializedName("windspeed")
        public float windspeed;

        @SerializedName("winddir")
        public float winddir;

        @SerializedName("pressure")
        public float pressure;

        @SerializedName("visibility")
        public float visibility;

        @SerializedName("cloudcover")
        public float cloudcover;

        @SerializedName("uvindex")
        public float uvindex;

        @SerializedName("conditions")
        public String conditions;

        @SerializedName("icon")
        public String icon;

    }

    // --------------------------------------------------------------------------------------------

    List<HourlyWeather> createHourlyModel(Context context, WeatherConfig config) {

        int limitNextHours = config.getLimitNextHours();

        if (limitNextHours < 0) {
            limitNextHours = days.size() * 24;
        } else if (limitNextHours == 0) {
            limitNextHours = 1;
        } else {
            limitNextHours += 1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long current = calendar.getTimeInMillis() / 1000L;

        List<HourlyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Day daily : days) {
            for (Hour hourly : daily.hours) {

                if (hourly.datetimeEpoch < current) {
                    continue;
                }

                if (count > limitNextHours) {
                    break;
                }

                weatherList.add(new HourlyWeather.Builder()

                        .setDate(hourly.datetimeEpoch * 1000)

                        .setCondition(Util.getIcon(hourly.icon),
                                (Util.getDescription(context, config.getLanguage(), hourly.conditions)))

                        .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.temp),
                                config.getTemperatureUnit().str())
                        .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.feelslike),
                                config.getTemperatureUnit().str())

                        .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), hourly.dew),
                                config.getTemperatureUnit().str())
                        .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), hourly.pressure),
                                config.getPressureUnit().str())
                        .setHumidity(Math.round(hourly.humidity))
                        .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), hourly.visibility),
                                config.getVisibilityUnit().str())

                        .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), hourly.windspeed),
                                hourly.winddir,
                                config.getSpeedUnit().str())

                        .setUvIndex(hourly.uvindex)

                        .setCloudCover(Math.round(hourly.cloudcover))

                        .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(),
                                        Math.max(hourly.precip, hourly.snow)),
                                Math.round(hourly.precipprob),
                                config.getPrecipitationAmountUnit().str())

                        .build());

                count += 1;

            }
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
