package com.zumepizza.interview.bindings;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zumepizza.interview.R;
import com.zumepizza.interview.ui.main.ItemFragment;

public class FragmentSelectAdapter extends FragmentPagerAdapter {

    private Context context;
    private int numOfTabs;

    public FragmentSelectAdapter(FragmentManager fm, Context context, int numOfTabs) {
        super(fm);
        this.context = context;
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemFragment.newInstance(1,position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.chef_special);
            case 1:
                return context.getString(R.string.classic);
            case 2:
                return context.getString(R.string.signature);
            case 3:
                return context.getString(R.string.vegetarian);
            default:
                return null;
        }
    }
}
