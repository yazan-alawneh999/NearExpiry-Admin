package com.big0soft.resource.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import com.big0soft.resource.BuildConfig;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.helper.FragmentsHelpers;
import com.big0soft.resource.helper.TAGs;
import com.google.gson.Gson;

import kotlinx.coroutines.BuildersKt;

/**
 * @see SharedPreferenceDao
 */
public class SharedPreferenceDaoImpl implements SharedPreferenceDao {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    static SharedPreferenceDaoImpl INSTANCE;
    public static final String KEY_DEFAULT_FILE = BuildConfig.LIBRARY_PACKAGE_NAME+".default";

    private SharedPreferenceDaoImpl(SharedPreferences sharedPreference, SharedPreferences.Editor editor) {
        this.sharedPreferences = sharedPreference;
        this.editor = editor;
    }
    protected SharedPreferenceDaoImpl(Context context) {
        this(context.getSharedPreferences(KEY_DEFAULT_FILE, Context.MODE_PRIVATE),
                context.getSharedPreferences(KEY_DEFAULT_FILE, Context.MODE_PRIVATE).edit());
    }



    public static SharedPreferenceDaoImpl getInstance(Context context) {
        if (INSTANCE == null) {
            Log.d(TAGs.TAG, "new shared preference Instance: cause there is no object");
            INSTANCE = new SharedPreferenceDaoImpl(context);
        }
        return INSTANCE;
    }

    public static SharedPreferenceDaoImpl getInstance(SharedPreferences sharedPreference, SharedPreferences.Editor editor) {
        if (INSTANCE == null) {
            Log.d(TAGs.TAG, "new shared preference Instance: cause there is no object");
            INSTANCE = new SharedPreferenceDaoImpl(sharedPreference, editor);
        }
        return INSTANCE;
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
