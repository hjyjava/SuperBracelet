package com.huang.superbracelet.activity.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.ScoreAdapter;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.bean.exam.ExamGrade;
import com.huang.superbracelet.db.exam.ExamDb;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/31.
 */
public class ScoreActivity extends BaseActivity implements View.OnClickListener{

    private ImageView top_left_iv;
    private TextView top_title_tv;
    private ListView main_lv;
    private ExamHttp examHttp;
    private ExamDb examDb;

    private ScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initIntance();
        initWedgit();
        initData();
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
        top_title_tv.setText("分数列表");
        top_left_iv = (ImageView) findViewById(R.id.top_left_iv);
        top_left_iv.setVisibility(View.VISIBLE);
        top_left_iv.setOnClickListener(this);

        main_lv = (ListView) findViewById(R.id.main_lv);
    }

    @Override
    protected void initData() {
        examHttp.getGradeList(studentId, childSubjectId, new MyVolleyListener<List<ExamGrade>>() {
            @Override
            public void onSuccess(List<ExamGrade> examGrades) {
                scoreAdapter = new ScoreAdapter(ScoreActivity.this,examGrades);
                main_lv.setAdapter(scoreAdapter);
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
}
