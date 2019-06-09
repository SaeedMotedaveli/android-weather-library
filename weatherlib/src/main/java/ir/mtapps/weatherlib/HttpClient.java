package ir.mtapps.weatherlib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.database.Database;
import ir.mtapps.weatherlib.enums.PROVIDER;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.ResponseResult;
import ir.mtapps.weatherlib.provider.WeatherProvider;

import static ir.mtapps.weatherlib.Loging.*;

class HttpClient extends WeatherClient {

    private final static String CACHE_DATABASE_NAME = "wcache";

    private abstract class JsonResponseListener {
        abstract void onSuccessful(@NonNull String json, @Nullable String geo);

        abstract void onError(int code, String message);

        void onAllReceive(String geo, String currently, String hourly, String daily) {
        }
    }


    private static HttpClient mClient = null;

    // Weather Provider
    private WeatherProvider mWeatherProvider = null;

    // Cache database
    private Database db;
    private Cache mCache = null;

    // Values
    private Context mContext;
    private PROVIDER mProviderType;
    private boolean mUseAutoCoordinate;

    private WeatherProvider.Params mParams;

    static HttpClient getInstance(Context context,
                                  PROVIDER provider,
                                  boolean autoCoordinate,
                                  WeatherProvider.Params params) {
        if (mClient == null) {
            mClient = new HttpClient();
        }

        mClient.init(context, provider, autoCoordinate, params);

        return mClient;

    }

    private void init(Context context,
                      PROVIDER provider,
                      boolean autoCoordinate,
                      WeatherProvider.Params params) {

        mProviderType = provider;
        mContext = context.getApplicationContext();
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

            default:
                mWeatherProvider = new ir.mtapps.weatherlib.provider.open_weather.Provider();
        }

        mWeatherProvider.setContext(mContext);
        mWeatherProvider.setParams(params);

        mCache = null;
    }


    // ********************************************************************************************
    // *                                Get Weather Methods
    // ********************************************************************************************

    @Override
    public void allWeather(@NonNull final AllWeatherListener listener) {

        getJson(WeatherProvider.URL.ALL, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.allWeather(json, geo, listener);

            }

            @Override
            void onAllReceive(String geo, String currently, String hourly, String daily) {

                mWeatherProvider.allWeather(currently, hourly, daily, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onError(code, message);

            }
        });

    }

    @Override
    public void currentCondition(@NonNull final CurrentWeatherListener listener) {

        getJson(WeatherProvider.URL.CURRENTLY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.currentCondition(json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onError(code, message);

            }
        });

    }

    @Override
    public void todayAstronomy(@NonNull final AstronomyListener listener) {

        getJson(WeatherProvider.URL.ASTRONOMY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.todayAstronomy(json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onError(code, message);

            }
        });

    }

    @Override
    public void hourlyWeather(@NonNull final HourlyWeatherListener listener) {

        getJson(WeatherProvider.URL.HOURLY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.hourlyWeather(json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onError(code, message);

            }
        });

    }

    @Override
    public void dailyWeather(@NonNull final DailyWeatherListener listener) {

        getJson(WeatherProvider.URL.DAILY, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String json, String geo) {

                mWeatherProvider.dailyWeather(json, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                listener.onError(code, message);

            }
        });

    }

    // ********************************************************************************************
    // *                                Get JSON from Cache or Server                             *
    // ********************************************************************************************

    // STEP 1: Check for user location

    private void getJson(final WeatherProvider.URL urlType, final JsonResponseListener listener) {

        log_v_debug("Start getting json...");

        if (mParams.apiKey == null) {

            log_e("API key is null.");

            Resources resources = Util.getLocalizedResources(mContext, new Locale(mParams.config.getLanguage()));
            listener.onError(Error.API_KEY_REQUIRE, resources.getString(R.string.error_api_key_required));
            return;
        }


        if (mUseAutoCoordinate) {

            log_v_debug("Use gps to edit data form provider...");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && mContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                log_e("Location permission denied.");

                Resources resources = Util.getLocalizedResources(mContext, new Locale(mParams.config.getLanguage()));
                listener.onError(Error.LOCATION_PERMISSION_DENIED,
                        resources.getString(R.string.location_permission_denied));
                return;
            }


            FusedLocationProviderClient fusedLocationClient =
                    LocationServices.getFusedLocationProviderClient(mContext);


            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                log_i_debug("Location received from gps: [" + location.getLatitude()
                                        + ", " + location.getLongitude() + "]");

                                mWeatherProvider.setCoordinate(location.getLatitude(), location.getLongitude());

                                checkForNeedGeoRequest(urlType, listener);

                            } else {

                                log_e("GPS location that received from getLastLocation() is null.");

                                Resources resources = Util.getLocalizedResources(mContext, new Locale(mParams.config.getLanguage()));
                                listener.onError(Error.LOCATION_ERROR, resources.getString(R.string.error_location));
                            }
                        }
                    });

        } else {

            checkForNeedGeoRequest(urlType, listener);

        }

    }

    // STEP 2: Check for need geo

    private void checkForNeedGeoRequest(WeatherProvider.URL urlType, JsonResponseListener listener) {

        if (mWeatherProvider.isGeoRequestRequired()) {

            log_v_debug("Provider api need geo key...");

            if (mParams.config.isCacheEnable()) {

                log_v_debug("Get geo key from cache...");

                initCache();
                String geo = mWeatherProvider.getJsonFromCache(mCache, WeatherProvider.URL.GEO);

                if (geo != null) {

                    log_i_debug("Geo key successfully received.");

                    getJsonFromCacheOrServer(urlType, geo, listener);

                } else {

                    log_w_debug("Geo cache is null. Then geo will getting from server.");

                    getGeo(urlType, listener);

                }

            } else {

                getGeo(urlType, listener);

            }

        } else {

            getJsonFromCacheOrServer(urlType, null, listener);

        }

    }

    // STEP 3: If geo required -> edit geo

    private void getGeo(final WeatherProvider.URL urlType, final JsonResponseListener listener) {

        String geoUrl = mWeatherProvider.getUrl(WeatherProvider.URL.GEO);

        log_v_debug("Get geo from server: " + geoUrl);

        sendRequest(WeatherProvider.URL.GEO, geoUrl, null, new JsonResponseListener() {
            @Override
            public void onSuccessful(@NonNull String geo, String ignore) {

                log_i_debug("Geo received successfully from server.");

                getJsonFromCacheOrServer(urlType, geo, listener);

            }

            @Override
            public void onError(int code, String message) {

                log_e("Error on getting geo from server.");

                listener.onError(code, message);

            }
        });

    }

    // STEP 4: edit geo from cache or server

    private void getJsonFromCacheOrServer(WeatherProvider.URL urlType,
                                          String geo,
                                          JsonResponseListener listener) {

        log_v_debug("Get json from cache or server.");

        if (urlType == WeatherProvider.URL.ALL) {
            getMultiJsonFromCacheOrServer(geo, listener);
        } else {
            getSingleJsonFromCacheOrServer(urlType, geo, listener);
        }

    }

    private void getSingleJsonFromCacheOrServer(WeatherProvider.URL urlType,
                                                String geo,
                                                JsonResponseListener listener) {

        String url;

        if (geo != null && !geo.isEmpty()) {
            url = mWeatherProvider.getUrl(urlType, geo);
        } else {
            url = mWeatherProvider.getUrl(urlType);
        }

        // Check and use cache
        if (mParams.config.isCacheEnable()) {

            log_v_debug("Get json from cache...");

            initCache();
            String json = mWeatherProvider.getJsonFromCache(mCache, urlType);

            if (json != null) {

                log_i_debug("Json getting from cache successfully.");

                listener.onSuccessful(json, geo);

            } else {

                log_w_debug("Json that received from cache is null. Then edit it from server.");

                sendRequest(urlType, url, geo, listener);

            }

        } else {

            sendRequest(urlType, url, geo, listener);

        }

    }

    private void getMultiJsonFromCacheOrServer(String geo,
                                               final JsonResponseListener listener) {

        if (mWeatherProvider.isSupportAllInOneUrl()) {

            log_v_debug("---> SINGLE REQUEST <---");

            getSingleJsonFromCacheOrServer(WeatherProvider.URL.CURRENTLY, geo, listener);

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

            // Check and use cache
            if (mParams.config.isCacheEnable()) {

                log_v_debug("Get json from cache...");

                initCache();
                String currently = mWeatherProvider.getJsonFromCache(mCache, WeatherProvider.URL.CURRENTLY);
                String hourly = mWeatherProvider.getJsonFromCache(mCache, WeatherProvider.URL.HOURLY);
                String daily = mWeatherProvider.getJsonFromCache(mCache, WeatherProvider.URL.DAILY);

                if (currently != null && hourly != null && daily != null) {

                    log_i_debug("Json getting from cache successfully.");

                    listener.onAllReceive(geo, currently, hourly, daily);

                } else {

                    log_w_debug("Json that received from cache is null. Then edit it from server.");

                    sendMultiRequest(currentlyUrl, hourlyUrl, dailyUrl, geo, listener);

                }

            } else {

                sendMultiRequest(currentlyUrl, hourlyUrl, dailyUrl, geo, listener);

            }

        }

    }

    // Send request by volley

    private void sendRequest(WeatherProvider.URL urlType,
                             String url,
                             String geo,
                             JsonResponseListener listener) {

        sendRequest(urlType, url, geo, true, listener);
    }

    private void sendRequest(final WeatherProvider.URL urlType,
                             String url,
                             final String geo,
                             final boolean isGoodTimeForUpdatingCache,
                             final JsonResponseListener listener) {

        log_v_debug("Send request: " + url);

        VolleySingletone volley = VolleySingletone.getInstance(mContext);

        // Cancel all previous requests
        volley.cancelAll();

        // Get string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        ResponseResult result = mWeatherProvider.checkForJsonValidity(response);

                        if (result.isSuccessful()) {

                            log_i_debug("Json received from server successfully.");

                            listener.onSuccessful(response, geo);

                            // Save result to database
                            if (mParams.config.isCacheEnable()) {

                                if (mCache == null) {

                                    mCache = new Cache();
                                    mCache.setProvider(mProviderType);
                                    mCache.setCoordinate(mParams.latitude, mParams.longitude);

                                }

                                mCache = mWeatherProvider.updateCache(mCache, urlType, response);

                                if (isGoodTimeForUpdatingCache) {
                                    updateCache();
                                }

                            }
                        } else {

                            log_e("result not successful.");

                            listener.onError(result.getCode(), result.getMessage());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        log_e(error.getMessage());

                        listener.onError(Error.NETWORK_ERROR, error.getMessage());
                    }
                }
        );

        // Add request
        volley.addToRequestQueue(stringRequest);

    }

    // Send multi request
    private void sendMultiRequest(String currentlyUrl,
                                  final String hourlyUrl,
                                  final String dailyUrl,
                                  final String geo,
                                  final JsonResponseListener listener) {

        sendRequest(WeatherProvider.URL.CURRENTLY, currentlyUrl, geo, false, new JsonResponseListener() {

            @Override
            void onSuccessful(@NonNull final String currently, @Nullable String geo) {

                log_i_debug("Currently weather data received successfully.");

                sendRequest(WeatherProvider.URL.HOURLY, hourlyUrl, geo, false, new JsonResponseListener() {

                    @Override
                    void onSuccessful(@NonNull final String hourly, @Nullable String geo) {

                        log_i_debug("Hourly weather data received successfully.");

                        sendRequest(WeatherProvider.URL.DAILY, dailyUrl, geo, true, new JsonResponseListener() {
                            @Override
                            void onSuccessful(@NonNull String daily, @Nullable String geo) {

                                log_i_debug("Daily weather data received successfully.");

                                listener.onAllReceive(geo, currently, hourly, daily);

                            }

                            @Override
                            void onError(int code, String message) {

                                log_e_debug("Error on getting daily weather data.");
                                log_e(message);

                                listener.onError(code, message);

                            }
                        });

                    }

                    @Override
                    void onError(int code, String message) {

                        log_e_debug("Error on getting hourly weather data.");
                        log_e(message);

                        listener.onError(code, message);

                    }

                });

            }

            @Override
            void onError(int code, String message) {

                log_e_debug("Error on getting currently weather data.");
                log_e(message);

                listener.onError(code, message);

            }

        });
    }

    // ********************************************************************************************
    // *                                Working with Cache                                        *
    // ********************************************************************************************

    private void initCache() {

        if (mCache != null) {

            log_i_debug("Cache already is initialized.");

            return;
        }

        // Get an instance of database
        if (db == null) {

            log_v_debug("Database is null. Then it's must initialized.");

            db = Room.databaseBuilder(mContext,
                    Database.class, CACHE_DATABASE_NAME).allowMainThreadQueries().build();
        }

        log_v_debug("Initialize cache...");

        // Get Cache
        Cache[] cacheList = db.cacheDao().getCache(mProviderType.toString(), mParams.latitude, mParams.longitude);

        mCache = cacheList.length > 0 ? cacheList[0] : null;

        if (mCache == null) {
            log_e_debug("Unfortunately cache is null.");
        } else {
            log_i_debug("Cache initialized successfully.");
        }

    }

    private void updateCache() {

        if (mCache == null) {

            log_e_debug("Cache is null so it's cant be inserted or updated into database.");

            return;
        }

        // Get an instance of database
        if (db == null) {
            db = Room.databaseBuilder(mContext,
                    Database.class, CACHE_DATABASE_NAME).allowMainThreadQueries().build();
        }

        log_v_debug("Inserting/Updating cache...");

        boolean isCacheExist = db.cacheDao().isCacheExist(mProviderType.toString(), mParams.latitude, mParams.longitude).length > 0;

        if (isCacheExist) {
            db.cacheDao().update(mCache);

            log_i_debug("Cache updated successfully.");

        } else {
            db.cacheDao().insert(mCache);

            log_i_debug("Cache inserted successfully.");
        }

    }
}
