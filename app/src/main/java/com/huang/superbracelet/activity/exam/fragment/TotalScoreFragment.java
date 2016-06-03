package com.huang.superbracelet.activity.exam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.TotalScoreAdapter;
import com.huang.superbracelet.base.BaseFragment;
import com.huang.superbracelet.bean.exam.RankingTotalScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class TotalScoreFragment extends BaseFragment {

    private ListView main_lv;
    private static final String TOTAL_SCORE_FLAG = "totalScoreFlag";
    private List<RankingTotalScore> mRankingTotalScoreList = new ArrayList<>();
    private TotalScoreAdapter totalScoreAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRankingTotalScoreList = (List<RankingTotalScore>) getArguments().getSerializable(TOTAL_SCORE_FLAG);
    }

    public static TotalScoreFragment newInstance(List<RankingTotalScore> rankingTotalScoreList) {
        TotalScoreFragment fragment = new TotalScoreFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TOTAL_SCORE_FLAG, (Serializable) rankingTotalScoreList);
        fragment.setArguments(bundle);
        return fragment;
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
        totalScoreAdapter = new TotalScoreAdapter(mContext,mRankingTotalScoreList);
        main_lv.setAdapter(totalScoreAdapter);
    }
}
