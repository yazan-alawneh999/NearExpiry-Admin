package com.big0soft.nearexpireadmin.data.models;

import com.big0soft.resource.adapter.DiffUtilItemCallbackImpl;

public class HomeCategoryModel implements DiffUtilItemCallbackImpl.CallbackDiffId {
    private int id;
    private int icon;
    private int color;
    private int title;

    public HomeCategoryModel(int id, int icon, int color, int title) {
        this.id = id;
        this.icon = icon;
        this.color = color;
        this.title = title;
    }

    public HomeCategoryModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @Override
    public int diffId() {
        return id;
    }
}
