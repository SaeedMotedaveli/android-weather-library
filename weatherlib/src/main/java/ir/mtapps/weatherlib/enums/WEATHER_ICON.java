package ir.mtapps.weatherlib.enums;

import androidx.annotation.DrawableRes;

import ir.mtapps.weatherlib.R;

// Icon source: https://www.iconfinder.com/iconsets/the-weather-is-nice-today
public enum WEATHER_ICON {

    CLEAR_DAY("clear_day", "1000d", R.drawable.ic_clear_day),
    CLEAR_NIGHT("clear_night", "1000n", R.drawable.ic_clear_night),

    PARTLY_CLOUDY_DAY("partly_cloudy_day", "1100d", R.drawable.ic_partly_cloudy_day),
    PARTLY_CLOUDY_NIGHT("partly_cloudy_night", "1100n", R.drawable.ic_partly_cloudy_night),

    MOSTLY_CLOUDY_DAY("mostly_cloudy_day", "1200d", R.drawable.ic_mostly_cloudy_day),
    MOSTLY_CLOUDY_NIGHT("mostly_cloudy_night", "1200n", R.drawable.ic_mostly_cloudy_night),

    CLOUDY("cloudy", "1300", R.drawable.ic_cloudy),

    CLOUDY_WINDY("cloudy_windy", "1400", R.drawable.ic_cloudy_windy),
    CLOUDY_WINDY_DAY("cloudy_windy_day", "1400d", R.drawable.ic_cloudy_windy_day),
    CLOUDY_WINDY_NIGHT("cloudy_windy_night", "1400n", R.drawable.ic_cloudy_windy_night),

    FOG("fog", "1500", R.drawable.ic_fog),
    FOG_DAY("fog_day", "1500d", R.drawable.ic_fog_day),
    FOG_NIGHT("fog_night", "1500n", R.drawable.ic_fog_night),

    CLOUDY_FOG("cloudy_fog", "1600", R.drawable.ic_cloudy_fog),
    CLOUDY_FOG_DAY("cloudy_fog_day", "1600d", R.drawable.ic_cloudy_fog_day),
    CLOUDY_FOG_NIGHT("cloudy_fog_night", "1600n", R.drawable.ic_cloudy_fog_night),

    DRIZZLE("drizzle", "1700", R.drawable.ic_drizzle),
    LIGHT_RAIN("light_rain", "1800", R.drawable.ic_light_rain),
    LIGHT_RAIN_DAY("light_rain_day", "1800d", R.drawable.ic_mostly_cloudy_light_rain_day),
    LIGHT_RAIN_NIGHT("light_rain_night", "1800n", R.drawable.ic_mostly_cloudy_light_rain_night),
    RAIN("rain", "1900", R.drawable.ic_rain),
    RAIN_DAY("rain_day", "1900d", R.drawable.ic_rain_day),
    RAIN_NIGHT("rain_night", "1900n", R.drawable.ic_rain_night),
    HEAVY_RAIN("heavy_rain", "2000", R.drawable.ic_heavy_rain),
    HEAVY_RAIN_DAY("heavy_rain_day", "2000d", R.drawable.ic_mostly_cloudy_heavy_rain_day),
    HEAVY_RAIN_NIGHT("heavy_rain_night", "2000n", R.drawable.ic_mostly_cloudy_heavy_rain_night),
    SHOWER_RAIN("shower_rain", "2100", R.drawable.ic_light_shower_rain),
    SHOWER_RAIN_DAY("shower_rain_day", "2100d", R.drawable.ic_light_shower_rain_day),
    SHOWER_RAIN_NIGHT("shower_rain_night", "2100n", R.drawable.ic_light_shower_rain_night),

    LIGHT_SNOW("light_snow", "2200", R.drawable.ic_light_snow),
    SNOW("snow", "2300", R.drawable.ic_snow),
    SNOW_DAY("snow_day", "2300d", R.drawable.ic_mostly_cloudy_snow_day),
    SNOW_NIGHT("snow_night", "2300n", R.drawable.ic_mostly_cloudy_snow_night),
    HEAVY_SNOW("heavy_snow", "2400", R.drawable.ic_heavy_snow),

    FREEZING_RAIN("freezing_rain", "2500", R.drawable.ic_freezing_rain),
    FREEZING_RAIN_DAY("freezing_rain_day", "2500d", R.drawable.ic_freezing_rain_day),
    FREEZING_RAIN_NIGHT("freezing_rain_night", "2500n", R.drawable.ic_freezing_rain_night),

    THUNDERSTORM("thunderstorm", "2600", R.drawable.ic_thunderstorm),
    THUNDERSTORM_WITH_RAIN("thunderstorm_with_rain", "2700", R.drawable.ic_light_rain_thunderstorm),
    THUNDERSTORM_WITH_RAIN_DAY("thunderstorm_with_rain_day", "2700d", R.drawable.ic_light_rain_thunderstorm_day),
    THUNDERSTORM_WITH_RAIN_NIGHT("thunderstorm_with_rain_night", "2700n", R.drawable.ic_light_rain_thunderstorm_night),

    HAIL("hail", "2800", R.drawable.ic_hail),
    HAIL_DAY("hail_day", "2800d", R.drawable.ic_hail_day),
    HAIL_NIGHT("hail_night", "2800n", R.drawable.ic_hail_night),
    HEAVY_HAIL("heavy_hail", "2900", R.drawable.ic_heavy_hail),

    RAIN_AND_SNOW("rain_and_snow", "3000", R.drawable.ic_rain_snow),
    SNOW_AND_HAIL("snow_and_hail", "3100", R.drawable.ic_snow_hail),

    COLD("cold", "3200", R.drawable.ic_cold),
    HOT("hot", "3300", R.drawable.ic_hot),

    DUST("dust", "3400", R.drawable.ic_dust),
    SMOKE("smoke", "3500", R.drawable.ic_smoke),

    WINDY("windy", "3600", R.drawable.ic_windy),

    SNOW_AND_WINDY("snow_and_windy", "3700", R.drawable.ic_snow_windy),

    TORNADO("tornado", "3800", R.drawable.ic_tornado),

    VOLCANO_ASH("volcano_ash", "3900", R.drawable.ic_volcano_ash),

    NA("na", "0", R.drawable.ic_na);

    private final String name;
    private final String code;
    private final int iconRes;

    WEATHER_ICON(String name, String code, @DrawableRes int iconRes) {
        this.name = name;
        this.code = code;
        this.iconRes = iconRes;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    @DrawableRes
    public int getIconRes() {
        return this.iconRes;
    }
}
