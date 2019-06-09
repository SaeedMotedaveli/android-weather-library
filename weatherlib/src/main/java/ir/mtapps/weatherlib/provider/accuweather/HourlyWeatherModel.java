package ir.mtapps.weatherlib.provider.accuweather;

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
import ir.mtapps.weatherlib.model.HourlyWeather;

class HourlyWeatherModel {

//    @SerializedName("MobileLink")
//    String mobileLink;

//    @SerializedName("Link")
//    String link;

//    @SerializedName("DateTime")
//    String dateTime;

    @SerializedName("EpochDateTime")
    long epochDateTime;

    @SerializedName("WeatherIcon")
    int weatherIcon;

    @SerializedName("IconPhrase")
    String iconPhrase;

    @SerializedName("IsDaylight")
    boolean isDaylight;

    @SerializedName("Temperature")
    Values temperature;

    @SerializedName("RealFeelTemperature")
    Values realFeelTemperature;

//    @SerializedName("WetBulbTemperature")
//    Values wetBulbTemperature;

    @SerializedName("DewPoint")
    Values dewPoint;

    @SerializedName("Wind")
    Wind wind;

//    @SerializedName("WindGust")
//    WindGust windGust;

    @SerializedName("RelativeHumidity")
    int relativeHumidity;

    @SerializedName("Visibility")
    Values visibility;

//    @SerializedName("Ceiling")
//    Ceiling ceiling;

    @SerializedName("UVIndex")
    float uVIndex;

    @SerializedName("UVIndexText")
    String uVIndexText;

    @SerializedName("PrecipitationProbability")
    int precipitationProbability;

    @SerializedName("RainProbability")
    int rainProbability;

    @SerializedName("SnowProbability")
    int snowProbability;

//    @SerializedName("IceProbability")
//    float iceProbability;

//    @SerializedName("TotalLiquid")
//    TotalLiquid totalLiquid;

    @SerializedName("Rain")
    Values rain;

    @SerializedName("Snow")
    Values snow;

//    @SerializedName("Ice")
//    Ice ice;

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

    static class Values {

        @SerializedName("Value")
        float value;

        @SerializedName("Unit")
        String unit;

        @SerializedName("UnitType")
        int unitType;

    }


    HourlyWeather createModel(Context context, WeatherConfig config) {

        float precipitation = 0;

        if (snow.value > 0) {
            precipitation = snow.value;
        } else if (rain.value > 0) {
            precipitation = rain.value;
        }

        return new HourlyWeather.Builder()

                .setDate(epochDateTime * 1000)

                .setCondition(Util.getIcon(weatherIcon),
                        Util.getDescription(context, config.getLanguage(), iconPhrase, weatherIcon))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), temperature.value),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), realFeelTemperature.value),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), dewPoint.value),
                        config.getTemperatureUnit().str())
                .setHumidity(relativeHumidity)
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), visibility.value),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), wind.speed.value),
                        wind.direction.degrees,
                        config.getSpeedUnit().str())

                .setUvIndex(uVIndex)

                .setCloudCover(cloudCover)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precipitation),
                        precipitationProbability,
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

}
