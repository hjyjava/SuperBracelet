package com.huang.superbracelet.activity.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.AnswerExamAdapter;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.bean.exam.Exam;
import com.huang.superbracelet.bean.exam.Examlist;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.utils.MyToastUtils;
import com.huang.superbracelet.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/28.
 */
public class AnswerExamActivity extends BaseActivity implements View.OnClickListener {

    private ImageView top_left_iv;
    private TextView top_title_tv;

    private MyListView question_mly;
    private LinearLayout parse_layout;
    private TextView question_tv, right_answer_tv, parse_tv, next_gate_tv;
    private ImageView parse_iv;

    private ExamHttp examHttp;
    private int gatePosition;
    private int examPosition;
    private int answerPosition;

    private List<Exam> mExams = new ArrayList<>();
    private List<String> mAnswerList = new ArrayList<>();
    private Exam currentExam;
    private AnswerExamAdapter answerExamAdapter;
    private List<Exam> errorExams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        initIntance();
        initIntent();
        initWedgit();
        initData();
    }

    @Override
    protected void initIntent() {
        super.initIntent();
        gatePosition = getIntent().getIntExtra("gatePosition", -1);
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
        int gateNum = gatePosition++;
        top_title_tv.setText("第" + gateNum + "关");
        top_left_iv = (ImageView) findViewById(R.id.top_left_iv);
        top_left_iv.setVisibility(View.VISIBLE);
        top_left_iv.setOnClickListener(this);

        question_tv = (TextView) findViewById(R.id.question_tv);
        question_mly = (MyListView) findViewById(R.id.question_mly);
        parse_layout = (LinearLayout) findViewById(R.id.parse_layout);
        right_answer_tv = (TextView) findViewById(R.id.right_answer_tv);
        parse_tv = (TextView) findViewById(R.id.parse_tv);
        parse_iv = (ImageView) findViewById(R.id.parse_iv);

        next_gate_tv = (TextView) findViewById(R.id.next_gate_tv);
        next_gate_tv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        examHttp.getExamList(childSubjectId, gatePosition + "", new MyVolleyListener<Examlist>() {
            @Override
            public void onSuccess(Examlist examlist) {
                mExams = examlist.getExamlist();
                buildQuestion();
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    private void buildQuestion() {
        if (mExams == null || mExams.size() == 0)
            return;
        currentExam = mExams.get(examPosition);
        if (currentExam == null)
            return;
        mAnswerList = currentExam.getAnswers();
        if (mAnswerList == null || mAnswerList.size() == 0)
            return;
        //TODO 多选题待处理
        try {
            answerPosition = Integer.valueOf(mAnswerList.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAnswerList.remove(0);
        if (answerExamAdapter == null) {
            answerExamAdapter = new AnswerExamAdapter(this, mAnswerList, answerPosition);
            question_mly.setAdapter(answerExamAdapter);
        }
        question_tv.setText(currentExam.getQuestionText());
        right_answer_tv.setText(AnswerExamAdapter.flagArray[answerPosition]);
        parse_tv.setText(currentExam.getAnalysis());

        answerExamAdapter.setOnAnswerClick(new AnswerExamAdapter.OnAnswerClick() {
            @Override
            public void answer(boolean isRight) {
                if (!isRight) {
                    errorExams.add(currentExam);
                }
                answerExamAdapter.notifyDataSetChanged();
                parse_layout.setVisibility(View.VISIBLE);
                MyToastUtils.showShort(AnswerExamActivity.this, isRight ? "回答正确" : "回答错误");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left_iv:
                finish();
                break;
            case R.id.next_gate_tv:
                if (answerExamAdapter != null && answerExamAdapter.isAsked()) {
                    nextPosition();
                }
                break;
            default:
                break;
        }
    }

    private void nextPosition() {
        if (mExams != null) {
            if (examPosition < mExams.size() - 1) {
                parse_layout.setVisibility(View.GONE);
                examPosition++;
                buildQuestion();
                answerExamAdapter.changeDatas(mAnswerList, answerPosition);
            } else {
                MyToastUtils.showShort(AnswerExamActivity.this, "已答完本关所有题目,共" + errorExams.size() + "道错题");
                finish();
            }
        }
    }
}
