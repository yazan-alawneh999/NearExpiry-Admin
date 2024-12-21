package com.big0soft.resource.screen;

import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * default {@link WindowScreen} impl use this for full screen
 */
public class WindowScreenImpl implements WindowScreen {

    /**
     * @param activity full screen activity
     */
    @Override
    public void windowScreen(AppCompatActivity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
