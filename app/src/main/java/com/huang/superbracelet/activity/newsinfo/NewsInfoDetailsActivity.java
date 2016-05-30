package com.huang.superbracelet.activity.newsinfo;

import android.os.Bundle;
import android.widget.ImageView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseActivity;

/**
 * Created by 黄家远 on 16/5/9.
 */
public class NewsInfoDetailsActivity extends BaseActivity {

    private ImageView info_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
    }

    @Override
    protected void initWedgit() {
        super.initWedgit();
        info_iv = (ImageView) findViewById(R.id.info_iv);
    }
}
