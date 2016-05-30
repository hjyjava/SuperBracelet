package com.huang.superbracelet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/27.
 */
public class TabTitleAdapter extends FragmentPagerAdapter{
    private List<String> mNames;
    private List<Fragment> mFranments;

    public TabTitleAdapter(FragmentManager fm, List<Fragment> franments, List<String> names) {
        super(fm);
        mFranments = franments;
        mNames = names;
    }

    @Override
    public Fragment getItem(int position) {
        return mFranments.get(position);
    }

    @Override
    public int getCount() {
        return mFranments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNames.get(position);
    }
}
