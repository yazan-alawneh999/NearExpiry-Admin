package com.big0soft.resource.adapter.animation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public interface OnTransformer extends ViewPager.PageTransformer, ViewPager2.PageTransformer{
    @Override
    void transformPage(@NonNull View page, float position);
}
