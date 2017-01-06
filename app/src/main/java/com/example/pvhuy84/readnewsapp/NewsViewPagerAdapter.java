package com.example.pvhuy84.readnewsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by pvhuy84 on 1/6/2017.
 */

public class NewsViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabTitles;

    public NewsViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        tabTitles = new ArrayList<>();
    }

    public void addFragment (Fragment fragment, String tabTitle) {
        fragments.add(fragment);
        tabTitles.add(tabTitle);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
