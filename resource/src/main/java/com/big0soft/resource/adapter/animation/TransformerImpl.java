package com.big0soft.resource.adapter.animation;

import android.view.View;

import androidx.annotation.NonNull;

public class TransformerImpl implements OnTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        float r = 1 - Math.abs(position);
        page.setScaleY(0.85f + r * 0.15f);
    }
}
