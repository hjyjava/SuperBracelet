package com.huang.superbracelet.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseActivity;

/**
 * Created by 黄家远 on 16/4/14.
 */
public class MineMainActivity extends BaseActivity{
    private TabLayout top_tl;
    private ViewPager content_vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_fragment_layout);

        top_tl = (TabLayout) findViewById(R.id.top_tl);
        content_vp = (ViewPager) findViewById(R.id.content_vp);
    }

    @Override
    protected void initWedgit() {

    }

}
