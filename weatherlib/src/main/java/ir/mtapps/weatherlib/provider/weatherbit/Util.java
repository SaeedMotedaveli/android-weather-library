package ir.mtapps.weatherlib.provider.weatherbit;

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
            "cz", // Czech
            "da", // Danish
            "de", // German
            "el", // Greek
            "en", // English
            "es", // Spanish
            "et", // Estonian
            "fi", // Finnish
            "fr", // French
            "ja", // Japanese
            "hr", // Croatian
            "hu", // Hungarian
            "id", // Indonesian
            "is", // Icelandic
            "it", // Italian
            "iw", // Hebrew
            "kw", // Cornish
            "lt", // Lithuanian
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
            "tr", // Turkish
            "uk", // Ukrainian
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

    static WEATHER_ICON getIcon(int icon, String isDayTime) {

        boolean isDay = isDayTime.contentEquals("d");

        switch (icon) {

            // Thunderstorm with light rain
            case 200:
                return isDay ? WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with rain
            case 201:
                return isDay ? WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with heavy rain
            case 202:
                return isDay ? WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with light drizzle
            case 230:
                return isDay ? WEATHER_ICON.THUNDERSTORM : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with drizzle
            case 231:
                return isDay ? WEATHER_ICON.THUNDERSTORM : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with heavy drizzle
            case 232:
                return isDay ? WEATHER_ICON.THUNDERSTORM : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Thunderstorm with Hail
            case 233:
                return isDay ? WEATHER_ICON.THUNDERSTORM : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            // Light Drizzle
            case 300:
                return WEATHER_ICON.SHOWER_RAIN;

            // Drizzle
            case 301:
                return WEATHER_ICON.SHOWER_RAIN;

            // Heavy Drizzle
            case 302:
                return WEATHER_ICON.SHOWER_RAIN;

            // Light Rain
            case 500:
                return WEATHER_ICON.LIGHT_RAIN;

            // Moderate Rain
            case 501:
                return WEATHER_ICON.RAIN;

            // Heavy Rain
            case 502:
                return WEATHER_ICON.RAIN;

            // Freezing rain
            case 511:
                return WEATHER_ICON.RAIN;

            // Light shower rain
            case 520:
                return isDay ? WEATHER_ICON.RAIN_DAY : WEATHER_ICON.RAIN_NIGHT;

            // Shower rain
            case 521:
                return isDay ? WEATHER_ICON.RAIN_DAY : WEATHER_ICON.RAIN_NIGHT;

            // Heavy shower rain
            case 522:
                return isDay ? WEATHER_ICON.RAIN_DAY : WEATHER_ICON.RAIN_NIGHT;

            // Light snow
            case 600:
                return isDay ? WEATHER_ICON.SNOW_DAY : WEATHER_ICON.SNOW_NIGHT;

            // Snow
            case 601:
                return WEATHER_ICON.SNOW;

            // Heavy Snow
            case 602:
                return WEATHER_ICON.SNOW;

            // Mix snow/rain
            case 610:
                return WEATHER_ICON.RAIN_AND_SNOW;

            // Sleet
            case 611:
                return WEATHER_ICON.HAIL;

            // Heavy sleet
            case 612:
                return WEATHER_ICON.HEAVY_HAIL;

            // Snow shower
            case 621:
                return isDay ? WEATHER_ICON.SNOW_DAY : WEATHER_ICON.SNOW_NIGHT;

            // Heavy snow shower
            case 622:
                return WEATHER_ICON.SNOW;

            // Flurries
            case 623:
                return WEATHER_ICON.SNOW;

            // Mist
            case 700:
                return WEATHER_ICON.DUST;

            // Smoke
            case 711:
                return WEATHER_ICON.SMOKE;

            // Haze
            case 721:
                return WEATHER_ICON.FOG;


            // Sand/dust
            case 731:
                return WEATHER_ICON.DUST;

            // Fog
            case 741:
                return WEATHER_ICON.FOG;

            // Freezing Fog
            case 751:
                return WEATHER_ICON.FOG;

            // Clear sky
            case 800:
                return isDay ? WEATHER_ICON.CLEAR_DAY : WEATHER_ICON.CLEAR_NIGHT;

            // Few clouds
            case 801:
                return isDay ? WEATHER_ICON.PARTLY_CLOUDY_DAY : WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            // Scattered clouds
            case 802:
                return isDay ? WEATHER_ICON.PARTLY_CLOUDY_DAY : WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            // Broken clouds
            case 803:
                return isDay ? WEATHER_ICON.MOSTLY_CLOUDY_DAY : WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;

            // Overcast clouds
            case 804:
                return WEATHER_ICON.CLOUDY;

            // Unknown Precipitation
            case 900:
                return WEATHER_ICON.RAIN_AND_SNOW;

            default:
                return WEATHER_ICON.NA;
        }

    }

    static String getDescription(Context context, String language, String desc, int icon) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        switch (icon) {

            // Thunderstorm with light rain
            case 200:
                return resources.getString(R.string.thunderstorm_with_light_rain);

            // Thunderstorm with rain
            case 201:
                return resources.getString(R.string.thunderstorm_with_rain);

            // Thunderstorm with heavy rain
            case 202:
                return resources.getString(R.string.thunderstorm_with_heavy_rain);

            // Thunderstorm with light drizzle
            case 230:
                return resources.getString(R.string.thunderstorm_with_light_drizzle);

            // Thunderstorm with drizzle
            case 231:
                return resources.getString(R.string.thunderstorm_with_drizzle);

            // Thunderstorm with heavy drizzle
            case 232:
                return resources.getString(R.string.thunderstorm_with_heavy_drizzle);

            // Thunderstorm with Hail
            case 233:
                return resources.getString(R.string.thunderstorm_with_hail);

            // Light Drizzle
            case 300:
                return resources.getString(R.string.light_drizzle);

            // Drizzle
            case 301:
                return resources.getString(R.string.drizzle);

            // Heavy Drizzle
            case 302:
                return resources.getString(R.string.heavy_drizzle);

            // Light Rain
            case 500:
                return resources.getString(R.string.light_rain);

            // Moderate Rain
            case 501:
                return resources.getString(R.string.moderate_rain);

            // Heavy Rain
            case 502:
                return resources.getString(R.string.heavy_rain);

            // Freezing rain
            case 511:
                return resources.getString(R.string.freezing_rain);

            // Light shower rain
            case 520:
                return resources.getString(R.string.light_shower_rain);

            // Shower rain
            case 521:
                return resources.getString(R.string.shower_rain);

            // Heavy shower rain
            case 522:
                return resources.getString(R.string.heavy_shower_rain);

            // Light snow
            case 600:
                return resources.getString(R.string.light_snow);

            // Snow
            case 601:
                return resources.getString(R.string.snow);

            // Heavy Snow
            case 602:
                return resources.getString(R.string.heavy_snow);

            // Mix snow/rain
            case 610:
                return resources.getString(R.string.rain_and_snow);

            // Sleet
            case 611:
                return resources.getString(R.string.sleet);

            // Heavy sleet
            case 612:
                return resources.getString(R.string.sleet);

            // Snow shower
            case 621:
                return resources.getString(R.string.shower_snow);

            // Heavy snow shower
            case 622:
                return resources.getString(R.string.heavy_shower_snow);

            // Flurries
            case 623:
                return resources.getString(R.string.flurries);

            // Mist
            case 700:
                return resources.getString(R.string.mist);

            // Smoke
            case 711:
                return resources.getString(R.string.smoke);

            // Haze
            case 721:
                return resources.getString(R.string.fog);

            // Sand/dust
            case 731:
                return resources.getString(R.string.mist);

            // Fog
            case 741:
                return resources.getString(R.string.fog);

            // Freezing Fog
            case 751:
                return resources.getString(R.string.freezing_fog);

            // Clear sky
            case 800:
                return resources.getString(R.string.clear_sky);

            // Few clouds
            case 801:
                return resources.getString(R.string.few_clouds);

            // Scattered clouds
            case 802:
                return resources.getString(R.string.few_clouds);

            // Broken clouds
            case 803:
                return resources.getString(R.string.cloudy);

            // Overcast clouds
            case 804:
                return resources.getString(R.string.cloudy);

            // Unknown Precipitation
            case 900:
                return resources.getString(R.string.unknown_precipitation);

            default:
                return resources.getString(R.string.weather_na);
        }
    }

}
