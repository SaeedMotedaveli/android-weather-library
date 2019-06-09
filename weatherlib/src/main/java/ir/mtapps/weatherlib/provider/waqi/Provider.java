package ir.mtapps.weatherlib.provider.waqi;

import android.content.res.Resources;

import com.google.gson.Gson;

import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.errors.Error;
import ir.mtapps.weatherlib.interfaces.AirQualityListener;
import ir.mtapps.weatherlib.provider.AirQualityProvider;

public class Provider extends AirQualityProvider {

    private final static String AIR_QUALITY_URL = "https://api.waqi.info/feed/geo:{lat};{lng}/?token={key}";

    @Override
    public void airQuality(String json, AirQualityListener listener) {

        if (json == null || json.isEmpty()) {
            Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(getContext(), new Locale(getParams().config.getLanguage()));
            listener.onError(Error.RESPONSE_IS_NULL, resources.getString(R.string.error_response_is_null));
            return;
        }

        Gson gson = new Gson();

        Model model = gson.fromJson(json, Model.class);

        if (!model.isDataValid()) {
            Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(getContext(), new Locale(getParams().config.getLanguage()));
            listener.onError(Error.RESPONSE_NOT_OK, resources.getString(R.string.error_response_not_ok));
            return;
        }

        boolean isDistanceValid = isDistanceValid(getParams().latitude, getParams().longitude,
                model.data.city.geo.get(0), model.data.city.geo.get(1));

        if (!isDistanceValid) {
            Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(getContext(), new Locale(getParams().config.getLanguage()));
            listener.onError(Error.AIR_QUALITY_NOT_FOUND, resources.getString(R.string.error_aq_not_found));
            return;
        }

        listener.onSuccessful(model.createAQModel(getContext(), getParams().config.getLanguage()));

    }

    @Override
    public final String getAirQualityUrl() {

        String url = AIR_QUALITY_URL.replace("{lat}", Double.toString(getParams().latitude));
        url = url.replace("{lng}", Double.toString(getParams().longitude));
        url = url.replace("{key}", getParams().apiKey);

        return url;

    }
}
