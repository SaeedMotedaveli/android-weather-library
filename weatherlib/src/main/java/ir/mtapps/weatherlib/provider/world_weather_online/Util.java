package ir.mtapps.weatherlib.provider.world_weather_online;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

class Util {

    private static final String[] SUPPORTED_LANGUAGE = {
            "ar", // Arabic
            "bn", // Bengali
            "bg", // Bulgarian
            "zh", // Chinese Simplified
            "zh_tw", // Chinese Traditional
            "cs", // Czech
            "da", // Danish
            "nl", // Dutch
            "fi", // Finnish
            "fr", // French
            "de", // German
            "el", // Greek
            "hi", // Hindi
            "hu", // Hungarian
            "it", // Italian
            "ja", // Japanese
            "jv", // Javanese
            "ko", // Korean
            "zh_cmn", // Mandarin
            "mr", // Marathi
            "pl", // Polish
            "pt", // Portuguese
            "pa", // Punjabi
            "ro", // Romanian
            "ru", // Russian
            "sr", // Serbian
            "si", // Sinhalese
            "sk", // Slovak
            "es", // Spanish
            "sv", // Swedish
            "ta", // Tamil
            "te", // Telugu
            "tr", // Turkish
            "uk", // Ukrainian
            "ur", // Urdu
            "vi", // Vietnamese
            "zh_wuu", // Wu (Shanghainese)
            "zh_hsn", // Xiang
            "zh_yue", // Yue (Cantonese)
            "zu" // Zulu
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
            case 395:   // Moderate or heavy snow in area with thunder
            case 338:   // Heavy snow
                return WEATHER_ICON.SNOW;
            case 335:   // Patchy heavy snow
            case 332:   // Moderate snow
            case 329:   // Patchy moderate snow
                return WEATHER_ICON.SNOW;
            case 392:   // Patchy light snow in area with thunder
                return WEATHER_ICON.LIGHT_SNOW;
            case 389:  // Moderate or heavy rain in area with thunder
                if (isDay)
                    return WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY;
                else
                    return WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;
            case 386:   // Patchy light rain in area with thunder
                return WEATHER_ICON.LIGHT_RAIN;
            case 377:   // Moderate or heavy showers of ice pellets
            case 374:   // Light showers of ice pellets
            case 350:   // Ice pellets
                return WEATHER_ICON.HAIL;
            case 371:   // Moderate or heavy snow showers
            case 230:   // Blizzard
                return WEATHER_ICON.SNOW;
            case 620:   // Light snow showers
            case 621:   // Light snow
            case 326:
                return WEATHER_ICON.LIGHT_SNOW;
            case 323:   // Patchy light snow
            case 227:   // Blowing snow
            case 179:   // Patchy snow nearby
                return WEATHER_ICON.SNOW;
            case 365:   // Moderate or heavy sleet showers
            case 362:   // Light sleet showers
            case 182:   // Patchy sleet nearby
                return WEATHER_ICON.HEAVY_HAIL;
            case 320:   // Moderate or heavy sleet
            case 317:   // Light sleet
                return WEATHER_ICON.HEAVY_HAIL;
            case 359:   // Torrential rain shower
            case 356:   // Moderate or heavy rain shower
                return WEATHER_ICON.SHOWER_RAIN;
            case 353:  // Light rain shower
                return WEATHER_ICON.SHOWER_RAIN;
            case 314:   // Moderate or Heavy freezing rain
            case 311:   // Light freezing rain
                return WEATHER_ICON.COLD;
            case 308:   // Heavy rain
            case 305:   // heavy rain at times
            case 302:   // Moderate rain
            case 299:   // Moderate rain at times
                return WEATHER_ICON.RAIN;
            case 296:   // Light rain
            case 293:   // Patchy light rain
            case 176:   // Patchy rain nearby
                return WEATHER_ICON.LIGHT_RAIN;
            case 284:   // Heavy freezing drizzle
            case 281:   // Freezing drizzle
            case 185:   // Patchy freezing drizzle nearby
                return WEATHER_ICON.COLD;
            case 266:   // Light drizzle
            case 263:   // Patchy light drizzle
                return WEATHER_ICON.SHOWER_RAIN;
            case 260:   // Freezing fog
            case 248:   // fog
            case 143:   // Mist
                return WEATHER_ICON.FOG;
            case 200:   // Thundery outbreaks in nearby
                return isDay ? WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY : WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            case 122:   // Overcast
                return isDay ? WEATHER_ICON.MOSTLY_CLOUDY_DAY : WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;
            case 119:   // Cloudy
                return WEATHER_ICON.CLOUDY;
            case 116:  // Partly Cloudy
                if (isDay)
                    return WEATHER_ICON.MOSTLY_CLOUDY_DAY;
                else
                    return WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;
            case 113:  // Clear/Sunny
                if (isDay)
                    return WEATHER_ICON.CLEAR_DAY;
                else
                    return WEATHER_ICON.CLEAR_NIGHT;

            default:
                return WEATHER_ICON.NA;
        }

    }

    static String getDescription(Context context, String language, int id, String desc) {

        // Check for supported language
        if (Arrays.binarySearch(SUPPORTED_LANGUAGE, language.toLowerCase()) >= 0) {
            return desc;
        }

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        switch (id) {
            case 395:   // Moderate or heavy snow in area with thunder
                return resources.getString(R.string.moderate_or_heavy_snow_with_thunder);

            case 338:   // Heavy snow
                return resources.getString(R.string.heavy_snow);

            case 335:   // Patchy heavy snow
                return resources.getString(R.string.patchy_heavy_snow);

            case 332:   // Moderate snow
                return resources.getString(R.string.snow);

            case 329:   // Patchy moderate snow
                return resources.getString(R.string.patchy_moderate_snow);

            case 392:   // Patchy light snow in area with thunder
                return resources.getString(R.string.patchy_light_snow_thunder);

            case 389:  // Moderate or heavy rain in area with thunder
                return resources.getString(R.string.heavy_shower_rain);

            case 386:   // Patchy light rain in area with thunder
                return resources.getString(R.string.patchy_light_rain_with_thunder);

            case 377:   // Moderate or heavy showers of ice pellets
                return resources.getString(R.string.hail);

            case 374:   // Light showers of ice pellets
                return resources.getString(R.string.hail);

            case 350:   // Ice pellets
                return resources.getString(R.string.hail);

            case 371:   // Moderate or heavy snow showers
                return resources.getString(R.string.shower_snow);

            case 230:   // Blizzard
                return resources.getString(R.string.blizzard);

            case 620:   // Light snow showers
                return resources.getString(R.string.shower_snow);

            case 621:   // Light snow
                return resources.getString(R.string.light_snow);

            case 326:
                return resources.getString(R.string.light_snow);

            case 323:   // Patchy light snow
                return resources.getString(R.string.light_snow);

            case 227:   // Blowing snow
                return resources.getString(R.string.snow);

            case 179:   // Patchy snow nearby
                return resources.getString(R.string.patchy_moderate_snow);

            case 365:   // Moderate or heavy sleet showers
                return resources.getString(R.string.moderate_or_heavy_sleet_showers);

            case 362:   // Light sleet showers
                return resources.getString(R.string.sleet);

            case 182:   // Patchy sleet nearby
                return resources.getString(R.string.sleet);

            case 320:   // Moderate or heavy sleet
                return resources.getString(R.string.sleet);

            case 317:   // Light sleet
                return resources.getString(R.string.light_sleet);

            case 359:   // Torrential rain shower
                return resources.getString(R.string.heavy_shower_rain);

            case 356:   // Moderate or heavy rain shower
                return resources.getString(R.string.heavy_shower_rain);

            case 353:  // Light rain shower
                return resources.getString(R.string.light_shower_rain);

            case 314:   // Moderate or Heavy freezing rain
                return resources.getString(R.string.freezing_rain);

            case 311:   // Light freezing rain
                return resources.getString(R.string.freezing_rain);

            case 308:   // Heavy rain
                return resources.getString(R.string.heavy_rain);

            case 305:   // heavy rain at times
                return resources.getString(R.string.heavy_rain);

            case 302:   // Moderate rain
                return resources.getString(R.string.moderate_rain);

            case 299:   // Moderate rain at times
                return resources.getString(R.string.moderate_rain);

            case 296:   // Light rain
                return resources.getString(R.string.light_rain);

            case 293:   // Patchy light rain
                return resources.getString(R.string.light_rain);

            case 176:   // Patchy rain nearby
                return resources.getString(R.string.moderate_rain);

            case 284:   // Heavy freezing drizzle
                return resources.getString(R.string.heavy_freezing_drizzle);

            case 281:   // Freezing drizzle
                return resources.getString(R.string.freezing_drizzle);

            case 185:   // Patchy freezing drizzle nearby
                return resources.getString(R.string.freezing_drizzle);

            case 266:   // Light drizzle
                return resources.getString(R.string.light_drizzle);

            case 263:   // Patchy light drizzle
                return resources.getString(R.string.light_drizzle);

            case 260:   // Freezing fog
                return resources.getString(R.string.freezing_fog);

            case 248:   // fog
                return resources.getString(R.string.fog);

            case 143:   // Mist
                return resources.getString(R.string.mist);

            case 200:   // Thundery outbreaks in nearby
                return resources.getString(R.string.thunderstorm);

            case 122:   // Overcast
                return resources.getString(R.string.cloudy);

            case 119:   // Cloudy
                return resources.getString(R.string.cloudy);

            case 116:  // Partly Cloudy
                return resources.getString(R.string.few_clouds);

            case 113:  // Clear/Sunny
                return resources.getString(R.string.clear_sky);

            default:
                return resources.getString(R.string.weather_na);
        }

    }

    static float getMoonPhase(int illumination, String phase) {

        boolean lessOrEqual14 = true;

        if (phase.equalsIgnoreCase("New Moon") ||
                phase.equalsIgnoreCase("Waxing Crescent") ||
                phase.equalsIgnoreCase("First Quarter") ||
                phase.equalsIgnoreCase("Waxing Gibbous") ||
                phase.equalsIgnoreCase("Full Moon")) {
            lessOrEqual14 = false;
        }

        if (lessOrEqual14) {
            return illumination / 200f;
        } else {
            return 0.5f + (100 - illumination) / 200f;
        }

    }
}
