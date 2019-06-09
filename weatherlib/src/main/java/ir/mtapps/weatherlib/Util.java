package ir.mtapps.weatherlib;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class Util {

    @NonNull
    public static Resources getLocalizedResources(Context context, String language) {
        return getLocalizedResources(context, new Locale(language));
    }

    @NonNull
    public static Resources getLocalizedResources(Context context, Locale desiredLocale) {
        Configuration conf = context.getResources().getConfiguration();
        conf = new Configuration(conf);
        conf.setLocale(desiredLocale);
        Context localizedContext = context.createConfigurationContext(conf);
        return localizedContext.getResources();
    }

    public static String getAirQualityRiskLevel(Context context, String language, float aq) {

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        if (aq < 51) {
            return resources.getString(R.string.air_quality_risk_level_1);
        }

        if (aq < 101) {
            return resources.getString(R.string.air_quality_risk_level_2);
        }

        if (aq < 151) {
            return resources.getString(R.string.air_quality_risk_level_3);
        }

        if (aq < 201) {
            return resources.getString(R.string.air_quality_risk_level_4);
        }

        if (aq < 301) {
            return resources.getString(R.string.air_quality_risk_level_5);
        }

        return resources.getString(R.string.air_quality_risk_level_6);
    }

    public static String getAirQualityHealthMessage(Context context, String language, float aq) {

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        if (aq < 51) {
            return resources.getString(R.string.air_quality_health_message_1);
        }

        if (aq < 101) {
            return resources.getString(R.string.air_quality_health_message_2);
        }

        if (aq < 151) {
            return resources.getString(R.string.air_quality_health_message_3);
        }

        if (aq < 201) {
            return resources.getString(R.string.air_quality_health_message_4);
        }

        if (aq < 301) {
            return resources.getString(R.string.air_quality_health_message_5);
        }

        return resources.getString(R.string.air_quality_health_message_6);
    }

    public static float getDistanceFromLatLonInKm(double lat1, double lon1,
                                           double lat2, double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);  // deg2rad below
        double dLon = deg2rad(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float d = (float) (R * c); // Distance in km
        return d;
    }

    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }

}
