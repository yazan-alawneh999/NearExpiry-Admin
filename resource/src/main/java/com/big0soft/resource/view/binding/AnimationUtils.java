package com.big0soft.resource.view.binding;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.RawRes;
import androidx.databinding.BindingAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class AnimationUtils {
    @BindingAdapter("app:loadFromRow")
    public static void loadFromRow(LottieAnimationView lottieAnimationView, @RawRes int id) {
        if (id==0)return;
        lottieAnimationView.setAnimation(id);
        if (!lottieAnimationView.isAnimating())
            lottieAnimationView.playAnimation();
    }

    @BindingAdapter("android:_colorFilter")
    public static void setColorFilter(LottieAnimationView lottieAnimationView, @ColorRes int id) {
        lottieAnimationView.setColorFilter(id);
    }


    @BindingAdapter(
            value = {
                    "android:transitionAnimation"
            }
    )
    public static void setTransitionAnimation(View view, int visibility) {
        view.animate()
//                .translationY(view.getHeight())
                .alpha(1)
                .setDuration(1000)
                .rotationY(30)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(visibility);
                    }
                });

    }
}
