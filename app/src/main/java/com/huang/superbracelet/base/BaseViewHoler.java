package com.huang.superbracelet.base;

import android.view.View;

/**
 * Created by 黄家远 on 16/5/4.
 */
public abstract class BaseViewHoler {

    protected View mRoot;

    public BaseViewHoler(View root){
        mRoot = root;
    }

    public View getmRoot() {
        return mRoot;
    }
}
