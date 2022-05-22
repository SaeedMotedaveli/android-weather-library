package ir.mtapps.weatherlib.enums;

public enum PROVIDER {

    /** Dark Sky */
    DARK_SKY("Dark Sky"),

    /** Open Weather */
    OPEN_WEATHER("Open Weather"),

    /** World Weather Online */
    WORLD_WEATHER_ONLINE("World Weather Online"),

    /** AccuWeather */
    ACCUWEATHER("Accuweather"),

    /** Weatherbit */
    WEATHERBIT("Weatherbit"),

    /** Visual Crossing */
    VISUAL_CROSSING("Visual Crossing"),

    /** Tomorrow.io */
    TOMORROW("Tomorrow.io"),

    /** Aeris Weather */
    AERIS_WEATHER("Aeris Weather");

    private final String name;

    PROVIDER(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
