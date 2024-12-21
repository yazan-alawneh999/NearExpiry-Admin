package com.big0soft.resource.data;
import android.content.Context;
import android.content.SharedPreferences;


import androidx.annotation.Nullable;


import com.big0soft.resource.BuildConfig;
import com.big0soft.resource.gson.FromJsonImpl;

public class BaseSharedPreferenceDao implements SharedPreferenceDao {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public static final String KEY_BASE_FILE = BuildConfig.LIBRARY_PACKAGE_NAME+".base";

    public BaseSharedPreferenceDao(SharedPreferences sharedPreference, SharedPreferences.Editor editor) {
        this.sharedPreferences = sharedPreference;
        this.editor = editor;
    }
    public BaseSharedPreferenceDao(SharedPreferences sharedPreference) {
        this.sharedPreferences = sharedPreference;
        this.editor = sharedPreference.edit();
    }


    public BaseSharedPreferenceDao(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY_BASE_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public String getString(String key, @Nullable String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    @Override
    public SharedPreferenceDao putObject(String key, ToJson toJson) {
        return putString(key, toJson.toJson());
    }

    @Override
    public FromJson getObject(String key, FromJson fromJson) {
        return fromJson.fromJson(getString(key));
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public SharedPreferenceDao putString(String key, @Nullable String value) {
        editor.putString(key, value);
        return this;
    }


    @Override
    public SharedPreferenceDao putInt(String key, int value) {
        editor.putInt(key, value);
        return this;
    }

    @Override
    public SharedPreferenceDao putLong(String key, long value) {
        editor.putLong(key, value);
        return this;
    }

    @Override
    public SharedPreferenceDao putFloat(String key, float value) {
        editor.putFloat(key, value);
        return this;
    }

    @Override
    public SharedPreferenceDao putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return this;
    }

    /**
     * @param key remove value by key
     */
    @Override
    public SharedPreferenceDao remove(String key) {
        editor.remove(key);
        return this;
    }

    /**
     * clear all data in file
     */
    @Override
    public SharedPreferenceDao clear() {
        editor.clear();
        return this;
    }

    /**
     * apply changes
     */
    @Override
    public void apply() {
        editor.apply();
    }
}
