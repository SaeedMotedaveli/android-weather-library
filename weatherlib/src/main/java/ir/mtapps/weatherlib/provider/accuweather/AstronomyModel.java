package ir.mtapps.weatherlib.provider.accuweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;

class AstronomyModel {

    @SerializedName("DailyForecasts")
    List<DailyForecast> dailyForecasts = null;

    static class DailyForecast {

        @SerializedName("Sun")
        Sun sun;

        @SerializedName("Moon")
        Moon moon;

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
    }


    Astronomy createModel() {

        DailyForecast today = dailyForecasts.get(0);

        return new Astronomy.Builder()
                .setSunAstronomy(today.sun.epochRise * 1000, today.sun.epochSet * 1000)
                .setMoonAstronomy(today.moon.epochRise * 1000, today.moon.epochSet * 1000)
                .setMoonPhase(today.moon.age / 29f)
                .build();

    }

}
