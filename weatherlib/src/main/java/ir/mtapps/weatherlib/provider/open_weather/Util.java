package ir.mtapps.weatherlib.provider.open_weather;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

class Util {

    private static final String[] SUPPORTED_LANGUAGE = {
            "ar", //Arabic
            "bg", // Bulgarian
            "ca", // Catalan
            "cz", // Czech
            "de", // German
            "el", // Greek
            "en", // English
            "fa", // Persian (Farsi)
            "fi", // Finnish
            "fr", // French
            "gl", // Galician
            "hr", // Croatian
            "hu", // Hungarian
            "it", // Italian
            "ja", // Japanese
            "kr", // Korean
            "la", // Latvian
            "lt", // Lithuanian
            "mk", // Macedonian
            "nl", // Dutch
            "pl", // Polish
            "pt", // Portuguese
            "ro", // Romanian
            "ru", // Russian
            "se", // Swedish
            "sk", // Slovak
            "sl", // Slovenian
            "es", // Spanish
            "tr", // Turkish
            "ua", // Ukrainian
            "vi", // Vietnamese
            "zh_cn", // Chinese Simplified
            "zh_tw" // Chinese Traditional
    };

    static String getLanguageForUrl(String language) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return language;
        } else {
            return "en";
        }

    }

    static WEATHER_ICON getIcon(int id, boolean isDay) {
        switch (id) {
            case 200:
            case 201:
            case 202:
                return isDay ? WEATHER_ICON.THUNDERSTORM_DAY : WEATHER_ICON.THUNDERSTORM_NIGHT;
            case 210:
                return WEATHER_ICON.THUNDERSTORM;
            case 211:
                return WEATHER_ICON.THUNDERSTORM;
            case 212:
                return WEATHER_ICON.THUNDERSTORM;
            case 221:
                return WEATHER_ICON.THUNDERSTORM;
            case 230:
            case 231:
            case 232:
                return isDay ? WEATHER_ICON.THUNDERSTORM_DAY : WEATHER_ICON.THUNDERSTORM_NIGHT;
            case 300:
            case 301:
                return WEATHER_ICON.RAIN;
            case 302:
            case 311:
            case 312:
            case 314:
                return isDay ? WEATHER_ICON.RAIN_DAY : WEATHER_ICON.RAIN_NIGHT;
            case 310:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 313:
                return WEATHER_ICON.SHOWERS;
            case 321:
                return WEATHER_ICON.RAIN;
            case 500:
                return WEATHER_ICON.RAIN;
            case 501:
            case 502:
            case 503:
            case 504:
                return isDay ? WEATHER_ICON.RAIN_DAY : WEATHER_ICON.RAIN_NIGHT;
            case 511:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 520:
                return WEATHER_ICON.SHOWERS;
            case 521:
                return WEATHER_ICON.SHOWERS;
            case 522:
                return WEATHER_ICON.SHOWERS;
            case 531:
                return isDay ? WEATHER_ICON.THUNDERSTORM_DAY : WEATHER_ICON.THUNDERSTORM_NIGHT;
            case 600:
                return WEATHER_ICON.SNOW;
            case 601:
                return WEATHER_ICON.SNOW;
            case 602:
                return WEATHER_ICON.SLEET;
            case 611:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 612:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 615:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 616:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 620:
                return WEATHER_ICON.RAIN_AND_SNOW;
            case 621:
                return WEATHER_ICON.SNOW;
            case 622:
                return WEATHER_ICON.SNOW;
            case 701:
                return WEATHER_ICON.SHOWERS;
            case 711:
                return WEATHER_ICON.SMOKE;
            case 721:
                return WEATHER_ICON.FOG;
            case 731:
                return WEATHER_ICON.DUST;
            case 741:
                return WEATHER_ICON.FOG;
            case 761:
                return WEATHER_ICON.DUST;
            case 762:
                return WEATHER_ICON.DUST;
            case 771:
                return WEATHER_ICON.CLOUDY_GUSTS;
            case 781:
                return WEATHER_ICON.TORNADO;
            case 800:
                return isDay ? WEATHER_ICON.SUNNY : WEATHER_ICON.CLEAR_NIGHT;
            case 801:
                return WEATHER_ICON.CLOUDY_GUSTS;
            case 802:
                return WEATHER_ICON.CLOUDY_GUSTS;
            case 803:
                return WEATHER_ICON.CLOUDY_GUSTS;
            case 804:
                return isDay ? WEATHER_ICON.MOSTLY_CLOUDY_DAY : WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;
            case 900:
                return WEATHER_ICON.TORNADO;
            case 901:
                return WEATHER_ICON.THUNDERSTORM;
            case 902:
                return WEATHER_ICON.HURRICANE;
            case 903:
                return WEATHER_ICON.COLD;
            case 904:
                return WEATHER_ICON.HOT;
            case 905:
                return WEATHER_ICON.WINDY;
            case 906:
                return WEATHER_ICON.HAIL;
            case 957:
                return WEATHER_ICON.WINDY;
            default:
                return WEATHER_ICON.NA;
        }
    }

    static String getDescription(Context context, String language, String desc, int id) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        switch (id) {
            case 212:   // thunderstorm
            case 200:
            case 210:
            case 211:
            case 232:
            case 221:
            case 201:
            case 202:
            case 230:
            case 231:
                return resources.getString(R.string.thunderstorm);

            case 300:   // light intensity drizzle
            case 301:   // drizzle
            case 310:   // light intensity drizzle rain
                return resources.getString(R.string.light_drizzle);

            case 311:    // drizzle rain
            case 313:    // shower rain and drizzle
                return resources.getString(R.string.drizzle);

            case 302:    // heavy intensity drizzle
            case 312:    // heavy intensity drizzle rain
                return resources.getString(R.string.heavy_drizzle);

            case 314:    // heavy shower rain and drizzle
            case 321:    // shower drizzle
                return resources.getString(R.string.shower_rain);

            case 500:   // light rain
                return resources.getString(R.string.light_rain);

            case 501:   // moderate rain
                return resources.getString(R.string.moderate_rain);

            case 502:   // heavy intensity rain
            case 503:   // very heavy rain
            case 504:   // extreme rain
                return resources.getString(R.string.heavy_rain);

            case 511:   // freezing rain
                return resources.getString(R.string.freezing_rain);

            case 520:    // light intensity shower rain
            case 521:    // shower rain
            case 531:    // ragged shower rain
                return resources.getString(R.string.shower_rain);

            case 522:   // heavy intensity shower rain
                return resources.getString(R.string.heavy_shower_rain);

            case 600:   // light snow
                return resources.getString(R.string.light_snow);

            case 601:   // snow
                return resources.getString(R.string.snow);

            case 602:   // heavy snow
                return resources.getString(R.string.heavy_snow);

            case 611:    // sleet
            case 612:    // shower sleet
                return resources.getString(R.string.sleet);

            case 615:    // light rain and snow
            case 616:    // rain and snow
                return resources.getString(R.string.rain_and_snow);

            case 620:    // light shower snow
            case 621:    // shower snow
                return resources.getString(R.string.shower_snow);

            case 622:   // heavy shower snow
                return resources.getString(R.string.heavy_shower_snow);

            case 701:   // mist
            case 731:    // sand, dust whirls
            case 751:    // sand
            case 761:    // dust
                return resources.getString(R.string.mist);

            case 741:   // fog
            case 721:    // haze
                return resources.getString(R.string.fog);

            case 711:    // smoke
                return resources.getString(R.string.smoke);

            case 762:    // volcanic ash
                return resources.getString(R.string.volcanic_ash);

            case 771:   // squalls
            case 902:   // hurricane
            case 957:    // high wind, near gale
            case 958:    // gale
            case 959:    // severe gale
            case 960:    // storm
            case 961:    // violent storm
            case 962:   // hurricane
                return resources.getString(R.string.hurricane);

            case 781:   // tornado
            case 900:   // tornado
                return resources.getString(R.string.tornado);

            case 800:  // clear sky
            case 951:  // calm
                return resources.getString(R.string.clear_sky);

            case 801:   // few clouds
            case 802:  // scattered clouds
            case 803:   // broken clouds
                return resources.getString(R.string.few_clouds);

            case 804:   // overcast clouds
                return resources.getString(R.string.cloudy);

            case 901:   // tropical storm
                return resources.getString(R.string.tropical_storm);

            case 903:   // cold
                return resources.getString(R.string.cold);

            case 904:   // hot
                return resources.getString(R.string.hot);

            case 905:   // windy
                return resources.getString(R.string.windy);

            case 906:   // hail
                return resources.getString(R.string.hail);

            case 952:    // light breeze
            case 953:    // gentle breeze
                return resources.getString(R.string.light_breeze);

            case 954:    // moderate breeze
            case 955:    // fresh breeze
                return resources.getString(R.string.moderate_breeze);

            case 956:    // strong breeze
                return resources.getString(R.string.strong_breeze);

            default:
                return resources.getString(R.string.weather_na);
        }
    }
}
