package ir.mtapps.weatherlib.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CacheDao {

    @Query("SELECT * FROM cache WHERE provider LIKE :provider AND latitude = :lat AND longitude = :lng")
    Cache[] getCache(String provider, double lat, double lng);

    @Query("SELECT id FROM cache WHERE provider LIKE :provider AND latitude = :lat AND longitude = :lng")
    CacheId[] isCacheExist(String provider, double lat, double lng);

    @Query("SELECT * FROM cache")
    Cache[] getAllCaches();

    @Query("SELECT * FROM cache WHERE ((weather_updated_at IS NOT NULL AND weather_updated_at < :reference)" +
            " OR " +
            "(daily_updated_at IS NOT NULL AND daily_updated_at < :reference)" +
            " OR " +
            "(hourly_updated_at IS NOT NULL AND hourly_updated_at < :reference))")
    Cache[] getExpiredCache(long reference);

    @Insert
    void insert(Cache cache);

    @Update
    void update(Cache cache);

    @Delete
    void delete(Cache... cache);

}
