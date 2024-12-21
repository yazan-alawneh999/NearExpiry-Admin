package com.big0soft.resource.utils.random;

import com.big0soft.resource.helper.DateHelper;

public final class RandomDate {
    public static String simpleMsDate() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String simpleFormatDate() {
        return DateHelper.dateHelper(DateHelper.FORMAT_WITHOUT_CODE);
    }
}
