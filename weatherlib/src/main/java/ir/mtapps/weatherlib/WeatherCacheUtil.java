package ir.mtapps.weatherlib;

import android.content.Context;

import androidx.room.Room;
import ir.mtapps.weatherlib.database.Cache;
import ir.mtapps.weatherlib.database.Database;
import ir.mtapps.weatherlib.enums.PROVIDER;

public class WeatherCacheUtil {

    private final static int CACHE_VALIDITY = 3600000;

    public static int clearAllCaches(Context context) {

        // TODO: run database builder in asynctask
        Database db = Room.databaseBuilder(context,
                Database.class, "weather_cache").allowMainThreadQueries().build();

        Cache[] caches = db.cacheDao().getAllCaches();

        int count = caches.length;

        db.cacheDao().delete(caches);

        return count;
    }

    public static int clearExpiredCaches(Context context) {

        // TODO: run database builder in asynctask
        Database db = Room.databaseBuilder(context,
                Database.class, "weather_cache").allowMainThreadQueries().build();

        Cache[] caches = db.cacheDao().getExpiredCache(System.currentTimeMillis() - CACHE_VALIDITY);

        int count = caches.length;

        db.cacheDao().delete(caches);

        return count;
    }

    public static int clearExpiredCaches(Context context, long beforeThisTime) {

        // TODO: run database builder in asynctask
        Database db = Room.databaseBuilder(context,
                Database.class, "weather_cache").allowMainThreadQueries().build();

        Cache[] caches = db.cacheDao().getExpiredCache(beforeThisTime);

        int count = caches.length;

        db.cacheDao().delete(caches);

        return count;
    }

    public static int clearCaches(Context context, PROVIDER provider, double latitude, double longitude) {

        // TODO: run database builder in asynctask
        Database db = Room.databaseBuilder(context,
                Database.class, "weather_cache").allowMainThreadQueries().build();

        Cache[] caches = db.cacheDao().getCache(provider.toString(), latitude, longitude);

        int count = caches.length;

        db.cacheDao().delete(caches);

        return count;
    }
}
