package com.big0soft.resource.screen;

import androidx.appcompat.app.AppCompatActivity;

/**
 * interface used for top screen bar configuration
 * @see WindowScreenImpl use this as default {@link WindowScreen} initialze
 */
public interface WindowScreen {

    void windowScreen(AppCompatActivity activity);
}
