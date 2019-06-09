package ir.mtapps.weatherlib.interfaces;

import java.util.List;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

public interface AllWeatherListener extends WeatherListener {

    void onSuccessful(City city,
                      CurrentWeather weather,
                      Astronomy astronomy,
                      List<HourlyWeather> hourlyWeatherList,
                      List<DailyWeather> dailyWeatherList);

}
