package com.big0soft.resource.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;


import java.util.ArrayList;
import java.util.List;

public class ViewPager2Impl extends FragmentStateAdapter {

    private final List<Fragment> listFragment = new ArrayList<>();
    private FragmentManager fragmentManager;

    public ViewPager2Impl(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPager2Impl(@NonNull FragmentActivity fragmentActivity, List<Fragment> listFragment) {
        super(fragmentActivity);
        this.listFragment.addAll(listFragment);
    }

    public Fragment getTabByPosition(int position) {
        return listFragment.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listFragment.get(position);
    }

    public void addFragment(Fragment fragment) {
        if (!fragment.isAdded())
            listFragment.add(fragment);
    }

    @Override
    public int getItemCount() {
        return listFragment.size();
    }

}
