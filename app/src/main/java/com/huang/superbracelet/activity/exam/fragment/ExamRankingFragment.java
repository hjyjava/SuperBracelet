package com.huang.superbracelet.activity.exam.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.VolleyError;
import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.RVTitleAdapter;
import com.huang.superbracelet.base.BaseFragment;
import com.huang.superbracelet.bean.exam.ExamRanking;
import com.huang.superbracelet.bean.exam.RankingJindu;
import com.huang.superbracelet.bean.exam.RankingTotalScore;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.listener.RVItemClickListener;
import com.huang.superbracelet.utils.RefreshUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamRankingFragment extends BaseFragment {

    private RecyclerView title_rv;
    private ViewPager rank_vp;

    private RVTitleAdapter rvTitleAdapter;
    private List<String> mNames = new ArrayList<>();

    private ExamHttp examHttp;
    private List<RankingTotalScore> mRankingTotalScoreList = new ArrayList<>();
    private List<RankingJindu> mRankingJinduList = new ArrayList<>();
    private int mGuanShu;

    private MyTabTitleAdapter tabTitleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        examHttp = new ExamHttp(mContext);
    }

    public static ExamRankingFragment newInstance() {
        ExamRankingFragment fragment = new ExamRankingFragment();
        return fragment;
    }

    @Override
    protected void initWidget() {
        rank_vp = (ViewPager) mainLayout.findViewById(R.id.rank_vp);
        title_rv = (RecyclerView) mainLayout.findViewById(R.id.title_rv);

        mNames.add("总分排行");
        mNames.add("进度排行");

        title_rv.setLayoutManager(new StaggeredGridLayoutManager(mNames.size(), StaggeredGridLayoutManager.VERTICAL));
        rvTitleAdapter = new RVTitleAdapter(mContext, mNames);
        rvTitleAdapter.setRvItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                rank_vp.setCurrentItem(position);
            }
        });
        title_rv.setAdapter(rvTitleAdapter);

        initData();
        RefreshUtil.getIntance(mContext).register(getClass(), new RefreshUtil.UpdateReceiverListener() {
            @Override
            public void updateData() {
                initData();
            }
        });
    }

    private void initData() {
        examHttp.getRinkingList(studentId, childSubjectId, new MyVolleyListener<ExamRanking>() {
            @Override
            public void onSuccess(ExamRanking examRanking) {
                mRankingTotalScoreList = examRanking.getGraderanklist();
                mRankingJinduList = examRanking.getNumranklist();
                mGuanShu = examRanking.getGuanshu();
                buildFragment();
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    private void buildFragment() {
        TotalScoreFragment totalScoreFragment = TotalScoreFragment.newInstance(mRankingTotalScoreList);
        JinduFragment jinduFragment = JinduFragment.newInstance(mRankingJinduList, mGuanShu);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(totalScoreFragment);
        fragments.add(jinduFragment);

        if(tabTitleAdapter==null){
            if (getActivity() == null)
                return;
            tabTitleAdapter = new MyTabTitleAdapter(getActivity().getSupportFragmentManager(), fragments);
            rank_vp.setAdapter(tabTitleAdapter);
        }else {
            tabTitleAdapter.setFragments(fragments);
        }
        rank_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rvTitleAdapter.setCheckNum(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_exam_ranking, null);
    }

    public class MyTabTitleAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragments;
        FragmentManager mFm;


        public MyTabTitleAdapter(FragmentManager fm, List<Fragment> franments) {
            super(fm);
            mFragments = franments;
            this.mFm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void setFragments(List<Fragment> fragments) {
            if (mFragments != null) {
                FragmentTransaction ft = mFm.beginTransaction();
                for (Fragment fragment : mFragments) {
                    ft.remove(fragment);
                }
                ft.commitAllowingStateLoss();
                ft = null;
                mFm.executePendingTransactions();
            }
            this.mFragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RefreshUtil.getIntance(mContext).unregister(getClass());
    }
}
