package ir.mtapps.weatherlib.provider.world_weather_online;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

class AstronomyModel {

    @SerializedName("data")
    Data data;

    static class Data {

        @SerializedName("nearest_area")
        List<NearestArea> nearestArea = null;

        @SerializedName("weather")
        List<Weather> weather = null;

        static class NearestArea {

            @SerializedName("areaName")
            List<AreaName> areaName = null;

            @SerializedName("country")
            List<Country> country = null;

            @SerializedName("region")
            List<Region> region = null;

            @SerializedName("latitude")
            double latitude;

            @SerializedName("longitude")
            double longitude;

//            @SerializedName("population")
//            int population;

//            @SerializedName("weatherUrl")
//            List<WeatherUrl> weatherUrl = null;

            static class AreaName {

                @SerializedName("value")
                String value;

            }

            static class Country {

                @SerializedName("value")
                String value;

            }

            static class Region {

                @SerializedName("value")
                String value;

            }

        }

        static class Weather {

            @SerializedName("date")
            String date;

            @SerializedName("astronomy")
            List<Astronomy> astronomy = null;

            static class Astronomy {

                @SerializedName("sunrise")
                String sunrise;

                @SerializedName("sunset")
                String sunset;

                @SerializedName("moonrise")
                String moonrise;

                @SerializedName("moonset")
                String moonset;

                @SerializedName("moon_phase")
                String moonPhase;

                @SerializedName("moon_illumination")
                int moonIllumination;

            }

        }

    }

    Astronomy createModel() {

        Data.Weather today = data.weather.get(0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);

        long sunrise = 0;
        long sunset = 0;
        long moonrise = 0;
        long moonset = 0;

        try {

            sunrise = format.parse(today.date + " " + today.astronomy.get(0).sunrise).getTime();
            sunset = format.parse(today.date + " " + today.astronomy.get(0).sunset).getTime();
            moonrise = format.parse(today.date + " " + today.astronomy.get(0).moonrise).getTime();
            moonset = format.parse(today.date + " " + today.astronomy.get(0).moonset).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return new Astronomy.Builder()

                .setSunAstronomy(sunrise, sunset)
                .setMoonAstronomy(moonrise, moonset)
                .setMoonPhase(Util.getMoonPhase(today.astronomy.get(0).moonIllumination, today.astronomy.get(0).moonPhase))

                .build();

    }

    City getCity() {

        return new City.Builder()
                .setName(data.nearestArea.get(0).areaName.get(0).value)
                .setCountry(data.nearestArea.get(0).country.get(0).value)
                .setCoordinate(data.nearestArea.get(0).latitude,
                        data.nearestArea.get(0).longitude)
                .build();

    }

}
