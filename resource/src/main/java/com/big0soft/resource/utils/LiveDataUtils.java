package com.big0soft.resource.utils;

import androidx.lifecycle.LiveData;

public class LiveDataUtils {

    public static String getStringValue(LiveData<String> liveData) {
        if (liveData.getValue()==null)return "";
        return liveData.getValue();
    }

    public static int getIntegerValue(LiveData<Integer> liveData) {
        if (liveData.getValue()==null)return 0;
        return liveData.getValue();
    }
    public static boolean getBooleanValue(LiveData<Boolean> liveData) {
        return Boolean.TRUE.equals(liveData.getValue());
    }


}
