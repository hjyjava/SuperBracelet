package com.huang.superbracelet.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by 黄家远 on 16/4/14.
 */
public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        } else {
            mContext = MyApplication.getmContext();
        }
    }

    protected void initWidget(){

    }
}
