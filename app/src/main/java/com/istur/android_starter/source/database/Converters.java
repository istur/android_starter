package com.istur.android_starter.source.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.istur.android_starter.model.Location;

import org.joda.time.DateTime;

import java.lang.reflect.Type;

public class Converters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static DateTime timestampToDateTime(Long value) {
        return value == null ? null : new DateTime(value);
    }

    @TypeConverter
    public static Long dateTimeToTimestamp(DateTime date) {
        return date == null ? null : date.getMillis();
    }

    @TypeConverter
    public static String locationToString(Location someObjects) {
        return gson.toJson(someObjects);
    }

    @TypeConverter
    public static Location stringToLocation(String data) {
        if (data == null) {
            return new Location();
        }
        Type locationType = new TypeToken<Location>() {
        }.getType();
        return gson.fromJson(data, locationType);
    }
}
