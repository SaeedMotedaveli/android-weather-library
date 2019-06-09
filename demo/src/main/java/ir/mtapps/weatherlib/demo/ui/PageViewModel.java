package ir.mtapps.weatherlib.demo.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ir.mtapps.weatherlib.model.AirQuality;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class PageViewModel extends ViewModel {

    private final MutableLiveData<Void> mClearLiveData = new MutableLiveData<>();

    private final MutableLiveData<CurrentWeather> mCurrentWeatherLiveData = new MutableLiveData<>();
    private final MutableLiveData<City> mCityLiveData = new MutableLiveData<>();
    private final MutableLiveData<Astronomy> mAstronomyLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<HourlyWeather>> mHourlyWeatherLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DailyWeather>> mDailyWeatherLiveData = new MutableLiveData<>();
    private final MutableLiveData<AirQuality> mAirQualityLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> mErrorLiveData = new MutableLiveData<>();

    void clear() {
        mClearLiveData.setValue(null);
    }

    MutableLiveData<Void> getClear() {
        return mClearLiveData;
    }

    void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeatherLiveData.setValue(currentWeather);
    }

    LiveData<CurrentWeather> getCurrentWeather() {
        return mCurrentWeatherLiveData;
    }

    void setCity(City city) {
        mCityLiveData.setValue(city);
    }

    MutableLiveData<City> getCity() {
        return mCityLiveData;
    }

    void setTodayAstronomy(Astronomy astronomy) {
        mAstronomyLiveData.setValue(astronomy);
    }

    MutableLiveData<Astronomy> getAstronomy() {
        return mAstronomyLiveData;
    }

    void setHourlyWeather(List<HourlyWeather> hourlyWeather) {
        mHourlyWeatherLiveData.setValue(hourlyWeather);
    }

    MutableLiveData<List<HourlyWeather>> getHourlyWeather() {
        return mHourlyWeatherLiveData;
    }

    void setDailyWeather(List<DailyWeather> dailyWeather) {
        mDailyWeatherLiveData.setValue(dailyWeather);
    }

    MutableLiveData<List<DailyWeather>> getDailyWeather() {
        return mDailyWeatherLiveData;
    }

    void setAirQuality(AirQuality airQuality) {
        mAirQualityLiveData.setValue(airQuality);
    }

    MutableLiveData<AirQuality> getAirQuality() {
        return mAirQualityLiveData;
    }

    void setError(String error) {
        mErrorLiveData.setValue(error);
    }

    MutableLiveData<String> getError() {
        return mErrorLiveData;
    }
}