package com.big0soft.resource.data;

import androidx.annotation.Nullable;

import com.big0soft.resource.gson.FromJsonImpl;


public interface SharedPreferenceDao {
    String DEFAULT_APPLICATION_FILE = "com.big0soft.resource" + ".default.save_data.file";

    String getString(String key, @Nullable String defValue);

    default String getString(String key) {
        return getString(key, "");
    }


    boolean contains(String key);

    int getInt(String key, int defValue);

    default int getInt(String key) {
        return getInt(key, 0);
    }

    long getLong(String key, long defValue);

    default long getLong(String key) {
        return getLong(key, 0L);
    }

    float getFloat(String key, float defValue);

    default float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    boolean getBoolean(String key, boolean defValue);

    default boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    SharedPreferenceDao putObject(String key, ToJson toJson);

    FromJson getObject(String key, FromJson fromJson);


    SharedPreferenceDao putString(String key, @Nullable String value);


    SharedPreferenceDao putInt(String key, int value);


    SharedPreferenceDao putLong(String key, long value);


    SharedPreferenceDao putFloat(String key, float value);


    SharedPreferenceDao putBoolean(String key, boolean value);


    SharedPreferenceDao remove(String key);

    SharedPreferenceDao clear();

    void apply();
}
