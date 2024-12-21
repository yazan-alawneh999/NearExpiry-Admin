package com.big0soft.resource.adapter;

import static com.google.android.material.R.style.Widget_Material3_Button_Icon;
import static com.google.android.material.R.style.Widget_Material3_Button_OutlinedButton;

import android.widget.Button;

import androidx.databinding.BindingAdapter;

import com.google.android.material.button.MaterialButton;

public final class BindingUtils {
    @BindingAdapter("buttonStyle")
    public static void setButtonStyle(MaterialButton button, boolean select) {
//        int styleId = select ? Widget_Material3_Button_OutlinedButton : Widget_Material3_Button_Icon;
//        button.setBackgroundResource(styleId);
    }
}
