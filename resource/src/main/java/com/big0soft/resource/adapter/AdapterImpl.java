package com.big0soft.resource.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterImpl<T> extends Adapter<T> {
    private OnClickItemPosition onClickItemPosition;
    private final ViewHolder<T> viewHolder;

    public AdapterImpl(List<T> listItems, ViewHolder<T> viewHolder) {
        super(listItems);
        this.viewHolder = viewHolder;
    }

    public AdapterImpl<T> setOnClickItemPosition(OnClickItemPosition onClickItemPosition) {
        this.onClickItemPosition = onClickItemPosition;
        return this;
    }

    public OnClickItemPosition onClickItemPosition() {
        return onClickItemPosition;
    }

    @NonNull
    @Override
    public ViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolder;
    }
}
