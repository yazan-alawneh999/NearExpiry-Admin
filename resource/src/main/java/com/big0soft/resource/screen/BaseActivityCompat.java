package com.big0soft.resource.screen;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.big0soft.resource.view.SnackbarUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * user this custom {@link BaseActivityCompat}
 * for all code like {@link #windowScreenConfiguration(WindowScreen)}
 */
public class BaseActivityCompat extends AppCompatActivity implements SnackbarUtils.SnackbarUi{

    /**
     * @param windowScreen window configuration
     * @see WindowScreenImpl default impl
     */
    public void windowScreenConfiguration(WindowScreen windowScreen) {
        windowScreen.windowScreen(this);
    }

    @Override
    public Snackbar snackbar(Object message) {
        String ms = "";
        if (message instanceof Integer){
            ms = getString(Integer.parseInt(message.toString()));
        }
        if (message instanceof String){
            ms = String.valueOf(message);
        }
        Snackbar snackbar = Snackbar.make(getRootView(),  ms, SnackbarUtils.LONG);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
        return snackbar;
    }

    public View getRootView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);

    }





}
