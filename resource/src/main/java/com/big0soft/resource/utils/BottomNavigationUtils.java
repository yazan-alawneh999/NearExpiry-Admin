package com.big0soft.resource.utils;

import android.content.res.ColorStateList;

import androidx.core.graphics.ColorUtils;
import androidx.databinding.BindingAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationUtils {
    @BindingAdapter("android:_itemIconTint")
    public static void setItemIconTint(BottomNavigationView view, int color) {
        if (color == 0)
            view.setItemIconTintList(null);
        else
            view.setItemIconTintList(ColorStateList.valueOf(color));
    }
}
