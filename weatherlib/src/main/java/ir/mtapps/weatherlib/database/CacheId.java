package ir.mtapps.weatherlib.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cache")
public class CacheId {

    @PrimaryKey(autoGenerate = true)
    public int id;

}
