package com.big0soft.resource.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilCallbackImpl<T> extends DiffUtil.Callback {

    private final int oSize;
    private final int nSize;
    private final List<T> oList;
    private final List<T> nList;

    public DiffUtilCallbackImpl(int oSize, int nSize, List<T> oList, List<T> nList) {
        this.oSize = oSize;
        this.nSize = nSize;
        this.oList = oList;
        this.nList = nList;
    }

    public DiffUtilCallbackImpl(List<T> oList, List<T> nList) {
        this(oList.size(), nList.size(), oList, nList);
    }

    @Override
    public int getOldListSize() {
        return oSize;
    }

    @Override
    public int getNewListSize() {
        return nSize;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == newItemPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oList.get(oldItemPosition).equals(nList.get(newItemPosition));
    }
}
