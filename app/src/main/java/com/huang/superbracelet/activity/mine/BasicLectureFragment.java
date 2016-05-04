package com.huang.superbracelet.activity.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseFragment;


/**
 * Created by 黄家远 on 16/4/19.
 */
public class BasicLectureFragment extends BaseFragment {

    private RelativeLayout mainLayout;
    private ListView main_ylv;
    // 提示内容
    private ProgressBar progressBar;
    private RelativeLayout rl_nodata;
    private TextView none;

    public static BasicLectureFragment newInstance() {
        BasicLectureFragment fragment = new BasicLectureFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainLayout == null) {
            mainLayout = (RelativeLayout) inflater.inflate(R.layout.my_collection_essay, null);
            initWidget();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mainLayout.getParent();
            if (parent != null) {
                parent.removeView(mainLayout);
            }
        }
        return mainLayout;
    }

    protected void initWidget() {
        main_ylv = (ListView) mainLayout.findViewById(R.id.main_ylv);
        rl_nodata = (RelativeLayout) mainLayout.findViewById(R.id.rl_nodata);
        progressBar = (ProgressBar) mainLayout.findViewById(R.id.meabout_progressBar);
        none = (TextView) mainLayout.findViewById(R.id.meabout_none);

        rl_nodata.setVisibility(View.VISIBLE);
        none.setVisibility(View.VISIBLE);
        none.setText(this.getClass().getSimpleName().toString());
//        main_ylv.setAdapter(essayadapter);
//        main_ylv.setXListViewListener(this);
    }

    protected void initData() {
//        if(mMeridianInfo!=null){
//            shichen_tv.setText(CheckUtils.isNullOrEmpty(mMeridianInfo.getShichen()));
//            info_tv.setText(CheckUtils.isNullOrEmpty(mMeridianInfo.getIntroduction()));
//        }
    }

}
