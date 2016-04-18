package com.huang.superbracelet.activity.home;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.huang.bean.User;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseActivity;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.everyhttp.UserHttp;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText name_et,pw_et;
    private CardView login_cv;
    private String userName,userPw;
    private User mUser ;
    private UserHttp userHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initIntance();
        initWedgit();
    }

    @Override
    protected void initIntance() {
        super.initIntance();
        userHttp = new UserHttp(this);
    }

    @Override
    protected void initWedgit() {
        super.initWedgit();
        name_et = (EditText) findViewById(R.id.name_et);
        pw_et = (EditText) findViewById(R.id.pw_et);
        login_cv = (CardView) findViewById(R.id.login_cv);
        login_cv.setOnClickListener(this);
        userHttp.login("18006195599", "654321", new MyVolleyListener<User>() {
            @Override
            public void onSuccess(User user) {
                mUser = user;
//                user.toString()
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_cv:
                userName = name_et.getText().toString().trim();
                userPw = pw_et.getText().toString().trim();
                login();
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    private void login(){
        userHttp.login(userName, userPw, new MyVolleyListener<User>() {
            @Override
            public void onSuccess(User user) {
                mUser = user;
//                user.toString()
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        });
    }

//    private void login(){
//        StringRequest request = new StringRequest(Request.Method.POST, UrlConstant.login_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                MyToastUtils.showShort(LoginActivity.this,s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> hasMap = new HashMap<>();
//                hasMap.put("UserName",userName);
//                hasMap.put("UserPassword",userPw);
//                return hasMap;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> hasMap = new HashMap<>();
//                hasMap.put("User-Agent", StringConstant.POST_HEADER);
//                return super.getHeaders();
//            }
//        };
//        request.setTag("loginRequest");
//        MyApplication.getHttpQueues().add(request);
//    }
}
