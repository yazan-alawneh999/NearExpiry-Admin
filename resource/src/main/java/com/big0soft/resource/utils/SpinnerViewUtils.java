package com.big0soft.resource.utils;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;

import java.util.List;

public class SpinnerViewUtils {
    /**
     * setup spinner with default view
     */
    @BindingAdapter(value = {"android:spinnerAdapter"})
    public static void setSpinner(Spinner spinner, List<String> spinnerList){
        if (spinnerList==null)return;
        if (spinnerList.isEmpty())return;
        ArrayAdapter<String> adaptUser = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_list_item_1, spinnerList);
        spinner.setAdapter(adaptUser);
    }
}
