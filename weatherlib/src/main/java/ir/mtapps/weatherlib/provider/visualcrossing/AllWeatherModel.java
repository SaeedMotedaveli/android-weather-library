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
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class AllWeatherModel {

//    @SerializedName("queryCost")
//    public int queryCost;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

//    @SerializedName("resolvedAddress")
//    public String resolvedAddress;

//    @SerializedName("address")
//    public String address;

//    @SerializedName("timezone")
//    public String timezone;

//    @SerializedName("tzoffset")
//    public float tzoffset;

//    @SerializedName("description")
//    public String description;

    @SerializedName("days")
    public List<Day> days = null;

//    @SerializedName("alerts")
//    public List<Object> alerts = null;

    @SerializedName("currentConditions")
    public CurrentConditions currentConditions;

    public static class CurrentConditions {

//        @SerializedName("datetime")
//        public String datetime;

//        @SerializedName("datetimeEpoch")
//        public long datetimeEpoch;

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

//        @SerializedName("snowdepth")
//        public float snowdepth;

//        @SerializedName("windgust")
//        public float windgust;

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

//        @SerializedName("solarradiation")
//        public float solarradiation;

//        @SerializedName("solarenergy")
//        public float solarenergy;

        @SerializedName("uvindex")
        public float uvindex;

//        @SerializedName("severerisk")
//        public float severerisk;

        @SerializedName("conditions")
        public String conditions;

        @SerializedName("icon")
        public String icon;

//        @SerializedName("stations")
//        public List<Object> stations = null;

//        @SerializedName("source")
//        public String source;

//        @SerializedName("sunrise")
//        public String sunrise;

        @SerializedName("sunriseEpoch")
        public long sunriseEpoch;

//        @SerializedName("sunset")
//        public String sunset;

        @SerializedName("sunsetEpoch")
        public long sunsetEpoch;

        @SerializedName("moonphase")
        public float moonphase;

    }

    public static class Day {

//        @SerializedName("datetime")
//        public String datetime;

        @SerializedName("datetimeEpoch")
        public long datetimeEpoch;

        @SerializedName("tempmax")
        public float tempmax;

        @SerializedName("tempmin")
        public float tempmin;

//        @SerializedName("temp")
//        public float temp;

        @SerializedName("feelslikemax")
        public float feelslikemax;

        @SerializedName("feelslikemin")
        public float feelslikemin;

//        @SerializedName("feelslike")
//        public float feelslike;

        @SerializedName("dew")
        public float dew;

        @SerializedName("humidity")
        public float humidity;

        @SerializedName("precip")
        public float precip;

        @SerializedName("precipprob")
        public float precipprob;

//        @SerializedName("precipcover")
//        public float precipcover;

        @SerializedName("snow")
        public float snow;

//        @SerializedName("snowdepth")
//        public float snowdepth;

//        @SerializedName("windgust")
//        public float windgust;

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

//        @SerializedName("solarradiation")
//        public float solarradiation;

//        @SerializedName("solarenergy")
//        public float solarenergy;

        @SerializedName("uvindex")
        public float uvindex;

//        @SerializedName("severerisk")
//        public float severerisk;

//        @SerializedName("sunrise")
//        public String sunrise;

        @SerializedName("sunriseEpoch")
        public long sunriseEpoch;

//        @SerializedName("sunset")
//        public String sunset;

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

//        @SerializedName("stations")
//        public List<String> stations = null;

//        @SerializedName("source")
//        public String source;

        @SerializedName("hours")
        public List<Hour> hours = null;

    }

    public static class Hour {

//        @SerializedName("datetime")
//        public String datetime;

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

//        @SerializedName("snowdepth")
//        public float snowdepth;

//        @SerializedName("windgust")
//        public float windgust;

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

//        @SerializedName("solarradiation")
//        public float solarradiation;

        @SerializedName("uvindex")
        public float uvindex;

//        @SerializedName("severerisk")
//        public float severerisk;

        @SerializedName("conditions")
        public String conditions;

        @SerializedName("icon")
        public String icon;

//        @SerializedName("stations")
//        public List<String> stations = null;

//        @SerializedName("source")
//        public String source;

    }

    // --------------------------------------------------------------------------------------------

    CurrentWeather createCurrentWeatherModel(Context context, WeatherConfig config) {

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(currentConditions.icon),
                        Util.getDescription(context, config.getLanguage(), currentConditions.conditions))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.temp),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.feelslike),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currentConditions.dew),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), currentConditions.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(currentConditions.humidity))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), currentConditions.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), currentConditions.windspeed),
                        currentConditions.winddir,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(currentConditions.cloudcover))

                .setUvIndex(currentConditions.uvindex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(),
                                Math.max(currentConditions.precip, currentConditions.snow)),
                        Math.round(currentConditions.precipprob),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    Astronomy createTodayAstronomyModel() {

        Day today = days.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(today.sunriseEpoch * 1000, today.sunsetEpoch * 1000)
                .setMoonPhase(today.moonphase)

                .build();

    }

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
