package com.tony.timemanager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tony.timemanager.base.BaseFragment;

/**
 * Created by tony on 8/16/16.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private BaseFragment[] mBaseFragments;

    public FragmentPagerAdapter(FragmentManager fm, BaseFragment... baseFragments) {
        super(fm);
        mBaseFragments = baseFragments;
    }

    @Override
    public int getCount() {
        return mBaseFragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mBaseFragments[position];
    }
}
