package com.big0soft.resource.ui;

import static com.big0soft.resource.language.LocaleHelper.FILE_SETTING;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ScreenThemeMode {
    private static final String SELECTED_UI_MODE = "setting.Selected.ui_mode";

    public void setUiMode(Context context, int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
        editor(context).putInt(SELECTED_UI_MODE, mode).commit();
    }

    public int getUiMode() {
        return AppCompatDelegate.getDefaultNightMode();
    }

    private static SharedPreferences sharedPreference(Context context) {
        return context.getSharedPreferences(FILE_SETTING, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor editor(Context context) {
        return context.getSharedPreferences(FILE_SETTING, Context.MODE_PRIVATE).edit();
    }

}