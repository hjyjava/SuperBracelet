package com.huang.superbracelet.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.ExamGridAdapter;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.bean.exam.ExamGate;
import com.huang.superbracelet.bean.exam.GateState;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.listener.RVItemClickListener;
import com.huang.superbracelet.utils.MyToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/25.
 */
public class ExamGridActivity extends BaseActivity implements View.OnClickListener {

    private ImageView top_left_iv;
    private TextView top_title_tv;
    private RecyclerView exams_rv;

    private String maxGateNum;
    private ExamHttp examHttp;
    private int askNum;

    private ExamGridAdapter examGridAdapter;

    private boolean isChuangGuan = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_grid);
        initIntance();
        initIntent();
        initWedgit();
        updateGate();
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        maxGateNum = getIntent().getStringExtra("maxGateNum");
        askNum = getIntent().getIntExtra("jinduNum", -1);
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

    private void updateGate() {
        examHttp.getErrorList(studentId, childSubjectId, new MyVolleyListener<List<GateState>>() {
            @Override
            public void onSuccess(List<GateState> gateStates) {
                final List<ExamGate> examGateList = new ArrayList<>();
                int gateNum = Integer.valueOf(maxGateNum);
                for (int i = 0; i < gateNum; i++) {
                    ExamGate examGate = new ExamGate();
                    for (int j = 0; j < gateStates.size(); j++) {
                        if(i == gateStates.get(j).getRounds()){
                            examGate.setErrorNum(gateStates.get(j).getNumA());
                        }
                    }
                    if (i <= askNum) {
                        examGate.setAsked(true);
                    }
                    examGateList.add(examGate);
                }
                if(examGridAdapter==null){
                    examGridAdapter = new ExamGridAdapter(ExamGridActivity.this, examGateList);
                    exams_rv.setAdapter(examGridAdapter);
                    examGridAdapter.setRvItemClickListener(new RVItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            if(position>askNum){
                                MyToastUtils.showShort(ExamGridActivity.this, "本关还未解锁");
                                return;
                            }
                            isChuangGuan = (position==askNum);
                            Intent intent = new Intent(ExamGridActivity.this, AnswerExamActivity.class);
                            intent.putExtra("gatePosition", position);
                            startActivityForResult(intent, 0);
                        }
                    });
                }else{
                    examGridAdapter.update(examGateList);
                }
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left_iv:
                finish();
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if(isChuangGuan){
                        askNum++;
                    }
                    updateGate();
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    }
}
