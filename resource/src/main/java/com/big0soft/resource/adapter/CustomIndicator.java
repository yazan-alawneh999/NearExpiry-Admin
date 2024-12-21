package com.big0soft.resource.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.big0soft.resource.adapter.animation.TransformerImpl;
import com.big0soft.resource.helper.TAGs;

import java.util.ArrayList;
import java.util.List;

public class CustomIndicator {
    public ViewPager2 viewPager2;
    public final List<String> pictures = new ArrayList<>();
    public Context context;
    public int indicatorImageInActive;
    public int indicatorImageActive;
    public int width;
    public int height;
    public ViewGroup indicatorContainer;

    public CustomIndicator(ViewPager2 viewPager2, Context context, int indicatorImageInActive
            , int indicatorImageActive, int width, int height
            , ViewGroup indicatorContainer) {
        this.viewPager2 = viewPager2;
        this.context = context;
        this.indicatorImageInActive = indicatorImageInActive;
        this.indicatorImageActive = indicatorImageActive;
        this.width = width;
        this.height = height;
        this.indicatorContainer = indicatorContainer;
    }

    public void sliderImage() {
        Log.d(TAGs.TAG(getClass()), "sliderImage:size " + pictures.size());
        setupSliderIndicators(pictures.size());
//        transitionSliderImage();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
        viewPager2.setPageTransformer(new TransformerImpl());
    }

    public void setupSliderIndicators(int count) {
        Log.d(TAGs.TAG(getClass()), "sliderImage:count " + count);
        ImageView[] indicator = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(8, 0, 8, 0);
        indicatorContainer.removeAllViews();
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(context);
            indicator[i].setImageDrawable(ContextCompat.getDrawable(context, indicatorImageInActive));
            indicator[i].setLayoutParams(layoutParams);
            indicatorContainer.addView(indicator[i]);
        }
        setCurrentSliderIndicator(0);
    }

    public void setCurrentSliderIndicator(int position) {
        int childCount = indicatorContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) indicatorContainer.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(context, indicatorImageActive));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(context, indicatorImageInActive));
            }
        }
    }
}
