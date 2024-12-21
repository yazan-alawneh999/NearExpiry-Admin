package com.big0soft.resource.adapter.animation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

public class TransformerAnimation3 implements OnTransformer {

    private final int offscreenPageLimit;
//    private static final float DEFAULT_TRANSLATION_X = 0.0F;
//    private static final float DEFAULT_TRANSLATION_FACTOR = 1.2F;
//    private static final float SCALE_FACTOR = 0.14F;
//    private static final float DEFAULT_SCALE = 1.0F;
//    private static final float ALPHA_FACTOR = 0.3F;
//    private static final float DEFAULT_ALPHA = 1.0F;

    public TransformerAnimation3(int offscreenPageLimit) {
        this.offscreenPageLimit = offscreenPageLimit;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(@NonNull View page, float position) {
        ViewCompat.setElevation(page, -Math.abs(position));
        float scaleFactor = -0.14F * position + 1.0F;
        float alphaFactor = -0.3F * position + 1.0F;
        if (position <= 0.0F) {
            page.setTranslationX(0.0F);
            page.setScaleX(1.0F);
            page.setScaleY(1.0F);
            page.setAlpha(1.0F + position);
        } else if (position <= (float)(this.offscreenPageLimit - 1)) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-((float)page.getWidth() / 1.2F) * position);
            page.setAlpha(alphaFactor);
        } else {
            page.setTranslationX(0.0F);
            page.setScaleX(1.0F);
            page.setScaleY(1.0F);
            page.setAlpha(1.0F);
        }
    }
}
