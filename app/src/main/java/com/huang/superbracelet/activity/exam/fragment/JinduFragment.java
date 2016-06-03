package com.huang.superbracelet.activity.exam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.JinduAdapter;
import com.huang.superbracelet.base.BaseFragment;
import com.huang.superbracelet.bean.exam.RankingJindu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class JinduFragment extends BaseFragment {

    private ListView main_lv;
    private static final String JINDU_FLAG = "jinduFlag";
    private static final String GUAN_SHU = "guanShu";
    private List<RankingJindu> mRankingJinduList = new ArrayList<>();
    private int mGuanShu;
    private JinduAdapter jinduAdapter;

    public static JinduFragment newInstance(List<RankingJindu> rankingJinduList,int guanshu) {
        JinduFragment fragment = new JinduFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JINDU_FLAG, (Serializable) rankingJinduList);
        bundle.putInt(GUAN_SHU,guanshu);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRankingJinduList = (List<RankingJindu>) getArguments().getSerializable(JINDU_FLAG);
        mGuanShu = getArguments().getInt(GUAN_SHU);
    }

    @Override
    protected void initWidget() {
        main_lv = (ListView) mainLayout.findViewById(R.id.main_lv);
        initData();
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_listview, null);
    }

    private void initData(){
        jinduAdapter = new JinduAdapter(mContext,mRankingJinduList,mGuanShu);
        main_lv.setAdapter(jinduAdapter);
    }
}
