package ir.mtapps.weatherlib;

import static ir.mtapps.weatherlib.Loging.log_e;
import static ir.mtapps.weatherlib.Loging.log_e_debug;
import static ir.mtapps.weatherlib.Loging.log_i_debug;
import static ir.mtapps.weatherlib.Loging.log_v_debug;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

import ir.mtapps.weatherlib.enums.PROVIDER;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.ResponseResult;
import ir.mtapps.weatherlib.provider.WeatherProvider;

class HttpClient extends WeatherClient {

    private interface JsonResponseListener {
        void onSuccessful(@NonNull String json, @Nullable String geo);
        void onError(int code, String message);
        default void onAllReceive(String geo, String currently, String hourly, String daily) {}
    }

    private static HttpClient mClient;

    // Weather Provider
    private WeatherProvider mWeatherProvider = null;

    // Values
    private boolean mUseAutoCoordinate;

    private WeatherProvider.Params mParams;

    static HttpClient getInstance(PROVIDER provider,
                                  boolean autoCoordinate,
                                  WeatherProvider.Params params) {

        if (mClient == null) {
            mClient = new HttpClient();
        }

        mClient.init(provider, autoCoordinate, params);

        return mClient;
    }

    private void init(PROVIDER provider,
                      boolean autoCoordinate,
                      WeatherProvider.Params params) {

        mUseAutoCoordinate = autoCoordinate;
        mParams = params;

        switch (provider) {
            case OPEN_WEATHER:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.open_weather.Provider();
                break;

            case DARK_SKY:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.dark_sky.Provider();
                break;

            case WORLD_WEATHER_ONLINE:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.world_weather_online.Provider();
                break;

            case ACCUWEATHER:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.accuweather.Provider();
                break;

            case WEATHERBIT:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.weatherbit.Provider();
                break;

            case VISUAL_CROSSING:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.visualcrossing.Provider();
                break;

            case TOMORROW:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.tomorrow.Provider();
                break;

            case AERIS_WEATHER:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.aeris_weather.Provider();
                break;

            default:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.open_weather.Provider();
        }

        mWeatherProvider.setParams(params);
    }


    // ********************************************************************************************
    // *                                Get Weather Methods
    // ********************************************************************************************

    @Override
    public void allWeather(@NonNull Context context, @NonNull final AllWeatherListener listener) {

        getJson(context, WeatherProvider.URL.ALL, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.allWeather(context, json, geo, listener);

            }

            @Override
            public void onAllReceive(String geo, String currently, String hourly, String daily) {

                mWeatherProvider.allWeather(context, currently, hourly, daily, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    @Override
    public void currentCondition(@NonNull Context context, @NonNull final CurrentWeatherListener listener) {

        getJson(context, WeatherProvider.URL.CURRENTLY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.currentCondition(context, json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    @Override
    public void todayAstronomy(@NonNull Context context, @NonNull final AstronomyListener listener) {

        getJson(context, WeatherProvider.URL.ASTRONOMY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.todayAstronomy(context, json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    @Override
    public void hourlyWeather(@NonNull Context context, @NonNull final HourlyWeatherListener listener) {

        getJson(context, WeatherProvider.URL.HOURLY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.hourlyWeather(context, json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    @Override
    public void dailyWeather(@NonNull Context context, @NonNull final DailyWeatherListener listener) {

        getJson(context, WeatherProvider.URL.DAILY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.dailyWeather(context, json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    // ********************************************************************************************
    // *                                Get JSON from Cache or Server                             *
    // ********************************************************************************************

    // STEP 1: Check for user location

    @SuppressLint("MissingPermission")
    private void getJson(Context context, final WeatherProvider.URL urlType, final JsonResponseListener listener) {

        log_v_debug("Start getting json...");

        if (mParams.apiKey == null) {

            log_e("API key is null.");

            Resources resources = Util.getLocalizedResources(context, new Locale(mParams.config.getLanguage()));
            listener.onError(Error.API_KEY_REQUIRE, resources.getString(R.string.error_api_key_required));
            return;
        }


        if (mUseAutoCoordinate) {

            log_v_debug("Use gps to get data form provider...");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                log_e("Location permission denied.");

                Resources resources = Util.getLocalizedResources(context, new Locale(mParams.config.getLanguage()));
                listener.onError(Error.LOCATION_PERMISSION_DENIED,
                        resources.getString(R.string.location_permission_denied));
                return;
            }


            FusedLocationProviderClient fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(context);


            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {

                            log_i_debug("Location received from gps: [" + location.getLatitude()
                                    + ", " + location.getLongitude() + "]");

                            mWeatherProvider.setCoordinate(location.getLatitude(), location.getLongitude());

                            checkForNeedGeoRequest(context, urlType, listener);

                        } else {

                            log_e("GPS location that received from getLastLocation() is null.");

                            Resources resources = Util.getLocalizedResources(context, new Locale(mParams.config.getLanguage()));
                            listener.onError(Error.LOCATION_ERROR, resources.getString(R.string.error_location));
                        }
                    })
                    .addOnFailureListener(e -> {
                        log_e("GPS location failed.");
                        Resources resources = Util.getLocalizedResources(context, new Locale(mParams.config.getLanguage()));
                        listener.onError(Error.LOCATION_ERROR, resources.getString(R.string.error_location));
                    });

        } else {

            checkForNeedGeoRequest(context, urlType, listener);

        }

    }

    // STEP 2: Check for need geo

    private void checkForNeedGeoRequest(Context context, WeatherProvider.URL urlType, JsonResponseListener listener) {

        if (mWeatherProvider.isGeoRequestRequired()) {

            log_v_debug("Provider api need geo key...");

            getGeo(context, urlType, listener);

        } else {

            getJsonFromServer(context, urlType, null, listener);

        }

    }

    // STEP 3: If geo required -> edit geo

    private void getGeo(Context context, final WeatherProvider.URL urlType, final JsonResponseListener listener) {

        String geoUrl = mWeatherProvider.getUrl(WeatherProvider.URL.GEO);

        log_v_debug("Get geo from server: " + geoUrl);

        sendRequest(context, geoUrl, null, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String geo, String ignore) {

                log_i_debug("Geo received successfully from server.");

                getJsonFromServer(context, urlType, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                log_e("Error on getting geo from server.");

                listener.onError(code, message);

            }
        });

    }

    // STEP 4: edit geo from cache or server

    private void getJsonFromServer(Context context,
                                   WeatherProvider.URL urlType,
                                   String geo,
                                   JsonResponseListener listener) {

        log_v_debug("Get json from cache or server.");

        if (urlType == WeatherProvider.URL.ALL) {
            getMultiJsonFromServer(context, geo, listener);
        } else {
            getSingleJsonFromServer(context, urlType, geo, listener);
        }

    }

    private void getSingleJsonFromServer(Context context,
                                         WeatherProvider.URL urlType,
                                         String geo,
                                         JsonResponseListener listener) {

        String url;

        if (geo != null && !geo.isEmpty()) {
            url = mWeatherProvider.getUrl(urlType, geo);
        } else {
            url = mWeatherProvider.getUrl(urlType);
        }

        sendRequest(context, url, geo, listener);
    }

    private void getMultiJsonFromServer(Context context,
                                        String geo,
                                        final JsonResponseListener listener) {

        if (mWeatherProvider.isSupportAllInOneUrl()) {

            log_v_debug("---> SINGLE REQUEST <---");

            getSingleJsonFromServer(context, WeatherProvider.URL.CURRENTLY, geo, listener);

        } else {

            log_v_debug("---> MULTI REQUEST <---");

            String currentlyUrl;
            final String hourlyUrl;
            final String dailyUrl;

            if (geo != null && !geo.isEmpty()) {
                currentlyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.CURRENTLY, geo);
                hourlyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.HOURLY, geo);
                dailyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.DAILY, geo);
            } else {
                currentlyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.CURRENTLY);
                hourlyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.HOURLY);
                dailyUrl = mWeatherProvider.getUrl(WeatherProvider.URL.DAILY);
            }

            sendMultiRequest(context, currentlyUrl, hourlyUrl, dailyUrl, geo, listener);
        }

    }

    // Send request by volley

    private void sendRequest(Context context,
                             String url,
                             final String geo,
                             final JsonResponseListener listener) {

        log_v_debug("Send request: " + url);

        VolleySingletone volley = VolleySingletone.getInstance(context);

        // Cancel all previous requests
        volley.cancelAll();

        // Get string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                response -> {

                    ResponseResult result = mWeatherProvider.checkForJsonValidity(context, response);

                    if (result.isSuccessful() && result.getCode() == 200) {

                        log_i_debug("Json received from server successfully.");

                        listener.onSuccessful(response, geo);

                    } else {

                        log_e("result not successful.");

                        listener.onError(result.getCode(), result.getMessage());

                    }
                },
                error -> {

                    log_e(error.getMessage());

                    listener.onError(Error.NETWORK_ERROR, error.getMessage());
                }
        );

        // Add request
        volley.addToRequestQueue(stringRequest);

    }

    // Send multi request
    private void sendMultiRequest(Context context,
                                  String currentlyUrl,
                                  final String hourlyUrl,
                                  final String dailyUrl,
                                  final String geo,
                                  final JsonResponseListener listener) {

        sendRequest(context, currentlyUrl, geo, new JsonResponseListener() {

            @Override
            public void onSuccessful(@NonNull final String currently, @Nullable String geo) {

                log_i_debug("Currently weather data received successfully.");

                sendRequest(context, hourlyUrl, geo, new JsonResponseListener() {

                    @Override
                    public void onSuccessful(@NonNull final String hourly, @Nullable String geo) {

                        log_i_debug("Hourly weather data received successfully.");

                        sendRequest(context, dailyUrl, geo, new JsonResponseListener() {
                            @Override
                            public void onSuccessful(@NonNull String daily, @Nullable String geo) {

                                log_i_debug("Daily weather data received successfully.");

                                listener.onAllReceive(geo, currently, hourly, daily);

                            }

                            @Override
                            public void onError(int code, String message) {

                                log_e_debug("Error on getting daily weather data.");
                                log_e(message);

                                listener.onError(code, message);

                            }
                        });

                    }

                    @Override
                    public void onError(int code, String message) {

                        log_e_debug("Error on getting hourly weather data.");
                        log_e(message);

                        listener.onError(code, message);

                    }

                });

            }

            @Override
            public void onError(int code, String message) {

                log_e_debug("Error on getting currently weather data.");
                log_e(message);

                listener.onError(code, message);

            }

        });
    }
}
