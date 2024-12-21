package com.big0soft.resource.view.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.big0soft.resource.helper.ResourceHelper;

import java.util.Locale;

public class ImageViewAdapter {

    @BindingAdapter("android:_imageIdentifier")
    public static void setImageByIdentifier(ImageView view, String name){
        view.setImageResource(view.getResources().getIdentifier(name, "drawable", view.getContext().getPackageName()));
    }

    @BindingAdapter("android:_imageLocale")
    public static void setImageLocale(ImageView view, String name){
        String sb =  name.toLowerCase();
        setImageByIdentifier(view,sb);
    }
}
