package com.huang.superbracelet.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.ExamGridAdapter;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.bean.exam.ExamGate;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.listener.RVItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/25.
 */
public class ExamGridActivity extends BaseActivity implements View.OnClickListener{

    private ImageView top_left_iv;
    private TextView top_title_tv;
    private RecyclerView exams_rv;

    private String maxGateNum;
    private String jinduNum;
    private ExamHttp examHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_grid);
        initIntance();
        initIntent();
        initWedgit();
        initData();
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        maxGateNum = getIntent().getStringExtra("maxGateNum");
        jinduNum = getIntent().getStringExtra("jinduNum");
    }

    @Override
    protected void initIntance() {
        super.initIntance();
        examHttp = new ExamHttp(this);
    }

    @Override
    protected void initWedgit() {
        super.initWedgit();
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        top_title_tv.setText("选择关卡");
        top_left_iv = (ImageView) findViewById(R.id.top_left_iv);
        top_left_iv.setVisibility(View.VISIBLE);
        top_left_iv.setOnClickListener(this);

        exams_rv = (RecyclerView) findViewById(R.id.exams_rv);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        exams_rv.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        super.initData();
        final List<ExamGate> examGateList = new ArrayList<>();
        int gateNum = Integer.valueOf(maxGateNum);
        int askNum = Integer.valueOf(jinduNum);
        for (int i = 0; i < gateNum; i++) {
            ExamGate examGate = new ExamGate();
            if (i <= askNum) {
                examGate.setAsked(true);
                examGate.setErrorNum(i * 2 + 3);
            }
            examGate.setUserid(studentId);
            examGate.setGrade(23 + "");
            examGate.setZongfen(100 + "");
            examGate.setCid(childSubjectId);
            examGateList.add(examGate);
        }
        ExamGridAdapter examGridAdapter = new ExamGridAdapter(this, examGateList);
        examGridAdapter.setRvItemClickListener(new RVItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ExamGate examGate = examGateList.get(position);
                Intent intent = new Intent(ExamGridActivity.this,AnswerExamActivity.class);
                intent.putExtra("gatePosition",position);
                startActivity(intent);
            }
        });
        exams_rv.setAdapter(examGridAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_left_iv:
                finish();
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
