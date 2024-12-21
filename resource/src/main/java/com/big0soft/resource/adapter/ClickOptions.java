package com.big0soft.resource.adapter;

public interface ClickOptions<T> extends OnClickItem<T>, OnLongClickItem<T> {
    void onSelectItem(T model,int position);
}
