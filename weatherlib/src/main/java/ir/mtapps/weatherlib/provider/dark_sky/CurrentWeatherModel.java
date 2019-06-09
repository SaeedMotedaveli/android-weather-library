package ir.mtapps.weatherlib.provider.dark_sky;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;

class CurrentWeatherModel {

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;

//    @SerializedName("timezone")
//    String timezone;

    @SerializedName("currently")
    Currently currently;

    static class Currently {

//        @SerializedName("time")
//        int time;

        @SerializedName("summary")
        String summary;

        @SerializedName("icon")
        String icon;

        @SerializedName("precipIntensity")
        float precipIntensity;

        @SerializedName("precipProbability")
        float precipProbability;

        @SerializedName("precipType")
        String precipType;

        @SerializedName("temperature")
        float temperature;

        @SerializedName("apparentTemperature")
        float apparentTemperature;

        @SerializedName("dewPoint")
        float dewPoint;

        @SerializedName("humidity")
        float humidity;

        @SerializedName("pressure")
        float pressure;

        @SerializedName("windSpeed")
        float windSpeed;

//        @SerializedName("windGust")
//        float windGust;

        @SerializedName("windBearing")
        int windBearing;

        @SerializedName("cloudCover")
        float cloudCover;

        @SerializedName("uvIndex")
        float uvIndex;

//        @SerializedName("ozone")
//        float ozone;

        @SerializedName("visibility")
        float visibility;

    }


    CurrentWeather createModel(Context context, WeatherConfig config) {

        return new CurrentWeather.Builder()

                .setCondition(Util.getIcon(currently.icon),
                        Util.getDescription(context, config.getLanguage(), currently.summary))

                .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.temperature),
                        config.getTemperatureUnit().str())
                .setApparentTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.apparentTemperature),
                        config.getTemperatureUnit().str())

                .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), currently.dewPoint),
                        config.getTemperatureUnit().str())
                .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), currently.pressure),
                        config.getPressureUnit().str())
                .setHumidity(Math.round(currently.humidity * 100))
                .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), currently.visibility),
                        config.getVisibilityUnit().str())

                .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), currently.windSpeed),
                        currently.windBearing,
                        config.getSpeedUnit().str())

                .setCloudCover(Math.round(currently.cloudCover * 100))

                .setUvIndex(currently.uvIndex)

                .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), currently.precipIntensity),
                        Math.round(currently.precipProbability * 100),
                        config.getPrecipitationAmountUnit().str())

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }

}
