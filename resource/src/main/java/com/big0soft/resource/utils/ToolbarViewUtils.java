package com.big0soft.resource.utils;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;

public class ToolbarViewUtils {
    @BindingAdapter(value = {
            "android:onIconNavigationClick"
    })
    public static void setOnClickListener(Toolbar view, View.OnClickListener listener) {
//        androidx.databinding.adapters.ViewBindingAdapter.setClickListener();
        view.setNavigationOnClickListener(listener);

    }
}
