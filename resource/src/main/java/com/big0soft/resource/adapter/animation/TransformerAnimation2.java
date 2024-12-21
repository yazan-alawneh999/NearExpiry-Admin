package com.big0soft.resource.adapter.animation;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static java.lang.Math.abs;

import android.view.View;

import androidx.annotation.NonNull;

public class TransformerAnimation2 implements OnTransformer {


    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(@NonNull View page, float position) {
        page.setTranslationX(-position * page.getWidth());
        if (abs(position) < 0.5) {
            page.setVisibility(VISIBLE);
            page.setScaleY(1 - abs(position));
            page.setScaleX(1 - abs(position));
        } else if (abs(position) > 0.5) {
            page.setVisibility(GONE);
        }
        if (position < -1) {
            page.setAlpha(0);
        } else {
            float v = abs(position) * abs(position) * abs(position)
                    * abs(position) * abs(position) * abs(position) * abs(position);
            if (position <= 0) {
                page.setAlpha(1);
                page.setRotation(3600_0 * v);
            } else if (position <= 1) {
                page.setAlpha(1);
                page.setRotation(-3600_0 * v);
            } else {
                page.setAlpha(0);
            }
        }


    }


}
