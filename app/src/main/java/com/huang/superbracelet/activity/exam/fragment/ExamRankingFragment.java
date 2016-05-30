package com.huang.superbracelet.activity.exam.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.TabTitleAdapter;
import com.huang.superbracelet.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamRankingFragment extends BaseFragment {

    private TabLayout rank_tl;
    private ViewPager rank_vp;

    private List<Fragment> mFranments = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    public static ExamRankingFragment newInstance() {
        ExamRankingFragment fragment = new ExamRankingFragment();
        return fragment;
    }

    @Override
    protected void initWidget() {
        rank_tl = (TabLayout)mainLayout.findViewById(R.id.rank_tl);
        rank_vp = (ViewPager) mainLayout.findViewById(R.id.rank_vp);

        mNames.add("总分排行");
        mNames.add("进度排行");
        TotalScoreFragment totalScoreFragment = TotalScoreFragment.newInstance();
        JinduFragment jinduFragment = JinduFragment.newInstance();
        mFranments.add(totalScoreFragment);
        mFranments.add(jinduFragment);

        TabTitleAdapter adapter = new TabTitleAdapter(getChildFragmentManager(), mFranments, mNames);
        rank_vp.setAdapter(adapter);
        rank_tl.setupWithViewPager(rank_vp);
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_exam_ranking, null);
    }
}
