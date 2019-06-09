package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

class AstronomyModel {

    @SerializedName("coord")
    Coord coord;

    @SerializedName("sys")
    Sys sys;

    @SerializedName("name")
    String name;

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

    static class Coord {

        @SerializedName("lon")
        double lon;

        @SerializedName("lat")
        double lat;

    }

    static class Sys {

        @SerializedName("country")
        String country;

        @SerializedName("sunrise")
        long sunrise;

        @SerializedName("sunset")
        long sunset;

    }

    Astronomy createModel() {

        return new Astronomy.Builder()

                .setSunAstronomy(sys.sunrise * 1000, sys.sunset * 1000)

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(name)
                .setCountry(sys.country)
                .setCoordinate(coord.lat, coord.lon)
                .build();

    }

}
