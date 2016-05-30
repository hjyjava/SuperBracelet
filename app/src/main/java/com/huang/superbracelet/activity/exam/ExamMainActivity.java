package com.huang.superbracelet.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.bean.ChildSubject;
import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.exam.fragment.ExamHomeFragment;
import com.huang.superbracelet.activity.exam.fragment.ExamMineFragment;
import com.huang.superbracelet.activity.exam.fragment.ExamRankingFragment;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.db.exam.ExamDb;
import com.huang.superbracelet.http.everyhttp.ExamHttp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamMainActivity extends BaseActivity implements View.OnClickListener{

    private ImageView top_right_iv;
    private TextView top_title_tv;

    private FrameLayout channel1,channel2,channel3;
    private ImageView img01,img02,img03;
    private TextView textView1,textView2,textView3;
    private int[] unSelectedIcon = {R.drawable.icon01_nomal,R.drawable.icon02_nomal,R.drawable.icon03_nomal};
    private int[] selectedIcon = {R.drawable.icon01_pressed,R.drawable.icon02_pressed,R.drawable.icon03_pressed};
    private int selectedTextColor = 0xff29b8ee;
    private int unSelectedTextColor = 0xff89929d;
    private List<ViewHolder> viewHolderList = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();
    private String[] names = {"首页","排行榜","我"};
    private int currentFragment;
    private ExamHttp examHttp;
    private ExamDb examDb;
    private String childSubjectName;
    private String maxGateNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaoshi_home);
        initIntance();
        initIntent();
        initWedgit();
        initData();
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        childSubjectName = getIntent().getStringExtra("childSubjectName");
        maxGateNum = getIntent().getStringExtra("maxGateNum");
    }

    @Override
    protected void initIntance() {
        super.initIntance();
        examHttp = new ExamHttp(this);
        examDb = new ExamDb(this);
    }

    @Override
    protected void initWedgit() {
        super.initWedgit();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        top_title_tv.setText("首页");
        top_right_iv = (ImageView) findViewById(R.id.top_right_iv);
        top_right_iv.setVisibility(View.VISIBLE);
        top_right_iv.setImageResource(R.drawable.menu);
        top_right_iv.setOnClickListener(this);

        channel1 = (FrameLayout) findViewById(R.id.channel1);
        channel1.setOnClickListener(this);
        channel2 = (FrameLayout) findViewById(R.id.channel2);
        channel2.setOnClickListener(this);
        channel3 = (FrameLayout) findViewById(R.id.channel3);
        channel3.setOnClickListener(this);
        img01 = (ImageView) findViewById(R.id.img01);
        img02 = (ImageView) findViewById(R.id.img02);
        img03 = (ImageView) findViewById(R.id.img03);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        ViewHolder viewHolder1 = new ViewHolder(channel1,img01,textView1);
        ViewHolder viewHolder2 = new ViewHolder(channel2,img02,textView2);
        ViewHolder viewHolder3 = new ViewHolder(channel3,img03,textView3);
        viewHolderList.add(viewHolder1);
        viewHolderList.add(viewHolder2);
        viewHolderList.add(viewHolder3);
        initDefaultFragment();
    }

    private void initDefaultFragment() {
        if(TextUtils.isEmpty(childSubjectName)||TextUtils.isEmpty(maxGateNum)){
            ChildSubject childSubject = examDb.queryChildSubject(childSubjectId);
            childSubjectName = childSubject.getName();
            maxGateNum = childSubject.getRounds();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ExamHomeFragment examHomeFragment = ExamHomeFragment.newInstance(childSubjectName,maxGateNum);
        ExamRankingFragment examRankingFragment = ExamRankingFragment.newInstance();
        ExamMineFragment examMineFragment = ExamMineFragment.newInstance();
        fragments.add(examHomeFragment);
        fragments.add(examRankingFragment);
        fragments.add(examMineFragment);
        currentFragment = 0;
        transaction.replace(R.id.main_fragment_layout, examHomeFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_right_iv:
                startActivity(new Intent(ExamMainActivity.this, SubjectActivity.class));
                break;
            case R.id.channel1:
                changeFragment(0);
                break;
            case R.id.channel2:
                changeFragment(1);
                break;
            case R.id.channel3:
                changeFragment(2);
                break;
            default:
                break;
        }
    }

    private void changeFragment(int index) {
        if (index == currentFragment) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        currentFragment = index;
        top_title_tv.setText(names[currentFragment]);
        transaction.replace(R.id.main_fragment_layout, fragments.get(currentFragment));
        transaction.commit();
        for (int i = 0; i < viewHolderList.size(); i++) {
            ViewHolder viewHolder = viewHolderList.get(i);
            if(i==currentFragment){
                viewHolder.imageView.setImageResource(selectedIcon[i]);
                viewHolder.textView.setTextColor(selectedTextColor);
            }else{
                viewHolder.imageView.setImageResource(unSelectedIcon[i]);
                viewHolder.textView.setTextColor(unSelectedTextColor);
            }
        }
    }

    class ViewHolder{
        FrameLayout frameLayout;
        ImageView imageView;
        TextView textView;

        public ViewHolder(FrameLayout frameLayout, ImageView imageView, TextView textView) {
            this.frameLayout = frameLayout;
            this.imageView = imageView;
            this.textView = textView;
        }
    }
}
