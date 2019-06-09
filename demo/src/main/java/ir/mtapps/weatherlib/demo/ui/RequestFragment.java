package ir.mtapps.weatherlib.demo.ui;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ir.mtapps.weatherlib.AirQualityClient;
import ir.mtapps.weatherlib.WeatherClient;
import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.demo.R;
import ir.mtapps.weatherlib.enums.AIR_QUALITY_PROVIDER;
import ir.mtapps.weatherlib.enums.PROVIDER;
import ir.mtapps.weatherlib.enums.UNIT_SYSTEM;
import ir.mtapps.weatherlib.interfaces.AirQualityListener;
import ir.mtapps.weatherlib.interfaces.AllWeatherListener;
import ir.mtapps.weatherlib.interfaces.AstronomyListener;
import ir.mtapps.weatherlib.interfaces.CurrentWeatherListener;
import ir.mtapps.weatherlib.interfaces.DailyWeatherListener;
import ir.mtapps.weatherlib.interfaces.HourlyWeatherListener;
import ir.mtapps.weatherlib.model.AirQuality;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

public class RequestFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String PREFERENCES_NAME = "prefs";

    private static final String PREF_LANGUAGE = "language";
    private static final String PREF_LATITUDE = "latitude";
    private static final String PREF_LONGITUDE = "longitude";

    private final static int PERMISSION_REQUEST_CODE = 12345;

    private SharedPreferences mPref;

    private PageViewModel mPageViewModel;
    private WeatherClient mWeatherClient;
    private AirQualityClient mAirQualityClient;
    private FloatingActionButton fab;

    private View progressView;
    private View requestView;

    private EditText apiKeyEt;
    private EditText languageEt;
    private EditText latitudeEt;
    private EditText longitudeEt;
    private SwitchCompat useGpsSwitch;
    private RadioGroup requestTypeRadioGroup;

    private PROVIDER mProvider = PROVIDER.ACCUWEATHER;
    private AIR_QUALITY_PROVIDER mAirQualityProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
    private boolean useGps = false;
    private boolean useMetricUnits = true;

    static RequestFragment newInstance(int index) {
        RequestFragment fragment = new RequestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageViewModel = ViewModelProviders.of(getActivity()).get(PageViewModel.class);

        mPref = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_request, container, false);

        progressView = root.findViewById(R.id.progress_parent);
        requestView = root.findViewById(R.id.request_parent);

        // Provider
        Spinner providerSpinner = root.findViewById(R.id.spinner_provider);
        providerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mProvider = null;
                mAirQualityProvider = null;

                showOnlyWeatherMethod();

                switch (position) {

                    case 0:
                        mProvider = PROVIDER.ACCUWEATHER;
                        break;

                    case 1:
                        mProvider = PROVIDER.DARK_SKY;
                        break;

                    case 2:
                        mProvider = PROVIDER.OPEN_WEATHER;
                        break;

                    case 3:
                        mProvider = PROVIDER.WEATHERBIT;
                        break;

                    case 4:
                        mProvider = PROVIDER.WORLD_WEATHER_ONLINE;
                        break;

                    case 5:
                        showOnlyAirQualityMethod();
                        mAirQualityProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
                        break;

                }

                if (mProvider != null) {
                    if (mPref.contains(mProvider.toString())) {
                        apiKeyEt.setText(mPref.getString(mProvider.toString(), null));
                    } else {
                        apiKeyEt.setText(null);
                    }
                } else if (mAirQualityProvider != null) {
                    if (mPref.contains(mAirQualityProvider.toString())) {
                        apiKeyEt.setText(mPref.getString(mAirQualityProvider.toString(), null));
                    } else {
                        apiKeyEt.setText(null);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Api Key
        apiKeyEt = root.findViewById(R.id.et_api_key);

        if (mPref.contains(mProvider.toString())) {
            apiKeyEt.setText(mPref.getString(mProvider.toString(), null));
        }

        // Save api key
        Button saveApiKeyButton = root.findViewById(R.id.button_save_api_key);
        saveApiKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String apiKey = apiKeyEt.getText().toString();

                if (apiKey.isEmpty()) {
                    Toast.makeText(getActivity(), "Error: Api Key is empty", Toast.LENGTH_SHORT).show();
                } else {

                    if (mProvider != null) {
                        mPref.edit().putString(mProvider.toString(), apiKey).apply();

                    } else if (mAirQualityProvider != null) {
                        mPref.edit().putString(mAirQualityProvider.toString(), apiKey).apply();
                    }

                }

            }
        });

        // Language
        languageEt = root.findViewById(R.id.et_language);

        if (mPref.contains(PREF_LANGUAGE)) {
            languageEt.setText(mPref.getString(PREF_LANGUAGE, null));
        }

        languageEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    mPref.edit().putString(PREF_LANGUAGE, s.toString()).apply();
                }

            }
        });

        // Coordinate
        latitudeEt = root.findViewById(R.id.et_latitude);
        longitudeEt = root.findViewById(R.id.et_longitude);

        if (mPref.contains(PREF_LATITUDE)) {
            latitudeEt.setText(mPref.getString(PREF_LATITUDE, null));
        }

        if (mPref.contains(PREF_LONGITUDE)) {
            longitudeEt.setText(mPref.getString(PREF_LONGITUDE, null));
        }

        latitudeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    mPref.edit().putString(PREF_LATITUDE, s.toString()).apply();
                }

            }
        });

        longitudeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    mPref.edit().putString(PREF_LONGITUDE, s.toString()).apply();
                }

            }
        });

        useGpsSwitch = root.findViewById(R.id.switch_use_gps);
        useGpsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                useGps = isChecked;

                if (isChecked) {

                    if (!isLocationPermissionGranted()) {
                        requestPermissions();
                        return;
                    }

                    latitudeEt.setEnabled(false);
                    longitudeEt.setEnabled(false);
                } else {
                    latitudeEt.setEnabled(true);
                    longitudeEt.setEnabled(true);
                }
            }
        });

        // Units
        Spinner unitsSpinner = root.findViewById(R.id.spinner_units);
        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                useMetricUnits = position == 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Request type
        requestTypeRadioGroup = root.findViewById(R.id.rg_request_type);

        // Send request
        fab = root.findViewById(R.id.fab_request);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressView(true);
                mPageViewModel.clear();

                int checkedId = requestTypeRadioGroup.getCheckedRadioButtonId();

                switch (checkedId) {

                    case R.id.rb_current_weather:
                        getCurrentWeather();
                        break;

                    case R.id.rb_today_astronomy:
                        getTodayAstronomy();
                        break;

                    case R.id.rb_hourly_weather:
                        getHourlyWeather();
                        break;

                    case R.id.rb_daily_weather:
                        getDailyWeather();
                        break;

                    case R.id.rb_all_weather:
                        getAllWeather();
                        break;

                    case R.id.rb_air_quality:
                        getAirQuality();
                        break;

                }

            }
        });

        return root;
    }

    private WeatherClient getWeatherClient() {

        // Get provider key
        String key = apiKeyEt.getText().toString();

        if (key.isEmpty()) {
            Toast.makeText(getActivity(), "Error: API Key is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Language
        String language = languageEt.getText().toString();

        if (language.isEmpty()) {
            Toast.makeText(getActivity(), "Error: Language is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Location
        double latitude = 0;
        double longitude = 0;

        if (!useGps) {

            // Parsing latitude
            String latStr = latitudeEt.getText().toString();
            if (!latStr.isEmpty()) {
                latitude = Double.parseDouble(latStr);
            } else {
                Toast.makeText(getActivity(), "Error: Latitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }

            // Parsing longitude
            String lngStr = longitudeEt.getText().toString();
            if (!lngStr.isEmpty()) {
                longitude = Double.parseDouble(lngStr);
            } else {
                Toast.makeText(getActivity(), "Error: Longitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }
        }

        // Unit
        UNIT_SYSTEM unit_system = useMetricUnits ? UNIT_SYSTEM.METRIC : UNIT_SYSTEM.IMPERIAL;

        // Weather Configure
        WeatherConfig config = WeatherConfig.edit()
                .language(language)
                .unitSystem(unit_system)
                .disableCache()
                .create();

        // Create weather client
        WeatherClient.Builder builder = new WeatherClient.Builder()
                .attach(getActivity())
                .config(config)
                .provider(mProvider)
                .apiKey(key);

        if (!useGps) {
            builder.coordinate(latitude, longitude);
        }

        return builder.build();
    }

    private AirQualityClient getAirQualityClient() {

        // Get provider key
        String key = apiKeyEt.getText().toString();

        if (key.isEmpty()) {
            Toast.makeText(getActivity(), "Error: API Key is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Language
        String language = languageEt.getText().toString();

        if (language.isEmpty()) {
            Toast.makeText(getActivity(), "Error: Language is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Location
        double latitude = 0;
        double longitude = 0;

        if (!useGps) {

            // Parsing latitude
            String latStr = latitudeEt.getText().toString();
            if (!latStr.isEmpty()) {
                latitude = Double.parseDouble(latStr);
            } else {
                Toast.makeText(getActivity(), "Error: Latitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }

            // Parsing longitude
            String lngStr = longitudeEt.getText().toString();
            if (!lngStr.isEmpty()) {
                longitude = Double.parseDouble(lngStr);
            } else {
                Toast.makeText(getActivity(), "Error: Longitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }
        }

        // Unit
        UNIT_SYSTEM unit_system = useMetricUnits ? UNIT_SYSTEM.METRIC : UNIT_SYSTEM.IMPERIAL;

        // Weather Configure
        WeatherConfig config = WeatherConfig.edit()
                .language(language)
                .unitSystem(unit_system)
                .disableCache()
                .validRadiusOfAirQuality(100)
                .create();

        // Create weather client
        AirQualityClient.Builder builder = new AirQualityClient.Builder()
                .attach(getActivity())
                .config(config)
                .provider(mAirQualityProvider)
                .apiKey(key);

        if (!useGps) {
            builder.coordinate(latitude, longitude);
        }

        return builder.build();
    }

    private void getCurrentWeather() {

        mWeatherClient = getWeatherClient();

        if (mWeatherClient == null) {
            return;
        }

        mWeatherClient.currentCondition(new CurrentWeatherListener() {
            @Override
            public void onSuccessful(City city, CurrentWeather weather) {
                mPageViewModel.setCity(city);
                mPageViewModel.setCurrentWeather(weather);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void getTodayAstronomy() {

        mWeatherClient = getWeatherClient();

        if (mWeatherClient == null) {
            return;
        }

        mWeatherClient.todayAstronomy(new AstronomyListener() {
            @Override
            public void onSuccessful(City city, Astronomy astronomy) {
                mPageViewModel.setCity(city);
                mPageViewModel.setTodayAstronomy(astronomy);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void getHourlyWeather() {

        mWeatherClient = getWeatherClient();

        if (mWeatherClient == null) {
            return;
        }

        mWeatherClient.hourlyWeather(new HourlyWeatherListener() {
            @Override
            public void onSuccessful(City city, List<HourlyWeather> hourlyWeatherList) {
                mPageViewModel.setCity(city);
                mPageViewModel.setHourlyWeather(hourlyWeatherList);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void getDailyWeather() {

        mWeatherClient = getWeatherClient();

        if (mWeatherClient == null) {
            return;
        }

        mWeatherClient.dailyWeather(new DailyWeatherListener() {
            @Override
            public void onSuccessful(City city, List<DailyWeather> dailyWeatherList) {
                mPageViewModel.setCity(city);
                mPageViewModel.setDailyWeather(dailyWeatherList);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void getAllWeather() {

        mWeatherClient = getWeatherClient();

        if (mWeatherClient == null) {
            return;
        }

        mWeatherClient.allWeather(new AllWeatherListener() {
            @Override
            public void onSuccessful(City city,
                                     CurrentWeather weather,
                                     Astronomy astronomy,
                                     List<HourlyWeather> hourlyWeatherList,
                                     List<DailyWeather> dailyWeatherList) {
                mPageViewModel.setCity(city);
                mPageViewModel.setCurrentWeather(weather);
                mPageViewModel.setTodayAstronomy(astronomy);
                mPageViewModel.setHourlyWeather(hourlyWeatherList);
                mPageViewModel.setDailyWeather(dailyWeatherList);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void getAirQuality() {

        mAirQualityClient = getAirQualityClient();

        if (mAirQualityClient == null) {
            return;
        }

        mAirQualityClient.airQuality(new AirQualityListener() {
            @Override
            public void onSuccessful(AirQuality airQuality) {
                mPageViewModel.setAirQuality(airQuality);
                showProgressView(false);
            }

            @Override
            public void onError(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void showProgressView(boolean show) {

        if (show) {
            progressView.setVisibility(View.VISIBLE);
            requestView.setVisibility(View.GONE);
            fab.setAlpha(0f);
        } else {
            progressView.setVisibility(View.GONE);
            requestView.setVisibility(View.VISIBLE);
            fab.setAlpha(1f);
        }

    }

    private void showOnlyWeatherMethod() {

        int[] weatherMethodIds = {
                R.id.rb_current_weather,
                R.id.rb_today_astronomy,
                R.id.rb_hourly_weather,
                R.id.rb_daily_weather,
                R.id.rb_all_weather
        };

        int[] airQualityMethodIds = {
                R.id.rb_air_quality
        };

        for (int id : weatherMethodIds) {
            getView().findViewById(id).setVisibility(View.VISIBLE);
        }

        for (int id : airQualityMethodIds) {
            getView().findViewById(id).setVisibility(View.GONE);
        }

        requestTypeRadioGroup.check(R.id.rb_current_weather);

    }

    private void showOnlyAirQualityMethod() {

        int[] weatherMethodIds = {
                R.id.rb_current_weather,
                R.id.rb_today_astronomy,
                R.id.rb_hourly_weather,
                R.id.rb_daily_weather,
                R.id.rb_all_weather
        };

        int[] airQualityMethodIds = {
                R.id.rb_air_quality
        };

        for (int id : weatherMethodIds) {
            getView().findViewById(id).setVisibility(View.GONE);
        }

        for (int id : airQualityMethodIds) {
            getView().findViewById(id).setVisibility(View.VISIBLE);
        }

        requestTypeRadioGroup.check(R.id.rb_air_quality);
    }

    // ---------------------------- REQUEST LOCATION PERMISSION ------------------------

    private boolean isLocationPermissionGranted() {
        return ActivityCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        final String fineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
        final String coarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
        final String[] permissions = new String[]{fineLocation, coarseLocation};

        // Request Permissions
        requestPermissions(permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {

            // Check for request result
            boolean isLocationPermissionsGranted = false;

            for (int result : grantResults) {
                isLocationPermissionsGranted = result == PackageManager.PERMISSION_GRANTED;
            }

            if (!isLocationPermissionsGranted) {
                useGps = false;
                useGpsSwitch.setChecked(false);
            }

        }
    }

}
