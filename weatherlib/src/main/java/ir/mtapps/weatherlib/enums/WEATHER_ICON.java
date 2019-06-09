package ir.mtapps.weatherlib.enums;

public enum WEATHER_ICON {

    TORNADO("tornado"),
    HURRICANE("hurricane"),

    THUNDERSTORM("thunderstorm"),
    THUNDERSTORM_DAY("day_thunderstorm"),
    THUNDERSTORM_NIGHT("night_thunderstorm"),

    SNOW_DAY("day_snow"),
    SNOW_NIGHT("night_snow"),
    SNOW("snow"),
    SNOW_LIGHT("snow_light"),
    SNOW_WIND("snow_wind"),

    RAIN_DAY("day_rain"),
    RAIN_NIGHT("night_rain"),
    RAIN("rain"),
    RAIN_LIGHT("rain_light"),
    SHOWERS("showers"),

    SUNNY("day_sunny"),
    PARTLY_CLOUDY_DAY("day_cloudy"),
    MOSTLY_CLOUDY_DAY("day_sunny_overcast"),

    CLEAR_NIGHT("night_clear"),
    PARTLY_CLOUDY_NIGHT("night_cloudy"),
    MOSTLY_CLOUDY_NIGHT("night_clear_overcast"),

    COLD("snowflake_cold"),
    HOT("hot"),

    RAIN_AND_SNOW("rain_mix"),

    FOG("fog"),
    HAIL("hail"),
    DUST("dust"),
    SMOKE("smoke"),

    WINDY("windy"),

    CLOUDY("cloudy"),
    CLOUDY_GUSTS("cloudy_gusts"),

    SLEET("sleet"),

    NA("na");

    private String name;

    WEATHER_ICON(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
