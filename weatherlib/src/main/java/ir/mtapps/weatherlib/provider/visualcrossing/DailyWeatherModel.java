package ir.mtapps.weatherlib.provider.visualcrossing;

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

public class DailyWeatherModel {

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("days")
    public List<Day> days = null;

    public static class Day {

        @SerializedName("datetimeEpoch")
        public long datetimeEpoch;

        @SerializedName("tempmax")
        public float tempmax;

        @SerializedName("tempmin")
        public float tempmin;

        @SerializedName("feelslikemax")
        public float feelslikemax;

        @SerializedName("feelslikemin")
        public float feelslikemin;

        @SerializedName("dew")
        public float dew;

        @SerializedName("humidity")
        public float humidity;

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

        @SerializedName("cloudcover")
        public float cloudcover;

        @SerializedName("visibility")
        public float visibility;

        @SerializedName("uvindex")
        public float uvindex;

        @SerializedName("sunriseEpoch")
        public long sunriseEpoch;

        @SerializedName("sunsetEpoch")
        public long sunsetEpoch;

        @SerializedName("moonphase")
        public float moonphase;

        @SerializedName("conditions")
        public String conditions;

        @SerializedName("description")
        public String description;

        @SerializedName("icon")
        public String icon;

    }

    // --------------------------------------------------------------------------------------------

    List<DailyWeather> createDailyModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = days.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (Day daily : days) {

            if (count > dayCount) {
                break;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.datetimeEpoch * 1000)

                    .setCondition(Util.getIcon(daily.icon),
                            Util.getDailyDescription(context, config.getLanguage(), daily.conditions, daily.description))

                    .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.tempmin),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.tempmax),
                            config.getTemperatureUnit().str())

                    .setApparentTemperature(
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelslikemin),
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.feelslikemax),
                            config.getTemperatureUnit().str())

                    .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.dew),
                            config.getTemperatureUnit().str())
                    .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), daily.pressure),
                            config.getPressureUnit().str())
                    .setHumidity(Math.round(daily.humidity))
                    .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), daily.visibility),
                            config.getVisibilityUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), daily.windspeed),
                            daily.winddir,
                            config.getSpeedUnit().str())

                    .setCloudCover(Math.round(daily.cloudcover))

                    .setUvIndex(daily.uvindex)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(),
                                    Math.max(daily.precip, daily.snow)),
                            Math.round(daily.precipprob * 100),
                            config.getPrecipitationAmountUnit().str())

                    .setSunAstronomy(daily.sunriseEpoch * 1000, daily.sunsetEpoch * 1000)
                    .setMoonPhase(daily.moonphase)

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
