package ir.mtapps.weatherlib.provider.visualcrossing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

public class AstronomyModel {
    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("days")
    public List<Day> days = null;

    public static class Day {

        @SerializedName("sunriseEpoch")
        public long sunriseEpoch;

        @SerializedName("sunsetEpoch")
        public long sunsetEpoch;

        @SerializedName("moonphase")
        public float moonphase;

    }

    // --------------------------------------------------------------------------------------------

    Astronomy createTodayAstronomyModel() {

        Day today = days.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(today.sunriseEpoch * 1000, today.sunsetEpoch * 1000)
                .setMoonPhase(today.moonphase)

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
