package com.istur.android_starter.source.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.istur.android_starter.model.Location;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Location location);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertAll(List<Location> locations);

    @Query("DELETE FROM location_table")
    void deleteAll();

    @Query("SELECT * FROM location_table ORDER BY name ASC")
    Observable<List<Location>> getLocationsList();

    @Query("SELECT * FROM location_table WHERE id = :locationId")
    Observable<Location> getLocationById(int locationId);

    @Query("SELECT * FROM location_table WHERE myCode = :code")
    Observable<Location> getLocationByCrscode(String code);

    @Query("SELECT * FROM location_table WHERE myCode IS NOT NULL ORDER BY name ASC")
    Observable<List<Location>> getLocationListHasCrsCode();
}
