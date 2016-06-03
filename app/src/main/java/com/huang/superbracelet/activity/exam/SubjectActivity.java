package com.huang.superbracelet.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.bean.ChildSubject;
import com.huang.bean.ParentSubject;
import com.huang.superbracelet.R;
import com.huang.superbracelet.adapter.exam.ParentSubjectAdapter;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.db.exam.ExamDb;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.listener.OnClickChildSubjectListener;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class SubjectActivity extends BaseActivity implements View.OnClickListener {

    private ImageView top_left_iv;
    private TextView top_title_tv;
    private ListView main_lv;
    private ExamHttp examHttp;
    private ExamDb examDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
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
        top_title_tv.setText("科目列表");
        top_left_iv = (ImageView) findViewById(R.id.top_left_iv);
        top_left_iv.setVisibility(View.VISIBLE);
        top_left_iv.setOnClickListener(this);

        main_lv = (ListView) findViewById(R.id.main_lv);
    }

    @Override
    protected void initData() {
        super.initData();
        if (checkNetwork())
            examHttp.getSubjectList(new MyVolleyListener<List<ParentSubject>>() {
                @Override
                public void onSuccess(List<ParentSubject> parentSubjects) {
                    buildParentSubject(parentSubjects);
                }

                @Override
                public void onError(VolleyError volleyError) {

                }
            });
    }

    private void buildParentSubject(List<ParentSubject> parentSubjects) {
        ParentSubjectAdapter parentSubjectAdapter = new ParentSubjectAdapter(this, parentSubjects, new OnClickChildSubjectListener() {
            @Override
            public void click(ChildSubject childSubject) {
//                Intent intent = new Intent(SubjectActivity.this,ExamGridActivity.class);
                Intent intent = new Intent(SubjectActivity.this, ExamMainActivity.class);
                intent.putExtra("childSubjectName", childSubject.getName());
                intent.putExtra("maxGateNum", childSubject.getRounds());
                saveChildSubjectId(childSubject);
                examDb.insertChildSubject(childSubject);
                Intent examMainIntent = new Intent("exam_main_back");
                examMainIntent.putExtra("updateState",0);
                LocalBroadcastManager.getInstance(SubjectActivity.this).sendBroadcast(examMainIntent);
                LocalBroadcastManager.getInstance(SubjectActivity.this).sendBroadcast(new Intent("login_back"));
                startActivity(intent);
                finish();
            }
        });
        main_lv.setAdapter(parentSubjectAdapter);
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
