package com.big0soft.resource.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class DiffUtilItemCallbackImpl<T extends DiffUtilItemCallbackImpl.CallbackDiffId> extends DiffUtil.ItemCallback<T> {


    @Override
    public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.diffId() == newItem.diffId();
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
        return oldItem.equals(newItem);
    }


    public interface CallbackDiffId {
        int diffId();


    }
}
