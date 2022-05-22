package ir.mtapps.weatherlib.provider.open_weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

class AstronomyModel {

    @SerializedName("lat")
    @Expose
    float lat;

    @SerializedName("lon")
    @Expose
    float lon;

    @SerializedName("daily")
    @Expose
    List<Daily> daily = null;

    public static class Daily {

        @SerializedName("sunrise")
        @Expose
        long sunrise;

        @SerializedName("sunset")
        @Expose
        long sunset;

        @SerializedName("moonrise")
        @Expose
        long moonrise;

        @SerializedName("moonset")
        @Expose
        long moonset;

        @SerializedName("moon_phase")
        @Expose
        float moonPhase;
    }

    // ---------------------------------------------------------------------------------------------

    Astronomy createTodayAstronomyModel() {

        Daily currentDay = daily.get(0);

        return new Astronomy.Builder()

                .setSunAstronomy(currentDay.sunrise * 1000, currentDay.sunset * 1000)
                .setMoonAstronomy(currentDay.moonrise * 1000, currentDay.moonset * 1000)
                .setMoonPhase(currentDay.moonPhase)

                .build();

    }

    City getCity() {

        return new City.Builder()
//                .setName(name)
//                .setCountry(sys.country)
                .setCoordinate(lat, lon)
                .build();

    }

}
