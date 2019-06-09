package ir.mtapps.weatherlib.provider.world_weather_online;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.enums.AMOUNT;
import ir.mtapps.weatherlib.enums.LENGTH;
import ir.mtapps.weatherlib.enums.PRESSURE;
import ir.mtapps.weatherlib.enums.SPEED;
import ir.mtapps.weatherlib.enums.TEMPERATURE;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

class DailyWeatherModel {

    @SerializedName("data")
    Data data;

    static class Data {

        @SerializedName("error")
        List<Error> error = null;

        @SerializedName("nearest_area")
        List<NearestArea> nearestArea = null;

        @SerializedName("weather")
        List<Weather> weather = null;

        static class Error {
            @SerializedName("msg")
            String message;
        }

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
            
            @SerializedName("maxtempC")
            float maxtempC;
            
//            @SerializedName("maxtempF")
//            float maxtempF;
            
            @SerializedName("mintempC")
            float mintempC;
            
//            @SerializedName("mintempF")
//            float mintempF;
            
            @SerializedName("totalSnow_cm")
            float totalSnowCm;
            
//            @SerializedName("sunHour")
//            float sunHour;
            
            @SerializedName("uvIndex")
            float uvIndex;
            
            @SerializedName("hourly")
            List<Hourly> hourly = null;

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

            static class Hourly {
                
//                @SerializedName("windspeedMiles")
//                float windspeedMiles;
                
                @SerializedName("windspeedKmph")
                float windspeedKmph;
                
                @SerializedName("winddirDegree")
                int winddirDegree;
                
//                @SerializedName("winddir16Point")
//                String winddir16Point;
                
                @SerializedName("weatherCode")
                int weatherCode;
                
//                @SerializedName("weatherIconUrl")
//                List<WeatherIconUrl> weatherIconUrl = null;
                
                @SerializedName("weatherDesc")
                List<WeatherDesc> weatherDesc = null;
                
                @SerializedName("precipMM")
                float precipMM;
                
                @SerializedName("humidity")
                int humidity;
                
                @SerializedName("visibility")
                float visibility;
                
                @SerializedName("pressure")
                float pressure;
                
                @SerializedName("cloudcover")
                int cloudcover;
                
//                @SerializedName("HeatIndexC")
//                float heatIndexC;
                
//                @SerializedName("HeatIndexF")
//                float heatIndexF;
                
                @SerializedName("DewPointC")
                float dewPointC;
                
//                @SerializedName("DewPointF")
//                float dewPointF;
                
//                @SerializedName("WindChillC")
//                float windChillC;
                
//                @SerializedName("WindChillF")
//                float windChillF;
                
//                @SerializedName("WindGustMiles")
//                float windGustMiles;
                
//                @SerializedName("WindGustKmph")
//                float windGustKmph;
                
//                @SerializedName("FeelsLikeC")
//                float feelsLikeC;
                
//                @SerializedName("FeelsLikeF")
//                float feelsLikeF;
                
                @SerializedName("chanceofrain")
                int chanceofrain;
                
//                @SerializedName("chanceofremdry")
//                int chanceofremdry;
                
//                @SerializedName("chanceofwindy")
//                int chanceofwindy;
                
//                @SerializedName("chanceofovercast")
//                int chanceofovercast;
                
//                @SerializedName("chanceofsunshine")
//                int chanceofsunshine;
                
//                @SerializedName("chanceoffrost")
//                int chanceoffrost;
                
//                @SerializedName("chanceofhightemp")
//                int chanceofhightemp;
                
//                @SerializedName("chanceoffog")
//                int chanceoffog;
                
                @SerializedName("chanceofsnow")
                int chanceofsnow;
                
//                @SerializedName("chanceofthunder")
//                int chanceofthunder;


                static class WeatherDesc {

                    @SerializedName("value")
                    String value;

                }
                
            }

        }
    }

    List<DailyWeather> createModel(Context context, WeatherConfig config) {

        int dayCount = config.getLimitNextDays();

        if (dayCount < 0) {
            dayCount = data.weather.size();
        } else if (dayCount == 0) {
            dayCount = 1;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);

        List<DailyWeather> weatherList = new ArrayList<>();

        long sunrise = 0;
        long sunset = 0;
        long moonrise = 0;
        long moonset = 0;
        int count = 1;

        for (Data.Weather daily : this.data.weather) {

            if (count > dayCount) {
                break;
            }

            Data.Weather.Hourly avr24h = daily.hourly.get(0);

            try {

                sunrise = timeFormat.parse(daily.date + " "
                        + daily.astronomy.get(0).sunrise).getTime();

                sunset = timeFormat.parse(daily.date + " "
                        + daily.astronomy.get(0).sunset).getTime();

                if (!daily.astronomy.get(0).moonrise.contentEquals("No moonrise")) {
                    moonrise = timeFormat.parse(daily.date + " "
                            + daily.astronomy.get(0).moonrise).getTime();
                }

                if (!daily.astronomy.get(0).moonset.contentEquals("No moonset")) {
                    moonset = timeFormat.parse(daily.date + " "
                            + daily.astronomy.get(0).moonset).getTime();
                }

                int precipitationProbably = 0;
                if (avr24h.chanceofsnow > 0) {
                    precipitationProbably = avr24h.chanceofsnow;
                } else if (avr24h.chanceofrain > 0) {
                    precipitationProbably = avr24h.chanceofrain;
                }

                weatherList.add(new DailyWeather.Builder()

                        .setDate(format.parse(daily.date).getTime())

                        .setCondition(Util.getIcon(avr24h.weatherCode, true),
                                Util.getDescription(context, config.getLanguage(),
                                avr24h.weatherCode,
                                avr24h.weatherDesc.get(0).value))

                        .setTemperature(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.mintempC),
                                TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), daily.maxtempC),
                                config.getTemperatureUnit().str())

                        .setDewPoint(TEMPERATURE.convert(TEMPERATURE.C, config.getTemperatureUnit(), avr24h.dewPointC),
                                config.getTemperatureUnit().str())
                        .setPressure(PRESSURE.convert(PRESSURE.hPa, config.getPressureUnit(), avr24h.pressure),
                                config.getPressureUnit().str())
                        .setHumidity(Math.round(avr24h.humidity))
                        .setVisibility(LENGTH.convert(LENGTH.Km, config.getVisibilityUnit(), avr24h.visibility),
                                config.getVisibilityUnit().str())

                        .setWind(SPEED.convert(SPEED.Km_h, config.getSpeedUnit(), avr24h.windspeedKmph),
                                avr24h.winddirDegree,
                                config.getSpeedUnit().str())

                        .setCloudCover(Math.round(avr24h.cloudcover))

                        .setUvIndex(daily.uvIndex)

                        .setPrecipitation(AMOUNT.convert(AMOUNT.mm, config.getPrecipitationAmountUnit(), avr24h.precipMM),
                                precipitationProbably,
                                config.getPrecipitationAmountUnit().str())

                        .setSunAstronomy(sunrise, sunset)
                        .setMoonAstronomy(moonrise, moonset)
                        .setMoonPhase(daily.astronomy.get(0).moonIllumination / 100f)

                        .build());

            } catch (ParseException e) {
                e.printStackTrace();

                return null;
            }

            count += 1;

        }

        return weatherList;
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
