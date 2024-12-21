package com.big0soft.resource.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPager extends FragmentPagerAdapter {
    private final List<ViewPagerModel> viewPagerModels;

    @Override // androidx.viewpager.widget.PagerAdapter, androidx.fragment.app.FragmentPagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
    }

    public ViewPager(FragmentManager fragmentManager, List<ViewPagerModel> viewPagerModels) {
        super(fragmentManager);
        this.viewPagerModels = viewPagerModels;
    }

    @NonNull
    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return viewPagerModels.get(i).fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagerModels.get(position).title;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return viewPagerModels.size();
    }

    public static class ViewPagerModel {
        public Fragment fragment;
        public String title;

        public ViewPagerModel(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }
    }
}