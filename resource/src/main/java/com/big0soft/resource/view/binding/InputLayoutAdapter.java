package com.big0soft.resource.view.binding;

import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputLayout;

public class InputLayoutAdapter {
    @BindingAdapter("android:_resError")
    public static void setError(TextInputLayout textInputLayout, int error) {
        if (error == -1) return;
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
        if (error == 0) return;
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(textInputLayout.getContext().getText(error));
    }
}
