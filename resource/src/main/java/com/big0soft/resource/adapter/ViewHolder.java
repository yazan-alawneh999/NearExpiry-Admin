package com.big0soft.resource.adapter;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ViewHolder<T> extends RecyclerView.ViewHolder {
    private ViewDataBinding viewDataBinding;

    public ViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    public ViewHolder(View view) {
        super(view);
    }

    public abstract void bindView(T model);
    public void bindView(){
        // do nothing
    };


}
