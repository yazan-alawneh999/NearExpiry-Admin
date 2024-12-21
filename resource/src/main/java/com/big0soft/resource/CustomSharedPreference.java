package com.big0soft.resource;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.big0soft.resource.helper.TAGs;

import java.util.Locale;

public class CustomSharedPreference {
    private static CustomSharedPreference instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final FILES fileName;

    private CustomSharedPreference(Context context, FILES filename) {
        this.fileName = filename;
        this.sharedPreferences = context.getSharedPreferences(context.getPackageName() + "." + filename.name().toLowerCase(Locale.ROOT), Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static CustomSharedPreference getInstance(Context context, FILES filename) {
        if (instance == null) {
            instance = new CustomSharedPreference(context, filename);
        } else if (!instance.fileName.equals(filename)) {
            instance = new CustomSharedPreference(context, filename);
        }
        return instance;
    }

    public enum FILES {
        APPLICATION, USER
    }


    // TODO: 11/30/2021 get value

    public String getStringValue(String key) {
        return getStringValue(key, "");
    }

    public String getStringValue(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public int getIntValue(String key) {
        return getIntValue(key, 0);
    }

    public int getIntValue(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }


    public float getFloatValue(String key) {
        return getFloatValue(key, 0F);
    }

    public float getFloatValue(String key, float value) {
        return sharedPreferences.getFloat(key, value);
    }


    public long getLongValue(String key) {
        return getLongValue(key, 0L);
    }

    public long getLongValue(String key, long value) {
        return sharedPreferences.getLong(key, value);
    }


    public boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public CustomSharedPreference putNumber(String key, Number value) {
        if (value instanceof Integer) {
            editor.putInt(key, value.intValue());
        } else if (value instanceof Float) {
            editor.putFloat(key, value.floatValue());
        } else if (value instanceof Long) {
            editor.putLong(key, value.longValue());
        }
        return this;
    }


    public CustomSharedPreference putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return this;
    }


    public CustomSharedPreference putString(String key, String value) {
        editor.putString(key, value);
        return this;
    }


    public boolean commit() {
        return editor.commit();
    }

    public static void removeAll(Context context) {
        CustomSharedPreference[] managerSharedPreferences = new CustomSharedPreference[FILES.values().length];
        for (int i = 0; i < FILES.values().length; i++) {
            FILES filename = FILES.values()[i];
            Log.d(TAGs.TAG, "removeAll: " + filename);
            managerSharedPreferences[i] = new CustomSharedPreference(context, filename);
            managerSharedPreferences[i].editor.clear().commit();
        }
    }
}

