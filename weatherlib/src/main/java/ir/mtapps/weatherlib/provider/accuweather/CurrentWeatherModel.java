package ir.mtapps.weatherlib.provider.accuweather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.CurrentWeather;

class CurrentWeatherModel {

//    @SerializedName("LocalObservationDateTime")
//    String localObservationDateTime;

//    @SerializedName("EpochTime")
//    int epochTime;

    @SerializedName("WeatherText")
    String weatherText;

    @SerializedName("WeatherIcon")
    int weatherIcon;

    @SerializedName("HasPrecipitation")
    boolean hasPrecipitation;

//    @SerializedName("PrecipitationType")
//    String precipitationType;

    @SerializedName("IsDayTime")
    boolean isDayTime;

    @SerializedName("Temperature")
    Temperature temperature;

    @SerializedName("RealFeelTemperature")
    RealFeelTemperature realFeelTemperature;

//    @SerializedName("RealFeelTemperatureShade")
//    RealFeelTemperatureShade realFeelTemperatureShade;

    @SerializedName("RelativeHumidity")
    int relativeHumidity;

    @SerializedName("DewPoint")
    DewPoint dewPoint;

    @SerializedName("Wind")
    Wind wind;

//    @SerializedName("WindGust")
//    WindGust windGust;

    @SerializedName("UVIndex")
    float uvIndex;

//    @SerializedName("UVIndexText")
//    String uvIndexText;

    @SerializedName("Visibility")
    Visibility visibility;

//    @SerializedName("ObstructionsToVisibility")
//    String obstructionsToVisibility;

    @SerializedName("CloudCover")
    int cloudCover;

//    @SerializedName("Ceiling")
//    Ceiling ceiling;

    @SerializedName("Pressure")
    Pressure pressure;

//    @SerializedName("PressureTendency")
//    PressureTendency pressureTendency;

//    @SerializedName("Past24HourTemperatureDeparture")
//    Past24HourTemperatureDeparture past24HourTemperatureDeparture;

    @SerializedName("ApparentTemperature")
    ApparentTemperature apparentTemperature;

//    @SerializedName("WindChillTemperature")
//    WindChillTemperature windChillTemperature;

//    @SerializedName("WetBulbTemperature")
//    WetBulbTemperature wetBulbTemperature;

    @SerializedName("Precip1hr")
    Precip1hr precip1hr;

    @SerializedName("PrecipitationSummary")
    PrecipitationSummary precipitationSummary;


    static class Temperature {

        @SerializedName("Metric")
        Values metric;

    }

    static class RealFeelTemperature {

        @SerializedName("Metric")
        Values metric;

    }

    static class RealFeelTemperatureShade {

        @SerializedName("Metric")
        Values metric;

    }

    static class DewPoint {

        @SerializedName("Metric")
        Values metric;

    }

    static class Wind {

        @SerializedName("Direction")
        Direction direction;

        @SerializedName("Speed")
        Speed speed;

        class Direction {

            @SerializedName("Degrees")
            int degrees;

            @SerializedName("Localized")
            String localized;

            @SerializedName("English")
            String english;

        }

        static class Speed {

            @SerializedName("Metric")
            Values metric;
        }

    }

    static class WindGust {

        @SerializedName("Speed")
        Values speed;

    }

    static class Visibility {

        @SerializedName("Metric")
        Values metric;

    }

    static class Ceiling {

        @SerializedName("Metric")
        Values metric;

    }

    static class Pressure {

        @SerializedName("Metric")
        Values metric;

    }

    static class PressureTendency {

        @SerializedName("LocalizedText")
        String localizedText;

        @SerializedName("Code")
        String code;

    }

    static class Past24HourTemperatureDeparture {

        @SerializedName("Metric")
        Values metric;

    }

    static class ApparentTemperature {

        @SerializedName("Metric")
        Values metric;

    }

    static class WindChillTemperature {

        @SerializedName("Metric")
        Values metric;

    }

    static class WetBulbTemperature {

        @SerializedName("Metric")
        Values metric;

    }

    static class Precip1hr {

        @SerializedName("Metric")
        Values metric;

    }

    static class PrecipitationSummary {

        @SerializedName("Precipitation")
        Precipitation precipitation;

        @SerializedName("PastHour")
        PastHour pastHour;

        static class Precipitation {

            @SerializedName("Metric")
            Values metric;

        }

        static class PastHour {

            @SerializedName("Metric")
            Values metric;

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


    CurrentWeather createModel(Context context, WeatherConfig config) {

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(weatherIcon),
                        Util.getDescription(context, config.getLanguage(), weatherText, weatherIcon))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), temperature.metric.value),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), realFeelTemperature.metric.value),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), dewPoint.metric.value),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), pressure.metric.value),
                        config.getPressureUnit().str())
                .setHumidity(relativeHumidity)
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), visibility.metric.value),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), wind.speed.metric.value),
                        wind.direction.degrees,
                        config.getSpeedUnit().str())

                .setCloudCover(cloudCover)

                .setUvIndex(uvIndex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), precip1hr.metric.value),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }
}
