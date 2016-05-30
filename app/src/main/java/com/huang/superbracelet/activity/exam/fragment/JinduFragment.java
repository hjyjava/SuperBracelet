package com.huang.superbracelet.activity.exam.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseFragment;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class JinduFragment extends BaseFragment {

    public static JinduFragment newInstance() {
        JinduFragment fragment = new JinduFragment();
        return fragment;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_jindu, null);
    }
}
