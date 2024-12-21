package com.big0soft.resource.adapter.selector;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.big0soft.resource.adapter.Adapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class AdapterWithSelectItems<T extends Select> extends Adapter<T> implements OnSelectItemOptions<T> {

    @NonNull
    private final List<T> selectItems;
    @NonNull
    private final OnSelectItemListener onSelectItemListener;

    public AdapterWithSelectItems(@NonNull List<T> listItems, @NonNull List<T> selectItems, @NonNull OnSelectItemListener onSelectItemListener) {
        super(listItems);
        this.selectItems = selectItems;
        this.onSelectItemListener = onSelectItemListener;
    }


    @Override
    public List<T> getItemsSelect() {
        return selectItems;
    }

    @Override
    public void unSelectItem(int position) {
        T t = listItems.get(position);
        t.select(false);
        selectItems.remove(t);
        notifyItemChanged(position);
        if (!hasSelectItem()) {
            onSelectItemListener.unSelectItemMode();
        }
    }

    @Override
    public T getSelectItem(int position) {
        return selectItems.get(position);
    }

    @Override
    public void selectItem(int position) {
        T t = listItems.get(position);
        t.select(true);
        selectItems.add(t);
        notifyItemChanged(position);
        if (hasSelectItem()) {
            onSelectItemListener.selectItemMode();
        }
    }

    @Override
    @MainThread
    public void selectAllItems() {
        listItems = listItems.stream().peek(t -> t.select(true))
                .collect(Collectors.toList());
        notifyItemRangeChanged(0, getItemCount());
        selectItems.clear();
        selectItems.addAll(listItems);
        onSelectItemListener.selectItemMode();
    }

    @Override
    public void unSelectItems() {
        listItems = listItems.stream().peek(t -> t.select(false))
                .collect(Collectors.toList());

        notifyItemRangeChanged(0, getItemCount());
        selectItems.clear();
        onSelectItemListener.unSelectItemMode();
    }

}
