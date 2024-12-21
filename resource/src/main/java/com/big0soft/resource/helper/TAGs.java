package com.big0soft.resource.helper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.big0soft.resource.BuildConfig;


public class TAGs {

    @NonNull
    public static final String TAG = BuildConfig.DEBUG ? TAGs.class.getPackage().getName() : "";

    public static String TAG(Class<?> cClass) {
        if (!BuildConfig.DEBUG) return "";
        return cClass.getSimpleName();
    }

    public static String line() {
        return "_______________________________________";
    }

    public static String TAGPackage(Class<?> cClass) {
        return cClass.getPackage().getName() + "." + cClass.getSimpleName();
    }

    public static void debugToast(Context context, String text) {
        if (!BuildConfig.DEBUG) return;
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void debugToast(Activity activity, String text) {
        activity.runOnUiThread(() -> {
            debugToast(((Context) activity), text);
        });
    }
}
