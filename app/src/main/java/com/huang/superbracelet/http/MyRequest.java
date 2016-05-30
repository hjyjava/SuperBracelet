package com.huang.superbracelet.http;

import android.content.Context;

import com.android.volley.Request;

import java.util.Map;

/**
 * Created by 黄家远 on 16/4/15.
 */
public class MyRequest {

    private Context context;
    private int method = Request.Method.GET;
    private String url;
    private String tag;
    private MyRequestTpye myRequestTpye = MyRequestTpye.STRING;
    private Map<String,String > params;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public MyRequestTpye getMyRequestTpye() {
        return myRequestTpye;
    }

    public void setMyRequestTpye(MyRequestTpye myRequestTpye) {
        this.myRequestTpye = myRequestTpye;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
