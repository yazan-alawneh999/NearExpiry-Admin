package com.big0soft.resource.utils;

import android.icu.text.SimpleDateFormat;

import androidx.core.util.ObjectsCompat;

import java.util.Date;
import java.util.Locale;


public class StrUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.getBytes().length == 0;
    }

    public static boolean compare(String s1, String s2) {
        return ObjectsCompat.equals(s1, s2);
    }


    public static boolean isNotEmpty(String s) {
        return s != null && s.getBytes().length != 0;
    }

    public static String getFileType(String fileName) {
        return fileName.split("\\.")[fileName.split("\\.").length - 1];
    }

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss", Locale.US).format(new Date());
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
    }

    public static boolean isKeyAndPasswordPattern(String input) {
        String[] split = input.split(":");
        return split.length == 2 && !isEmpty(split[0]) && !isEmpty(split[1]);
    }

    public static String[] exportKeyAndPasswordPattern(String input) {
        String[] split = input.split(":");
        return split;
    }

}

