package ir.mtapps.weatherlib.provider.accuweather;

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
            "bn", // Bengali
            "bs", // Bosnian
            "bg", // Bulgarian
            "ca", // Catalan
            "zh", // Chinese
            "hr", // Croatian
            "cs", // Czech
            "da", // Danish
            "nl", // Dutch
            "en", // English
            "et", // Estonian
            "fa", // Farsi
            "fil", // Filipino
            "fi", // Finnish
            "fr", // French
            "de", // German
            "el", // Greek
            "gu", // Gujarati
            "he", // Hebrew
            "hi", // Hindi
            "hu", // Hungarian
            "is", // Icelandic
            "id", // Indonesian
            "it", // Italian
            "ja", // Japanese
            "kn", // Kannada
            "kk", // Kazakh
            "ko", // Korean
            "lv", // Latvian
            "it", // Lithuanian
            "mk", // Macedonian
            "ms", // Malay
            "mr", // Marathi
            "nb", // Norwegian
            "pl", // Polish
            "pt", // Portuguese
            "pa", // Punjabi
            "ro", // Romanian
            "ru", // Russian
            "sr", // Serbian
            "sk", // Slovak
            "sl", // Slovenian
            "es", // Spanish
            "sw", // Swahili
            "sv", // Swedish
            "tl", // Tagalog
            "ta", // Tamil
            "te", // Telegu
            "th", // Thai
            "tr", // Turkish
            "uk", // Ukrainian
            "ur", // Urdu
            "uz", // Uzbek
            "vi"  // Vietnamese
    };

    static String getLanguageForUrl(String language) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return language;
        } else {
            return "en";
        }

    }

    static WEATHER_ICON getIcon(int code) {

        switch (code) {

            // Sunny
            case 1:
                return WEATHER_ICON.SUNNY;

            // Mostly Sunny
            case 2:
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;

            // Partly Sunny
            case 3:
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;

            // Intermittent Clouds
            case 4:
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;

            // Hazy Sunshine
            case 5:
                return WEATHER_ICON.FOG;

            // Mostly Cloudy
            case 6:
                return WEATHER_ICON.MOSTLY_CLOUDY_DAY;

            // Cloudy
            case 7:
                return WEATHER_ICON.CLOUDY;

            // Dreary (Overcast)
            case 8:
                return WEATHER_ICON.CLOUDY;

            // Fog
            case 11:
                return WEATHER_ICON.FOG;

            // Showers
            case 12:
                return WEATHER_ICON.SHOWERS;

            // Mostly Cloudy w/ Showers
            case 13:
                return WEATHER_ICON.RAIN_DAY;

            // Partly Sunny w/ Showers
            case 14:
                return WEATHER_ICON.RAIN_DAY;

            // T-Storms
            case 15:
                return WEATHER_ICON.THUNDERSTORM;

            // Mostly Cloudy w/ T-Storms
            case 16:
                return WEATHER_ICON.THUNDERSTORM_DAY;

            // Partly Sunny w/ T-Storms
            case 17:
                return WEATHER_ICON.THUNDERSTORM_DAY;

            // Rain
            case 18:
                return WEATHER_ICON.RAIN;

            // Flurries
            case 19:
                return WEATHER_ICON.SNOW;

            // Mostly Cloudy w/ Flurries
            case 20:
                return WEATHER_ICON.SNOW_DAY;

            // Partly Sunny w/ Flurries
            case 21:
                return WEATHER_ICON.SNOW_DAY;

            // Snow
            case 22:
                return WEATHER_ICON.SNOW;

            // Mostly Cloudy w/ Snow
            case 23:
                return WEATHER_ICON.SNOW_DAY;

            // Ice
            case 24:
                return WEATHER_ICON.COLD;

            // Sleet
            case 25:
                return WEATHER_ICON.SLEET;

            // Freezing Rain
            case 26:
                return WEATHER_ICON.SLEET;

            // Rain and Snow
            case 29:
                return WEATHER_ICON.RAIN_AND_SNOW;

            // Hot
            case 30:
                return WEATHER_ICON.HOT;

            // Cold
            case 31:
                return WEATHER_ICON.COLD;

            // Windy
            case 32:
                return WEATHER_ICON.WINDY;

            // Clear
            case 33:
                return WEATHER_ICON.CLEAR_NIGHT;

            // Mostly Clear
            case 34:
                return WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            // Partly Cloudy
            case 35:
                return WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            // Intermittent Clouds
            case 36:
                return WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;

            // Hazy Moonlight
            case 37:
                return WEATHER_ICON.FOG;

            // Mostly Cloudy
            case 38:
                return WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;

            // Partly Cloudy w/ Showers
            case 39:
                return WEATHER_ICON.RAIN_NIGHT;

            // Mostly Cloudy w/ Showers
            case 40:
                return WEATHER_ICON.RAIN_NIGHT;

            // Partly Cloudy w/ T-Storms
            case 41:
                return WEATHER_ICON.THUNDERSTORM_NIGHT;

            // Mostly Cloudy w/ T-Storms
            case 42:
                return WEATHER_ICON.THUNDERSTORM_NIGHT;

            // Mostly Cloudy w/ Flurries
            case 43:
                return WEATHER_ICON.SNOW_NIGHT;

            // Mostly Cloudy w/ Snow
            case 44:
                return WEATHER_ICON.SNOW_NIGHT;

            default:
                return WEATHER_ICON.NA;
        }

    }


    static String getDescription(Context context, String language, String desc, int code) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resource = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        switch (code) {

            // Sunny
            case 1:
                return resource.getString(R.string.clear_sky);

            // Mostly Sunny
            case 2:
                return resource.getString(R.string.few_clouds);

            // Partly Sunny
            case 3:
                return resource.getString(R.string.few_clouds);

            // Intermittent Clouds
            case 4:
                return resource.getString(R.string.few_clouds);

            // Hazy Sunshine
            case 5:
                return resource.getString(R.string.fog);

            // Mostly Cloudy
            case 6:
                return resource.getString(R.string.cloudy);

            // Cloudy
            case 7:
                return resource.getString(R.string.cloudy);

            // Dreary (Overcast)
            case 8:
                return resource.getString(R.string.cloudy);

            // Fog
            case 11:
                return resource.getString(R.string.fog);

            // Showers
            case 12:
                return resource.getString(R.string.shower_rain);

            // Mostly Cloudy w/ Showers
            case 13:
                return resource.getString(R.string.shower_rain);

            // Partly Sunny w/ Showers
            case 14:
                return resource.getString(R.string.shower_rain);

            // T-Storms
            case 15:
                return resource.getString(R.string.thunderstorm);

            // Mostly Cloudy w/ T-Storms
            case 16:
                return resource.getString(R.string.thunderstorm);

            // Partly Sunny w/ T-Storms
            case 17:
                return resource.getString(R.string.thunderstorm);

            // Rain
            case 18:
                return resource.getString(R.string.moderate_rain);

            // Flurries
            case 19:
                return resource.getString(R.string.flurries);

            // Mostly Cloudy w/ Flurries
            case 20:
                return resource.getString(R.string.flurries);

            // Partly Sunny w/ Flurries
            case 21:
                return resource.getString(R.string.flurries);

            // Snow
            case 22:
                return resource.getString(R.string.snow);

            // Mostly Cloudy w/ Snow
            case 23:
                return resource.getString(R.string.snow);

            // Ice
            case 24:
                return resource.getString(R.string.cold);

            // Sleet
            case 25:
                return resource.getString(R.string.sleet);

            // Freezing Rain
            case 26:
                return resource.getString(R.string.freezing_rain);

            // Rain and Snow
            case 29:
                return resource.getString(R.string.rain_and_snow);

            // Hot
            case 30:
                return resource.getString(R.string.hot);

            // Cold
            case 31:
                return resource.getString(R.string.cold);

            // Windy
            case 32:
                return resource.getString(R.string.windy);

            // Clear
            case 33:
                return resource.getString(R.string.clear_sky);

            // Mostly Clear
            case 34:
                return resource.getString(R.string.few_clouds);

            // Partly Cloudy
            case 35:
                return resource.getString(R.string.few_clouds);

            // Intermittent Clouds
            case 36:
                return resource.getString(R.string.few_clouds);

            // Hazy Moonlight
            case 37:
                return resource.getString(R.string.fog);

            // Mostly Cloudy
            case 38:
                return resource.getString(R.string.cloudy);

            // Partly Cloudy w/ Showers
            case 39:
                return resource.getString(R.string.shower_rain);

            // Mostly Cloudy w/ Showers
            case 40:
                return resource.getString(R.string.shower_rain);

            // Partly Cloudy w/ T-Storms
            case 41:
                return resource.getString(R.string.shower_rain);

            // Mostly Cloudy w/ T-Storms
            case 42:
                return resource.getString(R.string.shower_rain);

            // Mostly Cloudy w/ Flurries
            case 43:
                return resource.getString(R.string.flurries);

            // Mostly Cloudy w/ Snow
            case 44:
                return resource.getString(R.string.snow);

            default:
                return resource.getString(R.string.weather_na);
        }
    }

    static String getError(int code, String message) {

        switch (code) {
            case 400:
                return "Request had bad syntax or the parameters supplied were invalid.";

            case 401:
                return "Unauthorized. API authorization failed.";

            case 403:
                return "Unauthorized. You do not have permission to access this endpoint.";

            case 404:
                return "Server has not found a route matching the given URI.";

            case 500:
                return "Server encountered an unexpected condition which prevented it from fulfilling the request.";

            default:
                return "[" + code + "] " + message;
        }

    }
}
