package ir.mtapps.weatherlib.provider.visualcrossing;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

public class Util {

    private static final String[] SUPPORTED_LANGUAGE = {
            "de", // German
            "en", // English
            "es", // Spanish
            "fi", // Finnish
            "fr", // French
            "it", // Italian
            "ja", // Japanese
            "ko", // Korean
            "pt", // Portuguese
            "ru", // Russian
            "zh" // Chinese
    };

    static String getLanguageForUrl(String language) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return language;
        } else {
            return "en";
        }

    }

    static WEATHER_ICON getIcon(String icon) {
        switch (icon) {
            case "clear-day":
                return WEATHER_ICON.CLEAR_DAY;
            case "clear-night":
                return WEATHER_ICON.CLEAR_NIGHT;
            case "partly-cloudy-night":
                return WEATHER_ICON.PARTLY_CLOUDY_NIGHT;
            case "partly-cloudy-day":
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;
            case "cloudy":
                return WEATHER_ICON.CLOUDY;
            case "rain":
                return WEATHER_ICON.RAIN;
            case "sleet":
                return WEATHER_ICON.HAIL;
            case "snow":
                return WEATHER_ICON.SNOW;
            case "hail":
                return WEATHER_ICON.HAIL;
            case "thunderstorm":
                return WEATHER_ICON.THUNDERSTORM;
            case "fog":
                return WEATHER_ICON.FOG;
            case "tornado":
                return WEATHER_ICON.TORNADO;
            case "wind":
                return WEATHER_ICON.WINDY;
            default:
                return WEATHER_ICON.NA;
        }
    }

    static String getDescription(Context context, String language, String desc) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        desc = desc.toLowerCase();

        switch (desc) {

            case "clear":
                return resources.getString(R.string.ds_clear);

            case "no precipitation":
                return resources.getString(R.string.ds_no_precipitation);

            case "mixed precipitation":
                return resources.getString(R.string.ds_mixed_precipitation);

            case "possible very light precipitation":
                return resources.getString(R.string.ds_possible_very_light_precipitation);

            case "very light precipitation":
                return resources.getString(R.string.ds_very_light_precipitation);

            case "possible light precipitation":
                return resources.getString(R.string.ds_possible_light_precipitation);

            case "light precipitation":
                return resources.getString(R.string.ds_light_precipitation);

            case "precipitation":
                return resources.getString(R.string.ds_medium_precipitation);

            case "heavy precipitation":
                return resources.getString(R.string.ds_heavy_precipitation);

            case "possible drizzle":
                return resources.getString(R.string.ds_possible_very_light_rain);

            case "drizzle":
                return resources.getString(R.string.ds_very_light_rain);

            case "possible light rain":
                return resources.getString(R.string.ds_possible_light_rain);

            case "light rain":
                return resources.getString(R.string.ds_light_rain);

            case "rain":
                return resources.getString(R.string.ds_medium_rain);

            case "heavy rain":
                return resources.getString(R.string.ds_heavy_rain);

            case "possible very light sleet":
                return resources.getString(R.string.ds_possible_very_light_sleet);

            case "very light sleet":
                return resources.getString(R.string.ds_very_light_sleet);

            case "possible light sleet":
                return resources.getString(R.string.ds_possible_light_sleet);

            case "light sleet":
                return resources.getString(R.string.ds_light_sleet);

            case "sleet":
                return resources.getString(R.string.ds_medium_sleet);

            case "heavy sleet":
                return resources.getString(R.string.ds_heavy_sleet);

            case "possible flurries":
                return resources.getString(R.string.ds_possible_very_light_snow);

            case "flurries":
                return resources.getString(R.string.ds_very_light_snow);

            case "possible light snow":
                return resources.getString(R.string.ds_possible_light_snow);

            case "light snow":
                return resources.getString(R.string.ds_light_snow);

            case "snow":
                return resources.getString(R.string.ds_medium_snow);

            case "heavy snow":
                return resources.getString(R.string.ds_heavy_snow);

            case "possible thunderstorm":
                return resources.getString(R.string.ds_possible_thunderstorm);

            case "thunderstorm":
                return resources.getString(R.string.ds_thunderstorm);

            case "breezy":
                return resources.getString(R.string.ds_light_wind);

            case "windy":
                return resources.getString(R.string.ds_medium_wind);

            case "dangerously windy":
                return resources.getString(R.string.ds_heavy_wind);

            case "dry":
                return resources.getString(R.string.ds_low_humidity);

            case "humid":
                return resources.getString(R.string.ds_high_humidity);

            case "foggy":
                return resources.getString(R.string.ds_fog);

            case "partly cloudy":
                return resources.getString(R.string.ds_light_clouds);

            case "mostly cloudy":
                return resources.getString(R.string.ds_medium_clouds);

            case "overcast":
                return resources.getString(R.string.ds_heavy_clouds);
        }

        return desc;
    }

    static String getDailyDescription(Context context, String language, String condition, String desc) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        condition = condition.toLowerCase();

        if (condition.contains("clear")) {
            return resources.getString(R.string.ds_clear);
        } else if (condition.contains("no precipitation")) {
            return resources.getString(R.string.ds_no_precipitation);
        } else if (condition.contains("mixed precipitation")) {
            return resources.getString(R.string.ds_mixed_precipitation);
        } else if (condition.contains("possible very light precipitation")) {
            return resources.getString(R.string.ds_possible_very_light_precipitation);
        } else if (condition.contains("very light precipitation")) {
            return resources.getString(R.string.ds_very_light_precipitation);
        } else if (condition.contains("possible light precipitation")) {
            return resources.getString(R.string.ds_possible_light_precipitation);
        } else if (condition.contains("light precipitation")) {
            return resources.getString(R.string.ds_light_precipitation);
        } else if (condition.contains("precipitation")) {
            return resources.getString(R.string.ds_medium_precipitation);
        } else if (condition.contains("heavy precipitation")) {
            return resources.getString(R.string.ds_heavy_precipitation);
        } else if (condition.contains("possible drizzle")) {
            return resources.getString(R.string.ds_possible_very_light_rain);
        } else if (condition.contains("drizzle")) {
            return resources.getString(R.string.ds_very_light_rain);
        } else if (condition.contains("possible light rain")) {
            return resources.getString(R.string.ds_possible_light_rain);
        } else if (condition.contains("light rain")) {
            return resources.getString(R.string.ds_light_rain);
        } else if (condition.contains("rain")) {
            return resources.getString(R.string.ds_medium_rain);
        } else if (condition.contains("heavy rain")) {
            return resources.getString(R.string.ds_heavy_rain);
        } else if (condition.contains("possible very light sleet")) {
            return resources.getString(R.string.ds_possible_very_light_sleet);
        } else if (condition.contains("very light sleet")) {
            return resources.getString(R.string.ds_very_light_sleet);
        } else if (condition.contains("possible light sleet")) {
            return resources.getString(R.string.ds_possible_light_sleet);
        } else if (condition.contains("light sleet")) {
            return resources.getString(R.string.ds_light_sleet);
        } else if (condition.contains("sleet")) {
            return resources.getString(R.string.ds_medium_sleet);
        } else if (condition.contains("heavy sleet")) {
            return resources.getString(R.string.ds_heavy_sleet);
        } else if (condition.contains("possible flurries")) {
            return resources.getString(R.string.ds_possible_very_light_snow);
        } else if (condition.contains("flurries")) {
            return resources.getString(R.string.ds_very_light_snow);
        } else if (condition.contains("possible light snow")) {
            return resources.getString(R.string.ds_possible_light_snow);
        } else if (condition.contains("light snow")) {
            return resources.getString(R.string.ds_light_snow);
        } else if (condition.contains("snow")) {
            return resources.getString(R.string.ds_medium_snow);
        } else if (condition.contains("heavy snow")) {
            return resources.getString(R.string.ds_heavy_snow);
        } else if (condition.contains("possible thunderstorm")) {
            return resources.getString(R.string.ds_possible_thunderstorm);
        } else if (condition.contains("thunderstorm")) {
            return resources.getString(R.string.ds_thunderstorm);
        } else if (condition.contains("breezy")) {
            return resources.getString(R.string.ds_light_wind);
        } else if (condition.contains("windy")) {
            return resources.getString(R.string.ds_medium_wind);
        } else if (condition.contains("dangerously windy")) {
            return resources.getString(R.string.ds_heavy_wind);
        } else if (condition.contains("dry")) {
            return resources.getString(R.string.ds_low_humidity);
        } else if (condition.contains("humid")) {
            return resources.getString(R.string.ds_high_humidity);
        } else if (condition.contains("foggy")) {
            return resources.getString(R.string.ds_fog);
        } else if (condition.contains("partly cloudy")) {
            return resources.getString(R.string.ds_light_clouds);
        } else if (condition.contains("mostly cloudy")) {
            return resources.getString(R.string.ds_medium_clouds);
        } else if (condition.contains("overcast")) {
            return resources.getString(R.string.ds_heavy_clouds);
        }

        return condition;
    }
}
