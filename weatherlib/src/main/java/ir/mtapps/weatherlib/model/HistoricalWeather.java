package ir.mtapps.weatherlib.model;

import ir.mtapps.weatherlib.enums.WEATHER_ICON;

public class HistoricalWeather {

    public long date;

    public WEATHER_ICON icon;
    public String description;

    public float temperature;
    public float pressure;
    public float humidity;

    public float windSpeed;
    public float windDegree;

    public int cloudsCover;
    public float precipitationAmount;
    public int precipitationProbably = 0;

}
