package ir.mtapps.weatherlib.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import ir.mtapps.weatherlib.enums.PROVIDER;

@Entity(tableName = "cache")
public class Cache {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "provider", index = true)
    @NonNull
    private String provider;

    @ColumnInfo(name = "latitude", index = true)
    private double latitude;

    @ColumnInfo(name = "longitude", index = true)
    private double longitude;

    @Embedded
    private WeatherCache weatherCache;

    @Embedded
    private HourlyWeatherCache hourlyWeatherCache;

    @Embedded
    private DailyWeatherCache dailyWeatherCache;

    @Embedded
    private UvCache uvCache;

    @Embedded
    private GeoCache geoCache;


    public static class WeatherCache {

        WeatherCache(@NonNull String cache, long updatedAt) {
            this.cache = cache;
            this.updatedAt = updatedAt;
        }

        @ColumnInfo(name = "weatherCache")
        private String cache;

        @ColumnInfo(name = "weather_updated_at")
        private long updatedAt;

        public String getCache() {
            return this.cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public long getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class HourlyWeatherCache {

        HourlyWeatherCache(@NonNull String cache, long updatedAt) {
            this.cache = cache;
            this.updatedAt = updatedAt;
        }

        @ColumnInfo(name = "hourly_weather")
        private String cache;

        @ColumnInfo(name = "hourly_updated_at")
        private long updatedAt;

        public String getCache() {
            return this.cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public long getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class DailyWeatherCache {

        DailyWeatherCache(@NonNull String cache, long updatedAt) {
            this.cache = cache;
            this.updatedAt = updatedAt;
        }

        @ColumnInfo(name = "daily_weather")
        private String cache;

        @ColumnInfo(name = "daily_updated_at")
        private long updatedAt;

        public String getCache() {
            return this.cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public long getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class UvCache {

        UvCache(@NonNull String cache, long updatedAt) {
            this.cache = cache;
            this.updatedAt = updatedAt;
        }

        @ColumnInfo(name = "uvCache")
        private String cache;

        @ColumnInfo(name = "uv_updated_at")
        private long updatedAt;

        public String getCache() {
            return this.cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public long getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class GeoCache {

        GeoCache(@NonNull String cache, long updatedAt) {
            this.cache = cache;
            this.updatedAt = updatedAt;
        }

        @ColumnInfo(name = "go_cache")
        private String cache;

        @ColumnInfo(name = "geo_updated_at")
        private long updatedAt;

        public String getCache() {
            return this.cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public long getUpdatedAt() {
            return this.updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "" + provider + " [" + latitude + ", " + longitude + "] {"
                + "\n\t" + "WC: " + (weatherCache == null || weatherCache.getCache() == null ? "NULL" :  weatherCache.getCache().substring(0, 10))
                + "\n\t" + "HC: " + (hourlyWeatherCache == null || hourlyWeatherCache.getCache() == null ? "NULL" :  hourlyWeatherCache.getCache().substring(0, 10))
                + "\n\t" + "DC: " + (dailyWeatherCache == null || dailyWeatherCache.getCache() == null ? "NULL" :  dailyWeatherCache.getCache().substring(0, 10))
                + "\n\t" + "UV: " + (uvCache == null || uvCache.getCache() == null ? "NULL" :  uvCache.getCache().substring(0, 10))
                + "\n\t" + "GEO: " + (geoCache == null || geoCache.getCache() == null ? "NULL" :  geoCache.getCache().substring(0, 10))
                + "\n}";
    }

    // *********************************************************************************************
    // *                                    Getters and Setters                                    *
    // *********************************************************************************************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getProvider() {
        return provider;
    }

    public void setProvider(@NonNull String provider) {
        this.provider = provider;
    }

    public void setProvider(@NonNull PROVIDER provider) {
        this.provider = provider.toString();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public WeatherCache getWeatherCache() {
        return this.weatherCache;
    }

    public void setWeatherCache(WeatherCache cache) {
        this.weatherCache = cache;
    }

    public void setWeatherCache(@NonNull String cache, long updatedAt) {
        this.weatherCache = new WeatherCache(cache, updatedAt);
    }

    public HourlyWeatherCache getHourlyWeatherCache() {
        return this.hourlyWeatherCache;
    }

    public void setHourlyWeatherCache(HourlyWeatherCache cache) {
        this.hourlyWeatherCache = cache;
    }

    public void setHourlyWeatherCache(@NonNull String cache, long updatedAt) {
        this.hourlyWeatherCache = new HourlyWeatherCache(cache, updatedAt);
    }

    public DailyWeatherCache getDailyWeatherCache() {
        return this.dailyWeatherCache;
    }

    public void setDailyWeatherCache(DailyWeatherCache cache) {
        this.dailyWeatherCache = cache;
    }

    public void setDailyWeatherCache(@NonNull String cache, long updatedAt) {
        this.dailyWeatherCache = new DailyWeatherCache(cache, updatedAt);
    }

    public UvCache getUvCache() {
        return this.uvCache;
    }

    public void setUvCache(UvCache cache) {
        this.uvCache = cache;
    }

    public void setUvCache(@NonNull String cache, long updatedAt) {
        this.uvCache = new UvCache(cache, updatedAt);
    }

    public GeoCache getGeoCache() {
        return this.geoCache;
    }

    public void setGeoCache(GeoCache cache) {
        this.geoCache = cache;
    }

    public void setGeoCache(@NonNull String cache, long updatedAt) {
        this.geoCache = new GeoCache(cache, updatedAt);
    }
}
