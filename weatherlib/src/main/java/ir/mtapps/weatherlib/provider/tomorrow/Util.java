package ir.mtapps.weatherlib.provider.tomorrow;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

import ir.mtapps.weatherlib.R;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;

public class Util {

    static String getDescription(Context context, String language, int weatherCode) {

        Resources resources = ir.mtapps.weatherlib.Util.getLocalizedResources(context, new Locale(language));

        switch (weatherCode) {

            case 1000:
            case 10000:
            case 10001:
                return resources.getString(R.string.clear_sky);

            case 1100:
            case 11000:
            case 11001:
            case 1101:
            case 11010:
            case 11011:
            case 1103:
            case 11030:
            case 11031:
                return resources.getString(R.string.few_clouds);

            case 1102:
            case 11020:
            case 11021:
                return resources.getString(R.string.mostly_cloudy);

            case 1001:
            case 10010:
            case 10011:
                return resources.getString(R.string.cloudy);

            case 2100:
            case 21000:
            case 21001:
            case 2101:
            case 21010:
            case 21011:
            case 2102:
            case 21020:
            case 21021:
            case 2103:
            case 21030:
            case 21031:
                return resources.getString(R.string.light_fog);

            case 2000:
            case 20000:
            case 20001:
            case 2106:
            case 21060:
            case 21061:
            case 2107:
            case 21070:
            case 21071:
            case 2108:
            case 21080:
            case 21081:
                return resources.getString(R.string.fog);

            case 4000:
            case 40000:
            case 40001:
            case 4203:
            case 42030:
            case 42031:
            case 4204:
            case 42040:
            case 42041:
            case 4205:
            case 42050:
            case 42051:
                return resources.getString(R.string.drizzle);

            case 4200:
            case 42000:
            case 42001:
            case 4213:
            case 42130:
            case 42131:
            case 4214:
            case 42140:
            case 42141:
            case 4215:
            case 42150:
            case 42151:
                return resources.getString(R.string.light_rain);

            case 4001:
            case 40010:
            case 40011:
            case 4209:
            case 42090:
            case 42091:
            case 4208:
            case 42080:
            case 42081:
            case 4210:
            case 42100:
            case 42101:
                return resources.getString(R.string.rain);

            case 4201:
            case 42010:
            case 42011:
            case 4211:
            case 42110:
            case 42111:
            case 4202:
            case 42020:
            case 42021:
            case 4212:
            case 42120:
            case 42121:
                return resources.getString(R.string.heavy_rain);

            case 5001:
            case 50010:
            case 50011:
            case 5100:
            case 51000:
            case 51001:
            case 5115:
            case 51150:
            case 51151:
            case 5116:
            case 51160:
            case 51161:
            case 5117:
            case 51170:
            case 51171:
            case 5122:
            case 51220:
            case 51221:
            case 5102:
            case 51020:
            case 51021:
            case 5103:
            case 51030:
            case 51031:
            case 5104:
            case 51040:
            case 51041:
                return resources.getString(R.string.light_snow);

            case 5000:
            case 50000:
            case 50001:
            case 5105:
            case 51050:
            case 51051:
            case 5106:
            case 51060:
            case 51061:
            case 5107:
            case 51070:
            case 51071:
                return resources.getString(R.string.snow);

            case 5101:
            case 51010:
            case 51011:
            case 5119:
            case 51190:
            case 51191:
            case 51200:
            case 51201:
            case 5121:
            case 51210:
            case 51211:
                return resources.getString(R.string.heavy_snow);

            case 5110:
            case 51100:
            case 51101:
            case 5108:
            case 51080:
            case 51081:
                return resources.getString(R.string.rain_and_snow);

            case 5114:
            case 51140:
            case 51141:
                return resources.getString(R.string.snow_and_freezing_rain);

            case 5112:
            case 51120:
            case 51121:
                return resources.getString(R.string.snow_and_ice_pallets);

            case 6000:
            case 60000:
            case 60001:
            case 6200:
            case 62000:
            case 62001:
            case 6003:
            case 60030:
            case 60031:
            case 6002:
            case 60020:
            case 60021:
            case 6004:
            case 60040:
            case 60041:
            case 6204:
            case 62040:
            case 62041:
            case 6206:
            case 62060:
            case 62061:
            case 6205:
            case 62050:
            case 62051:
            case 6203:
            case 62030:
            case 62031:
            case 6209:
            case 62090:
            case 62091:
                return resources.getString(R.string.freezing_drizzle);

            case 6001:
            case 60010:
            case 60011:
            case 6213:
            case 62130:
            case 62131:
            case 6214:
            case 62140:
            case 62141:
            case 6215:
            case 62150:
            case 62151:
            case 6212:
            case 62120:
            case 62121:
            case 6220:
            case 62200:
            case 62201:
            case 6222:
            case 62220:
            case 62221:
                return resources.getString(R.string.freezing_rain);

            case 6201:
            case 62010:
            case 62011:
            case 6207:
            case 62070:
            case 62071:
            case 6202:
            case 62020:
            case 62021:
            case 6208:
            case 62080:
            case 62081:
                return resources.getString(R.string.heavy_freezing_rain);

            case 7102:
            case 71020:
            case 71021:
            case 7110:
            case 71100:
            case 71101:
            case 7111:
            case 71110:
            case 71111:
            case 7112:
            case 71120:
            case 71121:
                return resources.getString(R.string.light_ice_pallets);

            case 7000:
            case 70000:
            case 70001:
            case 7108:
            case 71080:
            case 71081:
            case 7107:
            case 71070:
            case 71071:
            case 7109:
            case 71090:
            case 71091:
            case 7105:
            case 71050:
            case 71051:
            case 7115:
            case 71150:
            case 71151:
            case 7117:
            case 71170:
            case 71171:
            case 7106:
            case 71060:
            case 71061:
                return resources.getString(R.string.ice_pallets);

            case 7101:
            case 71010:
            case 71011:
            case 7113:
            case 71130:
            case 71131:
            case 7114:
            case 71140:
            case 71141:
            case 7116:
            case 71160:
            case 71161:
            case 7103:
            case 71030:
            case 71031:
                return resources.getString(R.string.heavy_ice_pallets);

            case 8000:
            case 80000:
            case 80001:
            case 8001:
            case 80010:
            case 80011:
            case 8003:
            case 80030:
            case 80031:
            case 8002:
            case 80020:
            case 80021:
                return resources.getString(R.string.thunderstorm);

            case 0:
                return resources.getString(R.string.weather_na);
        }

        return null;
    }

    static WEATHER_ICON getIcon(int weatherCode) {

        switch (weatherCode) {

            case 1000:  // Clear
            case 10000:
                return WEATHER_ICON.CLEAR_DAY;

            case 10001:
                return WEATHER_ICON.CLEAR_NIGHT;

            case 1100:  // Mostly Clear
            case 11000:
            case 1101:  // Partly Cloudy
            case 11010:
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;

            case 11001:
            case 11011:
                return WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            case 1102:  // Mostly Cloudy
            case 11020:
                return WEATHER_ICON.MOSTLY_CLOUDY_DAY;

            case 11021:
                return WEATHER_ICON.MOSTLY_CLOUDY_NIGHT;

            case 1001:  // Cloudy
            case 10010:
            case 10011:
                return WEATHER_ICON.CLOUDY;

            case 1103:  // Partly Cloudy & Mostly Clear
            case 11030:
                return WEATHER_ICON.PARTLY_CLOUDY_DAY;

            case 11031:
                return WEATHER_ICON.PARTLY_CLOUDY_NIGHT;

            case 2100:  // Light Fog
            case 21000:
            case 21001:
            case 2000:  // Fog
            case 20000:
            case 20001:
                return WEATHER_ICON.CLOUDY_FOG;

            case 2101:  // Mostly Clear & Light Fog
            case 21010:
            case 2102:  // Partly Cloudy & Light Fog
            case 21020:
            case 2103:  // Mostly Cloudy & Light Fog
            case 21030:
            case 2106:  // Mostly Clear & Fog
            case 21060:
            case 2107:  // Partly Cloudy & Fog
            case 21070:
            case 2108:  // Mostly Cloudy & Fog
            case 21080:
                return WEATHER_ICON.CLOUDY_FOG_DAY;

            case 21011:
            case 21021:
            case 21031:
            case 21061:
            case 21071:
            case 21081:
                return WEATHER_ICON.CLOUDY_FOG_NIGHT;

            case 4000:  // Drizzle
            case 40000:
                return WEATHER_ICON.DRIZZLE;

            case 40001:
                return WEATHER_ICON.DRIZZLE;

            case 4200:  // Light Rain
            case 42000:
            case 42001:
                return WEATHER_ICON.LIGHT_RAIN;

            case 4001:  // Rain
            case 40010:
            case 40011:
                return WEATHER_ICON.RAIN;

            case 4201:  // Heavy Rain
            case 42010:
            case 42011:
                return WEATHER_ICON.HEAVY_RAIN;

            case 4203:  // Mostly Clear & Drizzle
            case 42030:
            case 4204:  // Partly Cloudy & Drizzle
            case 42040:
            case 4205:  // Mostly Cloudy & Drizzle
            case 42050:
            case 4213:  // Mostly Clear & Light Rain
            case 42130:
            case 4214:  // Partly Cloudy & Light Rain
            case 42140:
            case 4215:  // Mostly Cloudy & Light Rain
            case 42150:
                return WEATHER_ICON.LIGHT_RAIN_DAY;

            case 42031:
            case 42041:
            case 42051:
            case 42131:
            case 42141:
            case 42151:
                return WEATHER_ICON.LIGHT_RAIN_NIGHT;

            case 4209:  // Mostly Clear & Rain
            case 42090:
            case 4208:  // Partly Cloudy & Rain
            case 42080:
            case 4210:  // Mostly Cloudy & Rain
            case 42100:
                return WEATHER_ICON.RAIN_DAY;

            case 42091:
            case 42081:
            case 42101:
                return WEATHER_ICON.RAIN_NIGHT;

            case 4211:  // Mostly Clear & Heavy Rain
            case 42110:
            case 4202:  // Partly Cloudy & Heavy Rain
            case 42020:
            case 4212:  // Mostly Cloudy & Heavy Rain
            case 42120:
                return WEATHER_ICON.HEAVY_RAIN_DAY;

            case 42111:
            case 42021:
            case 42121:
                return WEATHER_ICON.HEAVY_RAIN_NIGHT;

            case 5001:  // Flurries
            case 50010:
            case 50011:
            case 5100:  // Light Snow
            case 51000:
            case 51001:
                return WEATHER_ICON.LIGHT_SNOW;

            case 5000:  // Snow
            case 50000:
            case 50001:
                return WEATHER_ICON.SNOW;

            case 5101:  // Heavy Snow
            case 51010:
            case 51011:
                return WEATHER_ICON.HEAVY_SNOW;

            case 5115:  // Mostly Clear & Flurries
            case 51150:
            case 5116:  // Partly Cloudy & Flurries
            case 51160:
            case 5117:  // Mostly Cloudy & Flurries
            case 51170:
            case 5102:  // Mostly Clear & Light Snow
            case 51020:
            case 5103:  // Partly Cloudy & Light Snow
            case 51030:
            case 5104:  // Mostly Cloudy & Light Snow
            case 51040:
            case 5105:  // Mostly Clear & Snow
            case 51050:
            case 5106:  // Partly Cloudy & Snow
            case 51060:
            case 5107:  // Mostly Cloudy & Snow
            case 51070:
            case 5119:  // Mostly Clear & Heavy Snow
            case 51190:
            case 5120:  // Partly Cloudy & Heavy Snow
            case 51200:
            case 5121:  // Mostly Cloudy & Heavy Snow
            case 51210:
                return WEATHER_ICON.SNOW_DAY;

            case 51151:
            case 51161:
            case 51171:
            case 51021:
            case 51031:
            case 51041:
            case 51051:
            case 51061:
            case 51071:
            case 51191:
            case 51201:
            case 51211:
                return WEATHER_ICON.SNOW_NIGHT;

            case 5122:  // Drizzle & Light Snow
            case 51220:
            case 51221:
            case 5110:  // Drizzle & Snow
            case 51100:
            case 51101:
            case 5108:  // Rain & Snow
            case 51080:
            case 51081:
            case 5114:  // Snow & Freezing Rain
            case 51140:
            case 51141:
                return WEATHER_ICON.RAIN_AND_SNOW;

            case 5112:  // Snow & Ice Pellets
            case 51120:
            case 51121:
                return WEATHER_ICON.SNOW_AND_HAIL;

            case 6000:  // Freezing Drizzle
            case 60000:
            case 60001:
            case 6200:  // Light Freezing Drizzle
            case 62000:
            case 62001:
            case 6001:  // Freezing Rain
            case 60010:
            case 60011:
            case 6201:  // Heavy Freezing Rain
            case 62010:
            case 62011:
                return WEATHER_ICON.FREEZING_RAIN;

            case 6003:  // Mostly Clear & Freezing Drizzle
            case 60030:
            case 6002:  // Partly Cloudy & Freezing Drizzle
            case 60020:
            case 6004:  // Mostly Cloudy & Freezing Drizzle
            case 60040:
            case 6204:  // Light Rain & Freezing Drizzle
            case 62040:
            case 6205:  // Mostly Clear & Light Freezing Rain
            case 62050:
            case 6203:  // Partly Cloudy & Light Freezing Rain
            case 62030:
            case 6209:  // Mostly Cloudy & Light Freezing Rain
            case 62090:
            case 6213:  // Mostly Clear & Freezing Rain
            case 62130:
            case 6214:  // Partly Cloudy & Freezing Rain
            case 62140:
            case 6215:  // Mostly Cloudy & Freezing Rain
            case 62150:
            case 6212:  // Drizzle & Freezing Rain
            case 62120:
            case 6220:  // Light Rain & Freezing Rain
            case 62200:
            case 6222:  // Rain & Freezing Rain
            case 62220:
            case 6207:  // Mostly Clear & Heavy Freezing Rain
            case 62070:
            case 6202:  // Partly Cloudy & Heavy Freezing Rain
            case 62020:
            case 6208:  // Mostly Cloudy & Heavy Freezing Rain
            case 62080:
                return WEATHER_ICON.FREEZING_RAIN_DAY;

            case 60031:
            case 60021:
            case 60041:
            case 62041:
            case 62051:
            case 62031:
            case 62091:
            case 62131:
            case 62141:
            case 62151:
            case 62121:
            case 62201:
            case 62221:
            case 62071:
            case 62021:
            case 62081:
                return WEATHER_ICON.FREEZING_RAIN_NIGHT;

            case 7102:  // Light Ice Pellets
            case 71020:
            case 71021:
            case 7000:  // Ice Pellets
            case 70000:
            case 70001:
            case 7101:  // Heavy Ice Pellets
            case 71010:
            case 71011:
                return WEATHER_ICON.HAIL;

            case 7111:  // Partly Cloudy & Light Ice Pellets
            case 71110:
            case 7112:  // Mostly Cloudy & Light Ice Pellets
            case 71120:
            case 7108:  // Mostly Clear & Ice Pellets
            case 71080:
            case 7107:  // Partly Cloudy & Ice Pellets
            case 71070:
            case 7109:  // Mostly Cloudy & Ice Pellets
            case 71090:
            case 7113:  // Mostly Clear & Heavy Ice Pellets
            case 71130:
            case 7114:  // Partly Cloudy & Heavy Ice Pellets
            case 71140:
            case 7116:  // Mostly Cloudy & Heavy Ice Pellets
            case 71160:
                return WEATHER_ICON.HAIL_DAY;

            case 71111:
            case 71121:
            case 71081:
            case 71071:
            case 71091:
            case 71131:
            case 71141:
            case 71161:
                return WEATHER_ICON.HAIL_NIGHT;

            case 7105:  // Drizzle & Ice Pellets
            case 71050:
            case 71051:
            case 7115:  // Light Rain & Ice Pellets
            case 71150:
            case 71151:
            case 7117:  // Rain & Ice Pellets
            case 71170:
            case 71171:
            case 7106:  // Freezing Rain & Ice Pellets
            case 71060:
            case 71061:
                return WEATHER_ICON.HAIL;

            case 7103:  // Freezing Rain & Heavy Ice Pellets
            case 71030:
            case 71031:
                return WEATHER_ICON.HEAVY_HAIL;

            case 8000:  // Thunderstorm
            case 80000:
            case 80001:
                return WEATHER_ICON.THUNDERSTORM_WITH_RAIN;

            case 8001:  // Mostly Clear & Thunderstorm
            case 80010:
            case 8003:  // Partly Cloudy & Thunderstorm
            case 80030:
            case 8002:  // Mostly Cloudy & Thunderstorm
            case 80020:
                return WEATHER_ICON.THUNDERSTORM_WITH_RAIN_DAY;

            case 80011:
            case 80031:
            case 80021:
                return WEATHER_ICON.THUNDERSTORM_WITH_RAIN_NIGHT;

            default:
                return WEATHER_ICON.NA;

        }

    }

}
