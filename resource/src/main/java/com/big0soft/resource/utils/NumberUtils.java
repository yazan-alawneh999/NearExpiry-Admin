package com.big0soft.resource.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isShort(String str) {
        try {
            Short.parseShort(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isByte(String str) {
        try {
            Byte.parseByte(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isBigDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isBigInteger(String str) {
        try {
            new BigInteger(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isBoolean(String str) {
        try {
            return Boolean.parseBoolean(str);
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isBoolean(String str, boolean defaultValue) {
        return isBoolean(str) ? Boolean.parseBoolean(str) : defaultValue;
    }


    public static boolean isNumber(String str) {
        return isInteger(str) || isLong(str) || isDouble(str) || isFloat(str) || isShort(str) || isByte(str) || isBigDecimal(str) || isBigInteger(str);
    }

    public static boolean isNumber(String str, boolean defaultValue) {
        return isNumber(str) || defaultValue;
    }

    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    public static int parseInt(String str, int defaultValue) {
        if (isInteger(str)) {
            return Integer.parseInt(str);
        }
        return defaultValue;
    }

    public static long parseLong(String str, long defaultValue) {
        if (isLong(str)) {
            return Long.parseLong(str);
        }
        return defaultValue;

    }

    public static long parseLong(String str) {
        return parseLong(str, 0);
    }

    public static float parseFloat(String str, float defaultValue) {
        if (isFloat(str)) {
            return Float.parseFloat(str);
        }
        return defaultValue;
    }

    public static float parseFloat(String str) {
        return parseFloat(str, 0);
    }


    public static double parseDouble(String str, double defaultValue) {
        if (isDouble(str)) {
            return Double.parseDouble(str);
        }
        return defaultValue;
    }

    public static double parseDouble(String str) {
        return parseDouble(str, 0);
    }

    public static short parseShort(String str, short defaultValue) {
        if (isShort(str)) {
            return Short.parseShort(str);
        }
        return defaultValue;
    }

    public static short parseShort(String str) {
        return parseShort(str, (short) 0);
    }


}
