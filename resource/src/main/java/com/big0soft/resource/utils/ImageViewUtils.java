package com.big0soft.resource.utils;


import static androidx.databinding.adapters.ViewBindingAdapter.setBackground;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.big0soft.resource.helper.TAGs;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageViewUtils {
    private static final String TAG = "BindingAdapters";

    @BindingAdapter({"app:src"})
    public static void setSrc(ImageView view, int src) {
        if (src==0)return;
        view.setImageResource(src);
//        view.setImageDrawable(src);
    }
    @BindingAdapter("android:_drawable")
    public static void setDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:goneIfNoSrc")
    public static void goneIfNoSrc(ImageView imageView, boolean ignore) {
        Log.d(TAGs.TAG, "goneIfNoSrc: " + imageView.getDrawable());
        ViewUtils.goneVisibility(imageView, imageView.getDrawable() != null);
    }

    @SuppressLint("RestrictedApi")
    @BindingAdapter("android:_circleColor")
    public static void setImageBackground(ImageView imageView, String  color) {
        if (StrUtils.isEmpty(color)){
            return;
        }
        Drawable background = createCircleDrawable(imageView.getContext(),color);
        setBackground(imageView, background);
    }

    public static GradientDrawable createCircleDrawable(@NonNull Context context,@NonNull String color) {
        int[] colors = new int[]{Color.parseColor(color), ContextCompat.getColor(context, android.R.color.transparent)};
        // Create a circular shape
        GradientDrawable circle = new GradientDrawable();
        circle.setShape(GradientDrawable.OVAL); // Make it circular
        circle.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        circle.setColors(colors);
        return circle;
    }

    static class PicassoCallbackImpl implements Callback {

        private final ImageView imageView;

        PicassoCallbackImpl(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onSuccess() {
            Log.i(TAGs.TAG(getClass()), "onSuccess: ");
        }

        @Override
        public void onError(Exception e) {
//            Log.e(TAGs.TAG(getClass()), "onError: ",e );
        }
    }

    @BindingAdapter("android:_imageColor")
    public static void setImageColor(ImageView imageView, int color) {
        if (color == 0) return;
        imageView.setBackgroundColor(color);
    }

    @BindingAdapter(value = {"android:imageURL"})
    public static void setImageURL(ImageView imageURL, String URL) {
        try {
            setImageCallback(imageURL, URL, new PicassoCallbackImpl(imageURL));
        } catch (Exception e) {
            Log.e(TAGs.TAG, "setImageURL: ", e);
        }

    }

    private static void setImageCallback(ImageView imageURL, String URL, Callback callback) throws Exception {
//        Log.i(TAGs.TAG, "setImageCallback: "+URL);
//        Picasso.get().setLoggingEnabled(BuildConfig.DEBUG);
        // FIXME: 9/29/2024 should loaded in first application open
        Picasso.get().load(URL)
                .fit()
                .noFade().into(imageURL, callback);
    }

}
