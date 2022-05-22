package ir.mtapps.weatherlib.provider.tomorrow;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

public class AstronomyModel {

    @SerializedName("data")
    public Data data;

    public static class Data {

        @SerializedName("timelines")
        public List<Timeline> timelines = null;

    }

    public static class Interval {

        @SerializedName("values")
        public Values values;

    }

    public static class Timeline {

        @SerializedName("intervals")
        public List<Interval> intervals = null;

    }

    public static class Values {

        @SerializedName("moonPhase")
        public int moonPhase;

        @SerializedName("sunriseTime")
        public String sunriseTime;

        @SerializedName("sunsetTime")
        public String sunsetTime;

    }

    Astronomy createTodayAstronomyModel() {

        Values today = data.timelines.get(0).intervals.get(0).values;

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);

        TimeZone timeZone = TimeZone.getDefault();
        int dts = timeZone.getRawOffset() + timeZone.getDSTSavings();

        long sunrise = 0;
        long sunset = 0;

        try {
            sunrise = sf.parse(today.sunriseTime.substring(0, 19)).getTime() + dts;
            sunset = sf.parse(today.sunsetTime.substring(0, 19)).getTime() + dts;
        } catch (ParseException ignored) {}

        return new Astronomy.Builder()
                .setSunAstronomy(sunrise, sunset)
                .setMoonPhase(today.moonPhase)

                .build();

    }

    City getCity(double latitude, double longitude) {

        return new City.Builder()
                .setName(null)
                .setCountry(null)
                .setCoordinate(latitude, longitude)
                .build();

    }
}
