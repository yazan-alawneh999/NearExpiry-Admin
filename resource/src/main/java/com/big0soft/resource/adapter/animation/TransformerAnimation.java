package com.big0soft.resource.adapter.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

public class TransformerAnimation implements OnTransformer {
    private static final Camera CAMERA_OFFSET = new Camera();
    private static final Matrix MATRIX_OFFSET = new Matrix();
    private static final float[] TEMP_FLOAT_OFFSET = new float[2];

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float abs = (f < 0.0f ? 30.0f : -30.0f) * Math.abs(f);
        view.setTranslationX(getOffsetX(abs, view.getWidth(), view.getHeight()));
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(0.0f);
        view.setRotationY(abs);
    }

    private float getOffsetX(float f, int i, int i2) {
        MATRIX_OFFSET.reset();
        CAMERA_OFFSET.save();
        CAMERA_OFFSET.rotateY(Math.abs(f));
        CAMERA_OFFSET.getMatrix(MATRIX_OFFSET);
        CAMERA_OFFSET.restore();
        MATRIX_OFFSET.preTranslate(((float) (-i)) * 0.5f, ((float) (-i2)) * 0.5f);
        float f2 = (float) i;
        float f3 = (float) i2;
        MATRIX_OFFSET.postTranslate(f2 * 0.5f, 0.5f * f3);
        float[] fArr = TEMP_FLOAT_OFFSET;
        fArr[0] = f2;
        fArr[1] = f3;
        MATRIX_OFFSET.mapPoints(fArr);
        return (f2 - TEMP_FLOAT_OFFSET[0]) * (f > 0.0f ? 1.0f : -1.0f);
    }
}
