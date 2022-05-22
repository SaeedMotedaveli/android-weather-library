package ir.mtapps.weatherlib.demo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ir.mtapps.weatherlib.demo.MainActivity;
import ir.mtapps.weatherlib.demo.R;

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
        pageViewModel = new ViewModelProvider(getActivity()).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_result, container, false);

        resultTv = root.findViewById(R.id.tv_result);

        pageViewModel.getClear().observe(getViewLifecycleOwner(), aVoid -> resultTv.setText(null));

        pageViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            setText(error, true);
            switchToResultPage();
        });

        pageViewModel.getCity().observe(getViewLifecycleOwner(), city -> {
            setText(city.toString());
            switchToResultPage();
        });

        pageViewModel.getCurrentWeather().observe(getViewLifecycleOwner(), currentWeather -> {
            setText(currentWeather.toString());
            switchToResultPage();
        });

        pageViewModel.getAstronomy().observe(getViewLifecycleOwner(), astronomy -> {
            setText(astronomy.toString());
            switchToResultPage();
        });

        pageViewModel.getHourlyWeather().observe(getViewLifecycleOwner(), hourlyWeathers -> {

            StringBuilder builder = new StringBuilder();
            int size = 3;

            for (int i = 0; i < size; i++) {
                builder.append(hourlyWeathers.get(i).toString());

                if (i < (size - 1)) {
                    builder.append("\n\n");
                }
            }

            setText(builder.toString());
            switchToResultPage();
        });

        pageViewModel.getDailyWeather().observe(getViewLifecycleOwner(), dailyWeathers -> {

            StringBuilder builder = new StringBuilder();
            int size = 3;

            for (int i = 0; i < size; i++) {
                builder.append(dailyWeathers.get(i).toString());

                if (i < (size - 1)) {
                    builder.append("\n\n");
                }
            }

            setText(builder.toString());
            switchToResultPage();
        });

        pageViewModel.getAirQuality().observe(getViewLifecycleOwner(), airQuality -> {
            setText(airQuality.toString());
            switchToResultPage();
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
    
    private void switchToResultPage() {
        
        MainActivity activity = (MainActivity) getActivity();
        
        if (activity != null) {
            activity.switchToResultPage();
        }
    }
}