package com.big0soft.resource.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public interface IAdapter<T> {
    void onBindViewHolder(@NonNull ViewHolder<T> holder, int position, T t);

    T getItem(int position);

    boolean hasItems();

    LayoutInflater inflater(ViewGroup parent);

    void addItem(T t);

    void addItems(List<T> t);

    void cleanItems();

    void removeItem(T t);

    void removeItem(int index);

    List<T> getAllItems();

    boolean updateItem(T model, int position);

    void updateItems(List<T> items);

    int oldSize();
}
