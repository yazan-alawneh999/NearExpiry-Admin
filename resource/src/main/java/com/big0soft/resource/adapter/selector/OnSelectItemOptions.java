package com.big0soft.resource.adapter.selector;

import java.util.List;

public interface OnSelectItemOptions<T> {
    List<T> getItemsSelect();

    T getSelectItem(int position);

    void unSelectItem(int position);

    void selectItem(int position);

    void selectAllItems();

    void unSelectItems();

    default boolean hasSelectItem() {
        return !getItemsSelect().isEmpty();
    }

    default int countSelectItems() {
        if (hasSelectItem()) {
            return getItemsSelect().size();
        }
        return 0;
    }


}
