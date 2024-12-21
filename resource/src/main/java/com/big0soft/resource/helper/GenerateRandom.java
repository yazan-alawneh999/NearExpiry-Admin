package com.big0soft.resource.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GenerateRandom {
    public static String generateRandomName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        return sdf.format(new Date());
    }
}
