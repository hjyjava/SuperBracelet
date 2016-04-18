package com.huang.superbracelet.http.everyhttp;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.huang.bean.User;
import com.huang.superbracelet.common.constant.UrlConstant;
import com.huang.superbracelet.http.MyGlobalHttp;
import com.huang.superbracelet.http.MyRequest;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄家远 on 16/4/15.
 */
public class UserHttp extends MyGlobalHttp {

    public UserHttp(Context context) {
        super(context);
    }

    /**
     * 登陆
     *
     * @return
     */
    public void login(String name, String pw, final MyVolleyListener<User> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setUrl(UrlConstant.login_url);
        Map<String, String> params = new HashMap<>();
        params.put("UserName", name);
        params.put("UserPassword", pw);
        myRequest.setParams(params);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    User user = JSON.parseObject(jsonObject.getString("data"), User.class);
                    myVolleyListener.onSuccess(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }


}
