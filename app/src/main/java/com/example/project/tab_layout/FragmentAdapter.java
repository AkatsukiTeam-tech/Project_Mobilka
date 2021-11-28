package com.example.project.tab_layout;

import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 1:
                return new AllFragment();

            case 2:
                return new AnnounceFragment();

            default:
                return new TodayFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}