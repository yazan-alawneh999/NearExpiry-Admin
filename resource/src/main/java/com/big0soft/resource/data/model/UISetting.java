package com.big0soft.resource.data.model;

import androidx.annotation.NonNull;


public class UISetting {
    public static final String KEY_SETTING = "SETTING";
    private String language;
    private UIMode uiMode;

    public UISetting(String language, UIMode uiMode) {
        this.language = language;
        this.uiMode = uiMode;
    }

    public UISetting(String language) {
        this(language, UIMode.DEFAULT);
    }

    public UISetting() {
        this("en");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public UIMode getUiMode() {
        return uiMode;
    }

    public void setUiMode(UIMode uiMode) {
        this.uiMode = uiMode;
    }

    public enum UIMode {DEFAULT, DARK_MODE, LIGHT_MODEL}

    @NonNull
    @Override
    public String toString() {
        return "SettingEntity{" +
                "language='" + language + '\'' +
                ", uiMode=" + uiMode +
                '}';
    }
}
