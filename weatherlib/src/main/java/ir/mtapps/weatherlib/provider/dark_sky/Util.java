package ir.mtapps.weatherlib.provider.dark_sky;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

class Util {

    private static final String[] SUPPORTED_LANGUAGE = {
            "ar", // Arabic
            "az", // Azerbaijani
            "be", // Belarusian
            "bg", // Bulgarian
            "bs", // Bosnian
            "ca", // Catalan
            "cs", // Czech
            "da", // Danish
            "de", // German
            "el", // Greek
            "en", // English
            "es", // Spanish
            "et", // Estonian
            "fi", // Finnish
            "fr", // French
            "hr", // Croatian
            "hu", // Hungarian
            "id", // Indonesian
            "is", // Icelandic
            "it", // Italian
            "ja", // Japanese
            "ka", // Georgian
            "kw", // Cornish
            "nb", // Norwegian Bokmål
            "nl", // Dutch
            "pl", // Polish
            "pt", // Portuguese
            "ro", // Romanian
            "ru", // Russian
            "sk", // Slovak
            "sl", // Slovenian
            "sr", // Serbian
            "sv", // Swedish
            "tet", // Tetum
            "tr", // Turkish
            "uk", // Ukrainian
            "x_pig_latin", // Igpay Atinlay
            "zh", // simplified Chinese
            "zh_tw" // traditional Chinese
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
                return WEATHER_ICON.SUNNY;
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
                return WEATHER_ICON.SLEET;
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

    static String getDailyDescription(Context context, String language, String desc) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        desc = desc.toLowerCase();

        if (desc.contains("clear")) {
            return resources.getString(R.string.ds_clear);
        } else if (desc.contains("no precipitation")) {
            return resources.getString(R.string.ds_no_precipitation);
        } else if (desc.contains("mixed precipitation")) {
            return resources.getString(R.string.ds_mixed_precipitation);
        } else if (desc.contains("possible very light precipitation")) {
            return resources.getString(R.string.ds_possible_very_light_precipitation);
        } else if (desc.contains("very light precipitation")) {
            return resources.getString(R.string.ds_very_light_precipitation);
        } else if (desc.contains("possible light precipitation")) {
            return resources.getString(R.string.ds_possible_light_precipitation);
        } else if (desc.contains("light precipitation")) {
            return resources.getString(R.string.ds_light_precipitation);
        } else if (desc.contains("precipitation")) {
            return resources.getString(R.string.ds_medium_precipitation);
        } else if (desc.contains("heavy precipitation")) {
            return resources.getString(R.string.ds_heavy_precipitation);
        } else if (desc.contains("possible drizzle")) {
            return resources.getString(R.string.ds_possible_very_light_rain);
        } else if (desc.contains("drizzle")) {
            return resources.getString(R.string.ds_very_light_rain);
        } else if (desc.contains("possible light rain")) {
            return resources.getString(R.string.ds_possible_light_rain);
        } else if (desc.contains("light rain")) {
            return resources.getString(R.string.ds_light_rain);
        } else if (desc.contains("rain")) {
            return resources.getString(R.string.ds_medium_rain);
        } else if (desc.contains("heavy rain")) {
            return resources.getString(R.string.ds_heavy_rain);
        } else if (desc.contains("possible very light sleet")) {
            return resources.getString(R.string.ds_possible_very_light_sleet);
        } else if (desc.contains("very light sleet")) {
            return resources.getString(R.string.ds_very_light_sleet);
        } else if (desc.contains("possible light sleet")) {
            return resources.getString(R.string.ds_possible_light_sleet);
        } else if (desc.contains("light sleet")) {
            return resources.getString(R.string.ds_light_sleet);
        } else if (desc.contains("sleet")) {
            return resources.getString(R.string.ds_medium_sleet);
        } else if (desc.contains("heavy sleet")) {
            return resources.getString(R.string.ds_heavy_sleet);
        } else if (desc.contains("possible flurries")) {
            return resources.getString(R.string.ds_possible_very_light_snow);
        } else if (desc.contains("flurries")) {
            return resources.getString(R.string.ds_very_light_snow);
        } else if (desc.contains("possible light snow")) {
            return resources.getString(R.string.ds_possible_light_snow);
        } else if (desc.contains("light snow")) {
            return resources.getString(R.string.ds_light_snow);
        } else if (desc.contains("snow")) {
            return resources.getString(R.string.ds_medium_snow);
        } else if (desc.contains("heavy snow")) {
            return resources.getString(R.string.ds_heavy_snow);
        } else if (desc.contains("possible thunderstorm")) {
            return resources.getString(R.string.ds_possible_thunderstorm);
        } else if (desc.contains("thunderstorm")) {
            return resources.getString(R.string.ds_thunderstorm);
        } else if (desc.contains("breezy")) {
            return resources.getString(R.string.ds_light_wind);
        } else if (desc.contains("windy")) {
            return resources.getString(R.string.ds_medium_wind);
        } else if (desc.contains("dangerously windy")) {
            return resources.getString(R.string.ds_heavy_wind);
        } else if (desc.contains("dry")) {
            return resources.getString(R.string.ds_low_humidity);
        } else if (desc.contains("humid")) {
            return resources.getString(R.string.ds_high_humidity);
        } else if (desc.contains("foggy")) {
            return resources.getString(R.string.ds_fog);
        } else if (desc.contains("partly cloudy")) {
            return resources.getString(R.string.ds_light_clouds);
        } else if (desc.contains("mostly cloudy")) {
            return resources.getString(R.string.ds_medium_clouds);
        } else if (desc.contains("overcast")) {
            return resources.getString(R.string.ds_heavy_clouds);
        }

        return desc;
    }

}
