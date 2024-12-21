package com.big0soft.resource.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.data.SharedPreferenceDaoImpl;

import java.util.Locale;


public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "setting.Selected.Language";
    public static final String FILE_SETTING = "application.setting";
    public static final String ARABIC = "ar";
    public static final String ENGLISH = "en";
    public static String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage();

    public static Context onAttach(Context context) {
        String lang = getPersistedData(context, DEFAULT_LANGUAGE);
        return setLocale(context, lang);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        return setLocale(context, lang);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, DEFAULT_LANGUAGE);
    }

    public static Context setLocale(Context context, String language) {
        persist(context, language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        return updateResourcesLegacy(context, language);
    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferenceDao managerSharedPreferences = sharedPreference(context);
        return managerSharedPreferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferenceDao sharedPreferenceDao = sharedPreference(context);
        sharedPreferenceDao.putString(SELECTED_LANGUAGE, language).
        apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }
        return context;
    }

    private static SharedPreferenceDao sharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_SETTING, Context.MODE_PRIVATE);
        return SharedPreferenceDaoImpl.getInstance(sharedPreferences, sharedPreferences.edit());
    }
}
