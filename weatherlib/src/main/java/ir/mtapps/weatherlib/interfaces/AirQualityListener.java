package ir.mtapps.weatherlib.interfaces;

import ir.mtapps.weatherlib.model.AirQuality;

public interface AirQualityListener extends WeatherListener {
    void onSuccessful(AirQuality airQuality);
}
