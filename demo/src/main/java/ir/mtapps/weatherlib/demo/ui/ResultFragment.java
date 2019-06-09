package ir.mtapps.weatherlib.demo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ir.mtapps.weatherlib.demo.MainActivity;
import ir.mtapps.weatherlib.demo.R;
import ir.mtapps.weatherlib.model.AirQuality;
import ir.mtapps.weatherlib.model.Astronomy;
import ir.mtapps.weatherlib.model.City;
import ir.mtapps.weatherlib.model.CurrentWeather;
import ir.mtapps.weatherlib.model.DailyWeather;
import ir.mtapps.weatherlib.model.HourlyWeather;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResultFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private TextView resultTv;

    static ResultFragment newInstance(int index) {
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(getActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_result, container, false);

        resultTv = root.findViewById(R.id.tv_result);

        pageViewModel.getClear().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                resultTv.setText(null);
            }
        });

        pageViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                setText(s, true);
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getCity().observe(this, new Observer<City>() {
            @Override
            public void onChanged(City city) {
                setText(city.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getCurrentWeather().observe(this, new Observer<CurrentWeather>() {
            @Override
            public void onChanged(CurrentWeather currentWeather) {
                setText(currentWeather.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getAstronomy().observe(this, new Observer<Astronomy>() {
            @Override
            public void onChanged(Astronomy astronomy) {
                setText(astronomy.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getHourlyWeather().observe(this, new Observer<List<HourlyWeather>>() {
            @Override
            public void onChanged(List<HourlyWeather> hourlyWeathers) {

                StringBuilder builder = new StringBuilder();
                int size = 3;

                for (int i = 0; i < size; i++) {
                    builder.append(hourlyWeathers.get(i).toString());

                    if (i < (size - 1)) {
                        builder.append("\n\n");
                    }
                }

                setText(builder.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getDailyWeather().observe(this, new Observer<List<DailyWeather>>() {
            @Override
            public void onChanged(List<DailyWeather> dailyWeathers) {

                StringBuilder builder = new StringBuilder();
                int size = 3;

                for (int i = 0; i < size; i++) {
                    builder.append(dailyWeathers.get(i).toString());

                    if (i < (size - 1)) {
                        builder.append("\n\n");
                    }
                }

                setText(builder.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        pageViewModel.getAirQuality().observe(this, new Observer<AirQuality>() {
            @Override
            public void onChanged(AirQuality airQuality) {
                setText(airQuality.toString());
                ((MainActivity) getActivity()).selectResultPage();
            }
        });

        return root;
    }

    private void setText(String text) {
        setText(text, false);
    }

    private void setText(String text, boolean isError) {

        String old = resultTv.getText().toString();
        String newStr = "";

        if (!old.isEmpty()) {
            newStr = old + "\n\n\n" + text;
        } else {
            newStr = text;
        }

        resultTv.setText(newStr);

        if (isError) {
            resultTv.setTextColor(Color.RED);
        } else {
            resultTv.setTextColor(Color.BLACK);
        }

    }
}