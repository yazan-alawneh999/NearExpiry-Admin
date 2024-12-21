package com.big0soft.resource.view;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WrapContentHorizontalManager extends LinearLayoutManager {

    public WrapContentHorizontalManager(Context context) {
        super(context,RecyclerView.HORIZONTAL, true);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException  e) {
            e.printStackTrace();
        }
    }
}
