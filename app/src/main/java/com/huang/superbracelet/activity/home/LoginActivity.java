package com.huang.superbracelet.activity.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.huang.bean.Student;
import com.huang.superbracelet.R;
import com.huang.superbracelet.activity.exam.SubjectActivity;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.db.exam.StudentDb;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.ExamHttp;
import com.huang.superbracelet.utils.MyToastUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView top_title_tv;
    private TextInputLayout name_til, password_til;
    private EditText name_et, pw_et;
    private CardView login_cv;
    private String userName, userPw;
    private ExamHttp examHttp;
    private StudentDb studentDb;
    private ImageView login_iv;

    private BackReceiver backReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!TextUtils.isEmpty(studentId)){

        }
        setContentView(R.layout.activity_login);
        initIntance();
        initWedgit();
        backReceiver = new BackReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(backReceiver,new IntentFilter("login_back"));
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

        login_iv = (ImageView) findViewById(R.id.login_iv);
        Glide.with(this).
                load("http://news.mydrivers.com/Img/20091207/09430342.png")
                .into(login_iv);
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
        if(TextUtils.isEmpty(userName)){
            MyToastUtils.showShort(this,"用户名不能为空");
        }
        login_cv.setClickable(false);
        examHttp.login(userName, userPw, new MyVolleyListener<Student>() {
            //        examHttp.login("h", "123", new MyVolleyListener<Student>() {
            @Override
            public void onSuccess(Student student) {
                login_cv.setClickable(true);
                studentDb.insertStudent(student);
                saveStudentToSp(student);
                startActivityForResult(new Intent(LoginActivity.this, SubjectActivity.class),0);
            }

            @Override
            public void onError(VolleyError volleyError) {
                login_cv.setClickable(true);
            }
        });
    }

    class BackReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}
