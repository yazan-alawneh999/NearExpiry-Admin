package com.big0soft.resource.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class ViewUtils {
    @BindingAdapter({"app:goneVisibility"})
    public static void goneVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = {
            "android:handleNull"
    })
    public static void setNullHandle(View view,boolean ignore){

    }


    @BindingAdapter({"app:invisibleVisibility"})
    public static void invisibleVisibility(View view, Boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }



    @BindingAdapter(value = {"android:rowColor"})
    public static void setRowColorByQty(View view, double qty) {
        if (qty > 10) {
            view.setBackgroundColor(Color.parseColor("#008000")); //green color
        } else if (qty == 0) {
            view.setBackgroundColor(Color.parseColor("#f42229")); //green color
        } else if (qty >= 1) {
            view.setBackgroundColor(Color.parseColor("#454cbf")); //green color
        }
    }

    @BindingAdapter(value = {"android:textColorQty"})
    public static void setTextColorByQty(TextView view, double qty) {
        if (qty > 10) {
            view.setTextColor(Color.parseColor("#008000")); //green color
        } else if (qty == 0) {
            view.setTextColor(Color.parseColor("#f42229"));//red color
        } else if (qty >= 1) {
            view.setTextColor(Color.parseColor("#454cbf"));
        }
    }

}
