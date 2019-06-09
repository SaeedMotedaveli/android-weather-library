package ir.mtapps.weatherlib.interfaces;

import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;

public interface CurrentWeatherListener extends WeatherListener {
    void onSuccessful(City city, CurrentWeather weather);
}
