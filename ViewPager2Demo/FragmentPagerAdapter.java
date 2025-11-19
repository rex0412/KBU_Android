package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 4;
    private final int displayMode;

    public FragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, int mode) {
        super(fragmentActivity);
        this.displayMode = mode;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return MyFragment.newInstance(position + 1, displayMode);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}