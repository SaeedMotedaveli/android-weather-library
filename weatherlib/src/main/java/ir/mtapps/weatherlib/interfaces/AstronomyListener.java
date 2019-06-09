package ir.mtapps.weatherlib.interfaces;

import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;

public interface AstronomyListener extends WeatherListener {
    void onSuccessful(City city, Astronomy astronomy);
}
