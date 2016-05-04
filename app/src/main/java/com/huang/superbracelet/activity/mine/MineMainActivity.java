package com.huang.superbracelet.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/4/14.
 */
public class MineMainActivity extends BaseActivity{
    private TabLayout top_tl;
    private ViewPager content_vp;

    private BasicDiseaseFragment basicDiseaseFragment;
    private BasicLectureFragment basicLectureFragment;
    private BasicCaseFragment basicCaseFragment;

    private List<Fragment> mFranments = new ArrayList<>();
    private List<String> mNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_fragment_layout);

        top_tl = (TabLayout) findViewById(R.id.top_tl);
        content_vp = (ViewPager) findViewById(R.id.content_vp);

        mNames.add("疾病");
        mNames.add("讲座");
        mNames.add("病例");
        basicDiseaseFragment = BasicDiseaseFragment.newInstance();
        basicLectureFragment = BasicLectureFragment.newInstance();
        basicCaseFragment = BasicCaseFragment.newInstance();
        mFranments.add(basicDiseaseFragment);
        mFranments.add(basicLectureFragment);
        mFranments.add(basicCaseFragment);

        BasicTitleAdapter adapter = new BasicTitleAdapter(getSupportFragmentManager(),mFranments,mNames);
        content_vp.setAdapter(adapter);
        top_tl.setupWithViewPager(content_vp);
    }

    class BasicTitleAdapter extends FragmentPagerAdapter {
        private List<String> mNames;
        private List<Fragment> mFranments;

        public BasicTitleAdapter(FragmentManager fm, List<Fragment> franments, List<String> names) {
            super(fm);
            mFranments = franments;
            mNames = names;
        }

        @Override
        public Fragment getItem(int position) {
            return mFranments.get(position);
        }

        @Override
        public int getCount() {
            return mFranments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mNames.get(position);
        }
    }
}
