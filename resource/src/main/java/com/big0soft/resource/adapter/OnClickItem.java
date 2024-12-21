package com.big0soft.resource.adapter;

/**
 * @see OnClickItemPosition
 * @deprecated because now we can get item from position
 * use {@link Adapter#getItem(int)}  }
 */
@Deprecated
public interface OnClickItem<T> {
    void clickItem(int position, T t);
}
