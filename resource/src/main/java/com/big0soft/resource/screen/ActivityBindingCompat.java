package com.big0soft.resource.screen;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.big0soft.resource.view.SnackbarUtils;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public abstract class ActivityBindingCompat<B extends ViewDataBinding> extends BaseActivityCompat {
    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutResourceID());
    }

    /**
     * @return should return activity layout
     * @throws Resources.NotFoundException if layout id is not exits
     */
    public abstract int layoutResourceID() throws Resources.NotFoundException;


    public B getBinding() {
        return binding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
