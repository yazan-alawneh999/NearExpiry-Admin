package com.big0soft.resource;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ListAdapter;

import com.big0soft.resource.adapter.Adapter;

public class BindingUtils {


    public static <T extends ViewDataBinding> T fragmentBinding(@NonNull LayoutInflater inflater, @LayoutRes int resource, @Nullable ViewGroup container) {
        return DataBindingUtil.inflate(inflater, resource, container, false);
    }

    public static <T extends ViewDataBinding> T activityBinding(Activity activity, @LayoutRes int resource) {
        return DataBindingUtil.setContentView(activity, resource);
    }
    public static <T extends ViewDataBinding> T adapterBinding(Adapter<?> adapter ,@LayoutRes int resource,@Nullable ViewGroup parent){
        return DataBindingUtil.inflate(adapter.inflater(parent),resource,parent,false);
    }


}
