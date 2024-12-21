package com.big0soft.resource.screen;

import android.app.Application;

import androidx.navigation.NavController;

public interface OnScreenRequire {
    NavController controller();

    Application application();
}
