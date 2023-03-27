package com.ordersdelivery.app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private  List<Fragment> fragments;
    private  List<String> titleList ;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments, List<String> titleList) {
        super(fm,behavior);
        this.fragments = fragments;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);

    }
}
