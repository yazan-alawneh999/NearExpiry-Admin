package com.big0soft.resource.adapter.utils;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterUtils {
    public static final String TAG = "AdapterUtils";
    public static <T> boolean listenerCheck(T listener,int position){
        if (listener == null) {
            Log.i(TAG, "listenerCheck listener null: ");
            return false;
        }
        if (position== RecyclerView.NO_POSITION){
            Log.i(TAG, "listenerCheck position is : "+position);
            return false;
        }
        return true;
    }
}
