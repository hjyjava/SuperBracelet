package com.huang.superbracelet.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.huang.bean.Student;
import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.exam.SubjectActivity;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.db.exam.StudentDb;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView top_title_tv;
    private TextInputLayout name_til, password_til;
    private EditText name_et, pw_et;
    private CardView login_cv;
    private String userName, userPw;
    private ExamHttp examHttp;
    private StudentDb studentDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!TextUtils.isEmpty(studentId)){

        }
        setContentView(R.layout.activity_login);
        initIntance();
        initWedgit();
    }

    @Override
    protected void initIntance() {
        super.initIntance();
        examHttp = new ExamHttp(this);
        studentDb = new StudentDb(this);
    }

    @Override
    protected void initWedgit() {
        top_title_tv = (TextView) findViewById(R.id.top_title_tv);
        top_title_tv.setText("登录");

        name_til = (TextInputLayout) findViewById(R.id.name_til);
        name_et = name_til.getEditText();
        name_til.setHint("请输入用户名");

        password_til = (TextInputLayout) findViewById(R.id.password_til);
        pw_et = password_til.getEditText();
        password_til.setHint("请输入用户名");

        login_cv = (CardView) findViewById(R.id.login_cv);
        login_cv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_cv:
                userName = name_et.getText().toString().trim();
                userPw = pw_et.getText().toString().trim();
                studentLogin();
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    private void studentLogin() {
        login_cv.setClickable(false);
        examHttp.login(userName, userPw, new MyVolleyListener<Student>() {
            //        examHttp.login("h", "123", new MyVolleyListener<Student>() {
            @Override
            public void onSuccess(Student student) {
                login_cv.setClickable(true);
                studentDb.insertStudent(student);
                saveStudentToSp(student);
                startActivity(new Intent(LoginActivity.this, SubjectActivity.class));
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

}
