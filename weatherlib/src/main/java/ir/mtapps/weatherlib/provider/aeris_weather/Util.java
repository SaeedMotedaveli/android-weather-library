package ir.mtapps.weatherlib.provider.aeris_weather;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

public class Util {

    static String getDescription(Context context, String language, String condition, String weatherCoded) {

        if (language.contentEquals("en")) {
            return condition;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        String[] args = weatherCoded.split(":");
        String coverageCode = args[0];
        String intensityCode = args[1];
        String weatherCode = args[2];

        String coverage = "";
        switch (coverageCode) {
            case "AR": // Areas of
                coverage = resources.getString(R.string.arw_coverage_areas_of);break;
            case "BR": // Brief
                coverage = resources.getString(R.string.arw_coverage_brief);break;
            case "C": // Chance of
                coverage = resources.getString(R.string.arw_coverage_chance_of);break;
            case "D": // Definite
                coverage = resources.getString(R.string.arw_coverage_definite);break;
            case "FQ": // Frequent
                coverage = resources.getString(R.string.arw_coverage_frequent);break;
            case "IN": // Intermittent
                coverage = resources.getString(R.string.arw_coverage_intermittent);break;
            case "IS": // Isolated
                coverage = resources.getString(R.string.arw_coverage_isolated);break;
            case "L": // Likely
                coverage = resources.getString(R.string.arw_coverage_likely);break;
            case "NM": // Numerous
                coverage = resources.getString(R.string.arw_coverage_numerous);break;
            case "O": // Occasional
                coverage = resources.getString(R.string.arw_coverage_occasional);break;
            case "PA": // Patchy
                coverage = resources.getString(R.string.arw_coverage_patchy);break;
            case "PD": // Periods of
                coverage = resources.getString(R.string.arw_coverage_periods_of);break;
            case "S": // Slight chance
                coverage = resources.getString(R.string.arw_coverage_slight_chance);break;
            case "SC": // Scattered
                coverage = resources.getString(R.string.arw_coverage_scattered);break;
            case "VC": // In the vicinity/Nearby
                coverage = resources.getString(R.string.arw_coverage_in_the_nearby);break;
            case "WD": // Widespread
                coverage = resources.getString(R.string.arw_coverage_widespread);break;
        }

        String intensity = "";
        switch (intensityCode) {
            case "VL": // Very light
                intensity = resources.getString(R.string.arw_intensity_very_light); break;
            case "L": // Light
                intensity = resources.getString(R.string.arw_intensity_light); break;
            case "H": // Heavy
                intensity = resources.getString(R.string.arw_intensity_heavy); break;
            case "VH": // Very heavy
                intensity = resources.getString(R.string.arw_intensity_very_heavy); break;
            default: // Moderate
                intensity = resources.getString(R.string.arw_intensity_moderate); break;
        }

        String weather = "";
        switch (weatherCode) {
            case "A":    // Hail
                weather =  resources.getString(R.string.arw_weather_hail, coverage, intensity); break;

            case "BD":    // Blowing dust
                weather =  resources.getString(R.string.arw_weather_blowing_dust, coverage, intensity); break;

            case "BN":    // Blowing sand
                weather =  resources.getString(R.string.arw_weather_blowing_sand, coverage, intensity); break;

            case "BR":    // Mist
                weather =  resources.getString(R.string.arw_weather_mist, coverage, intensity); break;

            case "BS":    // Blowing snow
                weather =  resources.getString(R.string.arw_weather_blowing_snow, coverage, intensity); break;

            case "BY":    // Blowing spray
                weather =  resources.getString(R.string.arw_weather_blowing_spray, coverage, intensity); break;

            case "F":    // Fog
                weather =  resources.getString(R.string.arw_weather_fog, coverage, intensity); break;

            case "FR":    // Frost
                weather =  resources.getString(R.string.arw_weather_frost, coverage, intensity); break;

            case "H":    // Haze
                weather =  resources.getString(R.string.arw_weather_haze, coverage, intensity); break;

            case "IC":    // Ice crystals
                weather =  resources.getString(R.string.arw_weather_ice_crystals, coverage, intensity); break;

            case "IF":    // Ice fog
                weather =  resources.getString(R.string.arw_weather_ice_fog, coverage, intensity); break;

            case "IP":    // Ice pellets / Sleet
                weather =  resources.getString(R.string.arw_weather_sleet, coverage, intensity); break;

            case "K":    // Smoke
                weather =  resources.getString(R.string.arw_weather_smoke, coverage, intensity); break;

            case "L":    // Drizzle
                weather =  resources.getString(R.string.arw_weather_drizzle, coverage, intensity); break;

            case "R":    // Rain
                weather =  resources.getString(R.string.arw_weather_rain, coverage, intensity); break;

            case "RW":    // Rain showers
                weather =  resources.getString(R.string.arw_weather_rain_showers, coverage, intensity); break;

            case "RS":    // Rain/snow mix
                weather =  resources.getString(R.string.arw_weather_rain_snow, coverage, intensity); break;

            case "SI":    // Snow/sleet mix
                weather =  resources.getString(R.string.arw_weather_snow_sleet, coverage, intensity); break;

            case "WM":    // Wintry mix (snow, sleet, rain)
                weather =  resources.getString(R.string.arw_weather_rain_snow_sleet, coverage, intensity); break;

            case "S":    // Snow
                weather =  resources.getString(R.string.arw_weather_snow, coverage, intensity); break;

            case "SW":    // Snow showers
                weather =  resources.getString(R.string.arw_weather_snow_showers, coverage, intensity); break;

            case "T":    // Thunderstorms
                weather =  resources.getString(R.string.arw_weather_thunderstorms, coverage, intensity); break;

            case "UP":    // Unknown precipitation	May occur in an automated observation station, which cannot determine the precipitation type falling.
                weather =  resources.getString(R.string.arw_weather_unknown_precipitation); break;

            case "VA":    // Volcanic ash
                weather =  resources.getString(R.string.arw_weather_volcanic_ash, coverage, intensity); break;

            case "WP":    // Waterspouts
                weather =  resources.getString(R.string.arw_weather_waterspouts, coverage, intensity); break;

            case "ZF":    // Freezing fog
                weather =  resources.getString(R.string.arw_weather_freezing_fog, coverage, intensity); break;

            case "ZL":    // Freezing drizzle
                weather =  resources.getString(R.string.arw_weather_freezing_drizzle, coverage, intensity); break;

            case "ZR":    // Freezing rain
                weather =  resources.getString(R.string.arw_weather_freezing_rain, coverage, intensity); break;

            case "ZY":    // Freezing spray
                weather =  resources.getString(R.string.arw_weather_freezing_spray, coverage, intensity); break;

            case "CL":    // Clear	Cloud coverage is 0-7% of the sky.
                weather =  resources.getString(R.string.arw_weather_clear); break;

            case "FW":    // Fair/Mostly sunny	Cloud coverage is 7-32% of the sky.
                weather =  resources.getString(R.string.arw_weather_fair); break;

            case "SC":    // Partly cloudy	Cloud coverage is 32-70% of the sky.
                weather =  resources.getString(R.string.arw_weather_partly_cloudy); break;

            case "BK":    // Mostly Cloudy	Cloud coverage is 70-95% of the sky.
                weather =  resources.getString(R.string.arw_weather_mostly_cloudy); break;

            case "OV":    // Cloudy/Overcast	Cloud coverage is 95-100% of the sky.
                weather =  resources.getString(R.string.arw_weather_cloudy); break;
        }

        weather = weather.replace("  ", " ");
        return weather.trim();
    }

    static WEATHER_ICON getIcon(String weatherCoded, float cloudCover, boolean isDay) {

        String[] args = weatherCoded.split(":");
        String intensityCode = args[1];
        String weatherCode = args[2];

        switch (weatherCode) {
            case "A":    // Hail
                return initWeatherIcon(WEATHER_ICON.HAIL, intensityCode, cloudCover, isDay);

            case "BD":    // Blowing dust
                return WEATHER_ICON.DUST;

            case "BN":    // Blowing sand
                return WEATHER_ICON.DUST;

            case "BR":    // Mist
                return initWeatherIcon(WEATHER_ICON.FOG, intensityCode, cloudCover, isDay);

            case "BS":    // Blowing snow
                return initWeatherIcon(WEATHER_ICON.SNOW, intensityCode, cloudCover, isDay);

            case "BY":    // Blowing spray
                return initWeatherIcon(WEATHER_ICON.RAIN, intensityCode, cloudCover, isDay);

            case "F":    // Fog
                return initWeatherIcon(WEATHER_ICON.FOG, intensityCode, cloudCover, isDay);

            case "FR":    // Frost
                return initWeatherIcon(WEATHER_ICON.FREEZING_RAIN, intensityCode, cloudCover, isDay);

            case "H":    // Haze
                return initWeatherIcon(WEATHER_ICON.FOG, intensityCode, cloudCover, isDay);

            case "IC":    // Ice crystals
                return initWeatherIcon(WEATHER_ICON.HAIL, intensityCode, cloudCover, isDay);

            case "IF":    // Ice fog
                return initWeatherIcon(WEATHER_ICON.FOG, intensityCode, cloudCover, isDay);

            case "IP":    // Ice pellets / Sleet
                return initWeatherIcon(WEATHER_ICON.HAIL, intensityCode, cloudCover, isDay);

            case "K":    // Smoke
                return WEATHER_ICON.SMOKE;

            case "L":    // Drizzle
                return initWeatherIcon(WEATHER_ICON.DRIZZLE, intensityCode, cloudCover, isDay);

            case "R":    // Rain
                return initWeatherIcon(WEATHER_ICON.RAIN, intensityCode, cloudCover, isDay);

            case "RW":    // Rain showers
                return initWeatherIcon(WEATHER_ICON.SHOWER_RAIN, intensityCode, cloudCover, isDay);

            case "RS":    // Rain/snow mix
                return WEATHER_ICON.RAIN_AND_SNOW;

            case "SI":    // Snow/sleet mix
                return WEATHER_ICON.SNOW_AND_HAIL;

            case "WM":    // Wintry mix (snow, sleet, rain)
                return WEATHER_ICON.SNOW_AND_HAIL;

            case "S":    // Snow
                return initWeatherIcon(WEATHER_ICON.SNOW, intensityCode, cloudCover, isDay);

            case "SW":    // Snow showers
                return initWeatherIcon(WEATHER_ICON.SNOW, intensityCode, cloudCover, isDay);

            case "T":    // Thunderstorms
                return initWeatherIcon(WEATHER_ICON.THUNDERSTORM, intensityCode, cloudCover, isDay);

            case "UP":    // Unknown precipitation	May occur in an automated observation station, which cannot determine the precipitation type falling.
                return WEATHER_ICON.NA;

            case "VA":    // Volcanic ash
                return WEATHER_ICON.VOLCANO_ASH;

            case "WP":    // Waterspouts
                return WEATHER_ICON.TORNADO;

            case "ZF":    // Freezing fog
                return initWeatherIcon(WEATHER_ICON.FOG, intensityCode, cloudCover, isDay);

            case "ZL":    // Freezing drizzle
                return initWeatherIcon(WEATHER_ICON.FREEZING_RAIN, intensityCode, cloudCover, isDay);

            case "ZR":    // Freezing rain
                return initWeatherIcon(WEATHER_ICON.FREEZING_RAIN, intensityCode, cloudCover, isDay);

            case "ZY":    // Freezing spray
                return initWeatherIcon(WEATHER_ICON.FREEZING_RAIN, intensityCode, cloudCover, isDay);

            case "CL":    // Clear	Cloud coverage is 0-7% of the sky.
                return isDay ? WEATHER_ICON.CLEAR_DAY : WEATHER_ICON.CLEAR_NIGHT;

            case "FW":    // Fair/Mostly sunny	Cloud coverage is 7-32% of the sky.
            case "SC":    // Partly cloudy	Cloud coverage is 32-70% of the sky.
                return isDay ? WEATHER_ICON.PARTLY_CLOUDY_DAY : WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            case "BK":    // Mostly Cloudy	Cloud coverage is 70-95% of the sky.
                return isDay ? WEATHER_ICON.MOSTLY_CLOUDY_DAY : WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;

            case "OV":    // Cloudy/Overcast	Cloud coverage is 95-100% of the sky.
                return WEATHER_ICON.CLOUDY;

            default:
                return null;

        }
    }

    private static WEATHER_ICON initWeatherIcon(WEATHER_ICON baseIcon, String intensity, float cloudCover, boolean isDay) {

        WEATHER_ICON weather_icon;

        switch (intensity) {
            case "VL": // Very light
            case "L": // Light
                try {
                    weather_icon = WEATHER_ICON.valueOf("LIGHT_" + baseIcon);
                } catch (IllegalArgumentException e) {
                    weather_icon = baseIcon;
                }
                break;
            case "H": // Heavy
            case "VH": // Very heavy
                try {
                    weather_icon = WEATHER_ICON.valueOf("HEAVY_" + baseIcon);
                } catch (IllegalArgumentException e) {
                    weather_icon = baseIcon;
                }
                break;
            default: // Moderate
                weather_icon = baseIcon;
        }

        if (cloudCover < 90) {

            if (isDay) {
                try {
                    weather_icon = WEATHER_ICON.valueOf(baseIcon + "_DAY");
                } catch (IllegalArgumentException e) {
                    weather_icon = baseIcon;
                }
            } else {
                try {
                    weather_icon = WEATHER_ICON.valueOf(baseIcon + "_NIGHT");
                } catch (IllegalArgumentException e) {
                    weather_icon = baseIcon;
                }
            }

        }

        return weather_icon;
    }

}
