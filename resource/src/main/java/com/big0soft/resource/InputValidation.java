package com.big0soft.resource;

import android.text.TextUtils;

import java.util.function.Predicate;

public class InputValidation  {
    public static final Predicate<String> predicate = TextUtils::isEmpty;

}
