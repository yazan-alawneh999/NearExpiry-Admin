package com.big0soft.resource.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class Adapter<T> extends RecyclerView.Adapter<ViewHolder<T>> implements IAdapter<T> {
    protected LayoutInflater inflater;
    protected List<T> listItems;

    public Adapter(List<T> listItems) {
        this.listItems = listItems;
    }


    public LayoutInflater inflater(ViewGroup parent) {
//        if (inflater == null) {
//            return LayoutInflater.from(parent.getContext());
//        }
//        return inflater;
        return LayoutInflater.from(parent.getContext());
    }


    @Override
    public int getItemCount() {
        if (listItems.isEmpty()) {
            return 0;
        }
        return listItems.size();
    }

    public void onBindViewHolder(@NonNull ViewHolder<T> holder, int position, T t) {
        holder.bindView(t);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<T> holder, int position) {
//        if (!hasItems()) return;
        onBindViewHolder(holder, position, listItems.get(position));
    }

    public T getItem(int position) {
        return listItems.get(position);
    }

    public boolean hasItems() {
        return listItems != null && !listItems.isEmpty();
    }

    public void addItem(T t) {
        int oldSize = oldSize();
        listItems.add(t);
//        notifyItemInserted();
        notifyItemRangeInserted(oldSize, getItemCount());
    }

    //    public void addItems(List<T> t) {
//        listItems.addAll(t);
//        notifyItemRangeInserted(listItems.size(), t.size());
//    }
    public void addItems(List<T> items) {
        int startPosition = listItems.size();
        listItems.addAll(items);
        notifyItemRangeInserted(startPosition, items.size());
    }


    public void cleanItems() {
        if (listItems.isEmpty()) return;
        notifyItemRangeRemoved(0, listItems.size());
        listItems.clear();
    }

    public void removeItem(T t) {
        notifyDataSetChanged();
        listItems.remove(t);
    }

    public void removeItem(int index) {
        notifyItemRemoved(index);
        listItems.remove(index);
    }


    @Override
    public List<T> getAllItems() {
        return listItems;
    }


    /**
     * Updates the item at the specified position with the given model.
     *
     * @param model    The new model to update the item with.
     * @param position The position of the item to be updated.
     * @return {@code true} if the item is successfully updated, {@code false} if the position is invalid.
     */
    public boolean updateItem(T model, int position) {
        if (position < 0 || position >= listItems.size()) {
            return false; // Return false if the position is invalid
        }
        listItems.set(position, model);
        notifyItemChanged(position);
        return true; // Return true if the item is successfully updated
    }


    public void updateItems(List<T> items) {
//        listItems.addAll(items);
        notifyItemRangeChanged(0, items.size());
    }


    public final int oldSize() {
        return getItemCount();
    }

}
