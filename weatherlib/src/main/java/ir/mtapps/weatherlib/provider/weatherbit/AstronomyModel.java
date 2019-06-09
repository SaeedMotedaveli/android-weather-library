package ir.mtapps.weatherlib.provider.weatherbit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

class AstronomyModel {

    @SerializedName("data")
    List<Datum> data = null;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("lon")
    double lon;

    @SerializedName("lat")
    double lat;

    @SerializedName("country_code")
    String countryCode;

    static class Datum {

        @SerializedName("moonrise_ts")
        long moonriseTs;

        @SerializedName("sunset_ts")
        long sunsetTs;

        @SerializedName("moon_phase")
        float moonPhase;

        @SerializedName("sunrise_ts")
        long sunriseTs;

        @SerializedName("moonset_ts")
        long moonsetTs;

    }


    Astronomy createModel() {

        Datum today = data.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(today.sunriseTs * 1000, today.sunsetTs * 1000)
                .setMoonAstronomy(today.moonriseTs * 1000, today.moonsetTs * 1000)
                .setMoonPhase(today.moonPhase / 2f)

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(cityName)
                .setCountry(countryCode)
                .setCoordinate(lat, lon)
                .build();

    }

}
