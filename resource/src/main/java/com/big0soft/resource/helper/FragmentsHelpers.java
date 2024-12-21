package com.big0soft.resource.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.data.SharedPreferenceDaoImpl;

public class FragmentsHelpers {
    /**
     * @return default object of shared preference
     * @see SharedPreferenceDaoImpl
     */
    public static SharedPreferenceDao sharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferenceDao.DEFAULT_APPLICATION_FILE, Context.MODE_PRIVATE);
        return SharedPreferenceDaoImpl.getInstance(sharedPreferences, sharedPreferences.edit());
    }

    /**
     * @return return a object with new file name
     * @see SharedPreferenceDaoImpl
     */
    public static SharedPreferenceDao sharedPreference(Context context, String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("file name is empty or null");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return SharedPreferenceDaoImpl.getInstance(sharedPreferences, sharedPreferences.edit());
    }
}
