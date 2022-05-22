package ir.mtapps.weatherlib.provider.aeris_weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

public class AstronomyModel {

    @SerializedName("success")
    public boolean success;

    @SerializedName("response")
    public List<ResponseItem> response;

    @SerializedName("error")
    public String error;

    public static class Loc {

        @SerializedName("long")
        public double longitude;

        @SerializedName("lat")
        public double latitude;
    }

    public static class ResponseItem {

        @SerializedName("loc")
        private Loc loc;

        @SerializedName("periods")
        public List<PeriodsItem> periods;
        
    }

    public static class PeriodsItem {

        @SerializedName("sunrise")
        public long sunrise;

        @SerializedName("sunset")
        public long sunset;
    }


    Astronomy createTodayAstronomyModel() {

        PeriodsItem today = response.get(0).periods.get(0);

        return new Astronomy.Builder()
                .setSunAstronomy(today.sunrise * 1000, today.sunset * 1000)
                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(response.get(0).loc.latitude, response.get(0).loc.longitude)
                .build();

    }
}
