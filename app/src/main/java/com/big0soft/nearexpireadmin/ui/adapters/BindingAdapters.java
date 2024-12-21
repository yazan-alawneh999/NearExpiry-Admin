package com.big0soft.nearexpireadmin.ui.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.big0soft.nearexpireadmin.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.makeramen.roundedimageview.RoundedImageView;

public class BindingAdapters {
    @BindingAdapter("app:cardBackgroundColor")
    public static void cardBackground(MaterialCardView cardView, int colorResId) {
        Context context = cardView.getContext();
        try {
            // Retrieve the color as a ColorStateList from the resource
            ColorStateList colorStateList = ColorStateList.valueOf(context.getColor(colorResId));
            // Set the card background color
            cardView.setCardBackgroundColor(colorStateList);
        } catch (Exception e) {
            // Handle any errors gracefully (e.g., invalid color resource ID)
            cardView.setCardBackgroundColor(ColorStateList.valueOf(context.getColor(R.color.md_theme_primary)));
        }
    }

    @BindingAdapter("android:text")
    public static void setTitleText(MaterialTextView textView, int titleResId) {
        Context context = textView.getContext();
        if (titleResId == 0) {
            textView.setText("");
            return;
        }

        textView.setText(context.getString(titleResId));
    }

    @BindingAdapter("android:src")
    public static void setIconImage(RoundedImageView imageView, int iconResId) {
        if (iconResId == 0) return;
        Context context = imageView.getContext();
        Drawable drawable = ContextCompat.getDrawable(context, iconResId);
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("android:visibility")
    public static void setImageVisibility(RoundedImageView imageView, boolean isNullOrEmpty) {
        imageView.setVisibility(isNullOrEmpty ? View.GONE : View.VISIBLE);
    }


}
