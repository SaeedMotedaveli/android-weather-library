package ir.mtapps.weatherlib.provider.accuweather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {

//    @SerializedName("Headline")
//    Headline headline;

    @SerializedName("DailyForecasts")
    List<DailyForecast> dailyForecasts = null;

    static class DailyForecast {

//        @SerializedName("Date")
//        String date;

        @SerializedName("EpochDate")
        long epochDate;

        @SerializedName("Sun")
        Sun sun;

        @SerializedName("Moon")
        Moon moon;

        @SerializedName("Temperature")
        Temperature temperature;

        @SerializedName("RealFeelTemperature")
        RealFeelTemperature realFeelTemperature;

//        @SerializedName("RealFeelTemperatureShade")
//        RealFeelTemperatureShade realFeelTemperatureShade;

//        @SerializedName("HoursOfSun")
//        float hoursOfSun;

//        @SerializedName("DegreeDaySummary")
//        DegreeDaySummary degreeDaySummary;

        @SerializedName("AirAndPollen")
        List<AirAndPollen> airAndPollen = null;

        @SerializedName("Day")
        DayAndNight day;

        @SerializedName("Night")
        DayAndNight night;

//        @SerializedName("Sources")
//        List<String> sources = null;

//        @SerializedName("MobileLink")
//        String mobileLink;

//        @SerializedName("Link")
//        String link;

        static class Sun {

//            @SerializedName("Rise")
//            String rise;

            @SerializedName("EpochRise")
            long epochRise;

//            @SerializedName("Set")
//            String set;

            @SerializedName("EpochSet")
            long epochSet;

        }

        static class Moon {

//            @SerializedName("Rise")
//            String rise;

            @SerializedName("EpochRise")
            long epochRise;

//            @SerializedName("Set")
//            String set;

            @SerializedName("EpochSet")
            long epochSet;

            @SerializedName("Phase")
            String phase;

            @SerializedName("Age")
            int age;

        }

        static class Temperature {

            @SerializedName("Minimum")
            Values minimum;

            @SerializedName("Maximum")
            Values maximum;

        }

        static class RealFeelTemperature {

            @SerializedName("Minimum")
            Values minimum;

            @SerializedName("Maximum")
            Values maximum;

        }

        static class RealFeelTemperatureShade {

            @SerializedName("Minimum")
            Values minimum;

            @SerializedName("Maximum")
            Values maximum;

        }

        static class AirAndPollen {

            @SerializedName("Name")
            String name;

            @SerializedName("Value")
            float value;

            @SerializedName("Category")
            String category;

            @SerializedName("CategoryValue")
            int categoryValue;

            @SerializedName("Type")
            String type;

        }

        static class DayAndNight {

            @SerializedName("Icon")
            int icon;

//            @SerializedName("IconPhrase")
//            String iconPhrase;

            @SerializedName("ShortPhrase")
            String shortPhrase;

//            @SerializedName("LongPhrase")
//            String longPhrase;

            @SerializedName("PrecipitationProbability")
            int precipitationProbability;

//            @SerializedName("ThunderstormProbability")
//            int thunderstormProbability;

            @SerializedName("RainProbability")
            int rainProbability;

            @SerializedName("SnowProbability")
            int snowProbability;

//            @SerializedName("IceProbability")
//            int iceProbability;

            @SerializedName("Wind")
            Wind wind;

//            @SerializedName("WindGust")
//            WindGust windGust;

//            @SerializedName("TotalLiquid")
//            TotalLiquid totalLiquid;

            @SerializedName("Rain")
            Values rain;

            @SerializedName("Snow")
            Values snow;

            @SerializedName("Ice")
            Values ice;

//            @SerializedName("HoursOfPrecipitation")
//            int hoursOfPrecipitation;

//            @SerializedName("HoursOfRain")
//            int hoursOfRain;

//            @SerializedName("HoursOfSnow")
//            int hoursOfSnow;

//            @SerializedName("HoursOfIce")
//            int hoursOfIce;

            @SerializedName("CloudCover")
            int cloudCover;

            static class Wind {

                @SerializedName("Speed")
                Values speed;

                @SerializedName("Direction")
                Direction direction;

                static class Direction {

                    @SerializedName("Degrees")
                    int degrees;

                    @SerializedName("Localized")
                    String localized;

                    @SerializedName("English")
                    String english;

                }

            }
        }

    }

    static class Values {

        @SerializedName("Value")
        float value;

        @SerializedName("Unit")
        String unit;

        @SerializedName("UnitType")
        int unitType;

    }


    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = dailyForecasts.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        List<DailyWeather> weatherList = new ArrayList<>();

        int count = 1;

        for (DailyForecast daily : dailyForecasts) {

            if (count > dayCount) {
                break;
            }

            float moonPhase = daily.moon.age / 29f;
            float uvIndex = 0;
            float precipitation = 0;

            for (DailyForecast.AirAndPollen air : daily.airAndPollen) {
                if (air.name.contentEquals("UVIndex")) {
                    uvIndex = air.value;
                    break;
                }
            }

            if (daily.day.snow.value > 0) {
                precipitation = daily.day.snow.value;
            } else if (daily.day.rain.value > 0) {
                precipitation = daily.day.rain.value;
            }

            weatherList.add(new DailyWeather.Builder()

                    .setDate(daily.epochDate * 1000)

                    .setCondition(Util.getIcon(daily.day.icon),
                            Util.getDescription(context, config.getLanguage(), daily.day.shortPhrase, daily.day.icon))

                    .setTemperature(
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(),
                                    daily.temperature.minimum.value),

                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(),
                                    daily.temperature.maximum.value),

                            config.getTemperatureUnit().str())

                    .setApparentTemperature(
                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(),
                                    daily.realFeelTemperature.minimum.value),

                            TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(),
                                    daily.realFeelTemperature.maximum.value),

                            config.getTemperatureUnit().str())

                    .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), daily.day.wind.speed.value),
                            daily.day.wind.direction.degrees,
                            config.getSpeedUnit().str())

                    .setCloudCover(daily.day.cloudCover)

                    .setUvIndex(uvIndex)

                    .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                            daily.day.precipitationProbability,
                            config.getPrecipitationAmountUnit().str())

                    .setSunAstronomy(daily.sun.epochRise * 1000, daily.sun.epochSet * 1000)
                    .setMoonAstronomy(daily.moon.epochRise * 1000, daily.moon.epochSet * 1000)
                    .setMoonPhase(moonPhase)

                    .build());

            count += 1;

        }

        return weatherList;
    }

    Astronomy createAstronomyModel() {

        DailyForecast today = dailyForecasts.get(0);

        return new Astronomy.Builder()
                .setSunAstronomy(today.sun.epochRise * 1000, today.sun.epochSet * 1000)
                .setMoonAstronomy(today.moon.epochRise * 1000, today.moon.epochSet * 1000)
                .setMoonPhase(today.moon.age / 29f)
                .build();

    }

}
