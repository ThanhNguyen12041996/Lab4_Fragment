package com.example.minhthanh.listview_lab3_androidth;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by USER on 3/28/2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT =2;
    private String tabTitles[] = new String[] { "Tab1", "Tab2"};
    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment1.newInstance(position+1);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}