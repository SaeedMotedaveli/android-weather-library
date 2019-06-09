package ir.mtapps.weatherlib.provider.dark_sky;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

class AstronomyModel {

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;

    @SerializedName("daily")
    Daily daily;

    public static class Daily {

        @SerializedName("data")
        public List<Datum> data = null;

        static class Datum {

            @SerializedName("sunriseTime")
            long sunriseTime;

            @SerializedName("sunsetTime")
            long sunsetTime;

            @SerializedName("moonPhase")
            float moonPhase;
        }
    }


    Astronomy createModel() {

        Daily.Datum today = daily.data.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(today.sunriseTime * 1000, today.sunsetTime * 1000)
                .setMoonPhase(today.moonPhase)

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
