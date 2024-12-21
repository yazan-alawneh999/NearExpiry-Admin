package com.big0soft.resource.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputLayout;

public final class TextInputLayoutUtils {
    @BindingAdapter(
            value = "android:onClickEndIcon"
    )
    public static void setOnClickEndIcon(TextInputLayout inputLayout, View.OnClickListener endIconOnClickListener) {
        inputLayout.setEndIconOnClickListener(endIconOnClickListener);
    }

    @BindingAdapter(
            value = "android:onClickStartIcon"
    )
    public static void setOnClickStartIcon(TextInputLayout inputLayout, View.OnClickListener endIconOnClickListener) {
        inputLayout.setStartIconOnClickListener(endIconOnClickListener);
    }
}
