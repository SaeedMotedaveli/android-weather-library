package ir.mtapps.weatherlib.interfaces;

import java.util.List;

import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.HourlyWeather;

public interface HourlyWeatherListener extends WeatherListener {
    void onSuccessful(City city, List<HourlyWeather> hourlyWeatherList);
}
