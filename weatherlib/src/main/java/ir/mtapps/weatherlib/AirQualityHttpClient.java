package ir.mtapps.weatherlib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

import ir.mtapps.weatherlib.enums.AIR_QUALITY_PROVIDER;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AirQualityListener;
import ir.mtapps.weatherlib.provider.AirQualityProvider;

import static ir.mtapps.weatherlib.Loging.*;

class AirQualityHttpClient extends AirQualityClient {

    private abstract class JsonResponseListener {
        abstract void onSuccessful(@NonNull String json);
        abstract void onError(int code, String message);
    }


    private static AirQualityHttpClient mClient = null;

    // Weather Provider
    private AirQualityProvider mProvider = null;

    // Values
    private Context mContext;
    private AIR_QUALITY_PROVIDER mProviderType;
    private boolean mUseAutoCoordinate;

    private AirQualityProvider.Params mParams;

    static AirQualityHttpClient getInstance(Context context,
                                            AIR_QUALITY_PROVIDER provider,
                                            boolean autoCoordinate,
                                            AirQualityProvider.Params params) {
        if (mClient == null) {
            mClient = new AirQualityHttpClient();
        }

        mClient.init(context, provider, autoCoordinate, params);

        return mClient;

    }

    private void init(Context context,
                      AIR_QUALITY_PROVIDER provider,
                      boolean autoCoordinate,
                      AirQualityProvider.Params params) {

        mProviderType = provider;
        mContext = context.getApplicationContext();
        mUseAutoCoordinate = autoCoordinate;
        mParams = params;

        switch (provider) {
            case WORLD_AIR_POLLUTION:
                mProvider = new ir.mtapps.weatherlib.provider.waqi.Provider();
                break;

            default:
                mProvider = new ir.mtapps.weatherlib.provider.waqi.Provider();
        }

        mProvider.setContext(mContext);
        mProvider.setParams(params);
    }

    // ********************************************************************************************
    // *                                Get Weather Methods
    // ********************************************************************************************

    @Override
    public void airQuality(@NonNull final AirQualityListener listener) {

        getJson(new JsonResponseListener() {

            @Override
            void onSuccessful(@NonNull String json) {

                mProvider.airQuality(json, listener);

            }

            @Override
            void onError(int code, String message) {

                listener.onFailure(code, message);

            }
        });

    }

    // ********************************************************************************************
    // *                                Get JSON from Cache or Server                             *
    // ********************************************************************************************

    // STEP 1: Check for user location

    private void getJson(final JsonResponseListener listener) {

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

                                mProvider.setCoordinate(location.getLatitude(), location.getLongitude());

                                sendAirQualityRequest(listener);

                            } else {

                                log_e("GPS location that received from getLastLocation() is null.");

                                Resources resources = Util.getLocalizedResources(mContext, new Locale(mParams.config.getLanguage()));
                                listener.onError(Error.LOCATION_ERROR, resources.getString(R.string.error_location));
                            }
                        }
                    });

        } else {

            sendAirQualityRequest(listener);

        }

    }

    // Send air quality request by volley

    private void sendAirQualityRequest(final JsonResponseListener listener) {

        String url = mProvider.getAirQualityUrl();

        VolleySingletone volley = VolleySingletone.getInstance(mContext);

        // Get string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        listener.onSuccessful(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(Error.NETWORK_ERROR, error.getMessage());
                    }
                }
        );

        // Add request
        volley.addToRequestQueue(stringRequest);
    }
}
