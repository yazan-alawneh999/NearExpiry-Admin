package com.big0soft.resource.view.binding;

import android.text.TextUtils;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.android.material.color.utilities.MathUtils;

import java.util.Locale;
import java.util.Random;

public class TextViewAdapter {

    @BindingAdapter("android:_localeText")
    public static void parseLocaleToTextView(TextView textView, String country) {
        if (TextUtils.isEmpty(country)) return;
        country = country.toUpperCase();
        Locale locale = new Locale("", country);
        textView.setText(locale.getDisplayCountry());
    }

    @BindingAdapter(value = {"android:_randomIntMax",
            "android:_randomIntMin"},requireAll = false)
    public static void randomInt(TextView textView, int max,int min) {
        if (max < 0) throw new RuntimeException("max must be greater than 0");
        if (min < 0) {
            min = 0;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        textView.setText(String.valueOf(randomNumber));
    }
}
