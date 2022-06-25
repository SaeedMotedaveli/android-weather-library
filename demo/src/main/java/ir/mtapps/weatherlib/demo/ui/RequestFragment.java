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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import ir.mtapps.weatherlib.AirQualityClient;
import ir.mtapps.weatherlib.WeatherClient;
import ir.mtapps.weatherlib.WeatherConfig;
import ir.mtapps.weatherlib.demo.R;
import ir.mtapps.weatherlib.demo.databinding.FragmentRequestBinding;
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

    private SharedPreferences mPref;

    private PageViewModel mPageViewModel;
    private WeatherClient mWeatherClient;
    private AirQualityClient mAirQualityClient;

    private FragmentRequestBinding binding;

    private PROVIDER mProvider = PROVIDER.ACCUWEATHER;
    private AIR_QUALITY_PROVIDER mAirQualityProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
    private boolean useGps = false;
    private boolean useMetricUnits = true;

    ActivityResultLauncher<String[]> launcher;

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
        mPageViewModel = new ViewModelProvider(getActivity()).get(PageViewModel.class);
        mPref = getActivity().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (result.containsKey(false)) {
                useGps = false;
                binding.switchUseGps.setChecked(false);
            }
        });
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentRequestBinding.inflate(inflater, container, false);

        // Provider
        binding.spinnerProvider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mProvider = null;
                mAirQualityProvider = null;

                showOnlyWeatherMethod();

                binding.etApiKey.setVisibility(View.VISIBLE);
                binding.clientApiParent.setVisibility(View.GONE);

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
                        mProvider = PROVIDER.VISUAL_CROSSING;
                        break;

                    case 6:
                        mProvider = PROVIDER.TOMORROW;
                        break;

                    case 7:
                        mProvider = PROVIDER.AERIS_WEATHER;
                        binding.etApiKey.setVisibility(View.GONE);
                        binding.clientApiParent.setVisibility(View.VISIBLE);
                        break;

                    case 8:
                        showOnlyAirQualityMethod();
                        mAirQualityProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
                        break;

                }

                if (mProvider != null) {
                    if (mPref.contains(mProvider.toString())) {
                        if (mProvider != PROVIDER.AERIS_WEATHER) {
                            binding.etApiKey.setText(mPref.getString(mProvider.toString(), null));
                        } else {
                            String[] apiKeys = mPref.getString(mProvider.toString(), null).split(",");
                            binding.etClientId.setText(apiKeys[0]);
                            binding.etClientSecret.setText(apiKeys[1]);
                        }
                    } else {
                        binding.etApiKey.setText(null);
                    }
                } else if (mAirQualityProvider != null) {
                    if (mPref.contains(mAirQualityProvider.toString())) {
                        binding.etApiKey.setText(mPref.getString(mAirQualityProvider.toString(), null));
                    } else {
                        binding.etApiKey.setText(null);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Api Key
        if (mPref.contains(mProvider.toString())) {
            if (mProvider != PROVIDER.AERIS_WEATHER) {
                binding.etApiKey.setText(mPref.getString(mProvider.toString(), null));
            } else {
                String[] apiKeys = mPref.getString(mProvider.toString(), null).split(",");
                binding.etClientId.setText(apiKeys[0]);
                binding.etClientSecret.setText(apiKeys[1]);
            }
        }

        // Save api key
        binding.buttonSaveApiKey.setOnClickListener(v -> {

            String apiKey = null;
            if (mProvider != null && mProvider == PROVIDER.AERIS_WEATHER) {
                String clientId = binding.etClientId.getText().toString();
                String clientSecret = binding.etClientSecret.getText().toString();

                if (!clientId.isEmpty() && !clientSecret.isEmpty()) {
                    apiKey = clientId + "," + clientSecret;
                }
            } else {
                apiKey = binding.etApiKey.getText().toString();
            }

            if (apiKey == null || apiKey.isEmpty()) {
                Toast.makeText(getActivity(), "Error: Api Key is empty", Toast.LENGTH_SHORT).show();
            } else {

                if (mProvider != null) {
                    mPref.edit().putString(mProvider.toString(), apiKey).apply();
                } else if (mAirQualityProvider != null) {
                    mPref.edit().putString(mAirQualityProvider.toString(), apiKey).apply();
                }

            }

        });

        // Language
        if (mPref.contains(PREF_LANGUAGE)) {
            binding.etLanguage.setText(mPref.getString(PREF_LANGUAGE, null));
        }

        binding.etLanguage.addTextChangedListener(new TextWatcher() {
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
        if (mPref.contains(PREF_LATITUDE)) {
            binding.etLatitude.setText(mPref.getString(PREF_LATITUDE, null));
        }

        if (mPref.contains(PREF_LONGITUDE)) {
            binding.etLongitude.setText(mPref.getString(PREF_LONGITUDE, null));
        }

        binding.etLatitude.addTextChangedListener(new TextWatcher() {
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

        binding.etLongitude.addTextChangedListener(new TextWatcher() {
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

        binding.switchUseGps.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useGps = isChecked;

            binding.etLatitude.setEnabled(!isChecked);
            binding.etLongitude.setEnabled(!isChecked);

            if (isChecked) {
                if (!isLocationPermissionGranted()) {
                    requestPermissions();
                    return;
                }
            }
        });

        // Units
        binding.spinnerUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                useMetricUnits = position == 0;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Send request
        binding.fabRequest.setOnClickListener(v -> {

            showProgressView(true);
            mPageViewModel.clear();

            int checkedId = binding.rgRequestType.getCheckedRadioButtonId();

            if (checkedId == R.id.rb_current_weather) {
                getCurrentWeather();
            } else if (checkedId == R.id.rb_today_astronomy) {
                getTodayAstronomy();
            } else if (checkedId == R.id.rb_hourly_weather) {
                getHourlyWeather();
            } else if (checkedId == R.id.rb_daily_weather) {
                getDailyWeather();
            } else if (checkedId == R.id.rb_all_weather) {
                getAllWeather();
            } else if (checkedId == R.id.rb_air_quality) {
                getAirQuality();
            }

        });

        return binding.getRoot();
    }

    private WeatherClient getWeatherClient() {

        // Get provider key
        String apiKey = null;
        if (mProvider == PROVIDER.AERIS_WEATHER) {
            String clientId = binding.etClientId.getText().toString();
            String clientSecret = binding.etClientSecret.getText().toString();

            if (!clientId.isEmpty() && !clientSecret.isEmpty()) {
                apiKey = clientId + "," + clientSecret;
            }
        } else {
            apiKey = binding.etApiKey.getText().toString();
        }

        if (apiKey == null || apiKey.isEmpty()) {
            Toast.makeText(getActivity(), "Error: API Key is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Language
        String language = binding.etLanguage.getText().toString();

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
            String latStr = binding.etLatitude.getText().toString();
            if (!latStr.isEmpty()) {
                latitude = Double.parseDouble(latStr);
            } else {
                Toast.makeText(getActivity(), "Error: Latitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }

            // Parsing longitude
            String lngStr = binding.etLongitude.getText().toString();
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
                .create();

        // Create weather client
        WeatherClient.Builder builder = new WeatherClient.Builder()
                .config(config)
                .provider(mProvider)
                .apiKey(apiKey);

        if (!useGps) {
            builder.coordinate(latitude, longitude);
        }

        return builder.build();
    }

    private AirQualityClient getAirQualityClient() {

        // Get provider key
        String key = binding.etApiKey.getText().toString();

        if (key.isEmpty()) {
            Toast.makeText(getActivity(), "Error: API Key is null", Toast.LENGTH_SHORT).show();
            showProgressView(false);
            return null;
        }

        // Language
        String language = binding.etLanguage.getText().toString();

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
            String latStr = binding.etLatitude.getText().toString();
            if (!latStr.isEmpty()) {
                latitude = Double.parseDouble(latStr);
            } else {
                Toast.makeText(getActivity(), "Error: Latitude is null", Toast.LENGTH_SHORT).show();
                showProgressView(false);
                return null;
            }

            // Parsing longitude
            String lngStr = binding.etLongitude.getText().toString();
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

        mWeatherClient.currentCondition(getContext(), new CurrentWeatherListener() {
            @Override
            public void onSuccessful(City city, CurrentWeather weather) {
                mPageViewModel.setCity(city);
                mPageViewModel.setCurrentWeather(weather);
                showProgressView(false);
            }

            @Override
            public void onFailure(int code, String message) {
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

        mWeatherClient.todayAstronomy(getContext(), new AstronomyListener() {
            @Override
            public void onSuccessful(City city, Astronomy astronomy) {
                mPageViewModel.setCity(city);
                mPageViewModel.setTodayAstronomy(astronomy);
                showProgressView(false);
            }

            @Override
            public void onFailure(int code, String message) {
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

        mWeatherClient.hourlyWeather(getContext(), new HourlyWeatherListener() {
            @Override
            public void onSuccessful(City city, List<HourlyWeather> hourlyWeatherList) {
                mPageViewModel.setCity(city);
                mPageViewModel.setHourlyWeather(hourlyWeatherList);
                showProgressView(false);
            }

            @Override
            public void onFailure(int code, String message) {
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

        mWeatherClient.dailyWeather(getContext(), new DailyWeatherListener() {
            @Override
            public void onSuccessful(City city, List<DailyWeather> dailyWeatherList) {
                mPageViewModel.setCity(city);
                mPageViewModel.setDailyWeather(dailyWeatherList);
                showProgressView(false);
            }

            @Override
            public void onFailure(int code, String message) {
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

        mWeatherClient.allWeather(getContext(), new AllWeatherListener() {
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
            public void onFailure(int code, String message) {
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
            public void onFailure(int code, String message) {
                mPageViewModel.setError(message);
                showProgressView(false);
            }
        });

    }

    private void showProgressView(boolean show) {

        if (show) {
            binding.progressParent.setVisibility(View.VISIBLE);
            binding.requestParent.setVisibility(View.GONE);
            binding.fabRequest.setAlpha(0f);
        } else {
            binding.progressParent.setVisibility(View.GONE);
            binding.requestParent.setVisibility(View.VISIBLE);
            binding.fabRequest.setAlpha(1f);
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

        binding.rgRequestType.check(R.id.rb_current_weather);

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

        binding.rgRequestType.check(R.id.rb_air_quality);
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
        launcher.launch(permissions);
    }

}
