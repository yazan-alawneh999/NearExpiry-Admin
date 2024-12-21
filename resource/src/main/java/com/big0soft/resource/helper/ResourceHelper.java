package com.big0soft.resource.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.big0soft.resource.R;


public class ResourceHelper {
    public static String getString(Context context, @StringRes int rec) {
        return context.getResources().getString(rec);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int rec) {
        return ContextCompat.getDrawable(context, rec);
    }

    public static String getIdentifier(Context context, String name, String type) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        @SuppressLint("DiscouragedApi")
        int resId = resources.getIdentifier(name, type, context.getPackageName());
        if (resId == 0x0) {
            throw new Resources.NotFoundException(getString(context, R.string.error_in_app));
        } else return ResourceHelper.getString(context, resId);
    }

    public static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }
    public static int getIdentifier(Context context, String name) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        return resources.getIdentifier(name, null, context.getPackageName());

    }


}
