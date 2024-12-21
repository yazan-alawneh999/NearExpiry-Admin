package com.big0soft.resource.helper;

import android.graphics.Color;

public class ColorsHelper {
    public static String intToHexColor(int Int) {
        return "#" + Integer.toHexString(Int).toUpperCase().substring(2);
    }

    public static int[] hexColorToRGB(int Int) {
        int color = getColor(intToHexColor(Int));
        return new int[]{(color >> 16) & 0xFF, (color >> 8) & 0xFF, (color) & 0xFF, (color >> 24) & 0xFF};
    }
    public static int getColor(String color){
        return color != null && !color.isEmpty() ? Color.parseColor(color) : Color.parseColor("#ffffff");
    }
}
