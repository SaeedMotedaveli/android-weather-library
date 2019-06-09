package ir.mtapps.weatherlib.interfaces;

import java.util.List;

import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.DailyWeather;

public interface DailyWeatherListener extends WeatherListener {
    void onSuccessful(City city, List<DailyWeather> dailyWeatherList);
}
