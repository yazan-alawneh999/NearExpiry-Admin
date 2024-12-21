package com.big0soft.resource.utils;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class RandomValueHelper {
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    public static final String NUMBERS = "123456789";

    public static String randomUID() {
        return UUID.randomUUID().toString();
    }

    public static String randomCharacter(String patternRandomString,int sizeValue) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < sizeValue) { // length of the random string.
            int index = (int) (rnd.nextFloat() * patternRandomString.length());
            salt.append(patternRandomString.charAt(index));
        }
        return salt.toString();
    }
}
