package com.huang.superbracelet.activity.home.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseFragment;


/**
 * Created by 黄家远 on 16/4/19.
 */
public class DynamicFragment extends BaseFragment {

    public static DynamicFragment newInstance() {
        DynamicFragment fragment = new DynamicFragment();
        return fragment;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.dynamic_fragment_layout,null);
    }
}
