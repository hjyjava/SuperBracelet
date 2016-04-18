package com.huang.superbracelet.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.huang.superbracelet.base.MyApplication;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by 黄家远 on 16/4/15.
 */
public class VolleyRequest {

    public static <T> void request(final MyVolleyListener<T> myVolleyListener,MyRequest myRequest){
        switch (myRequest.getMyRequestTpye()){
            case STRING:
                stringRequest(myRequest, new VolleyInterface<T>(myRequest.getContext()) {
                    @Override
                    public void onMySuccess(T result) {
                        if(myVolleyListener!=null){
                            myVolleyListener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError volleyError) {
                        if(myVolleyListener!=null){
                            myVolleyListener.onError(volleyError);
                        }
                    }
                });
                break;
            case JSONOBJECT:
                jsonObjectRequest(myRequest, new VolleyInterface<T>(myRequest.getContext()) {
                    @Override
                    public void onMySuccess(T result) {
                        if (myVolleyListener != null) {
                            myVolleyListener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError volleyError) {
                        if (myVolleyListener != null) {
                            myVolleyListener.onError(volleyError);
                        }
                    }
                });
                break;
            case JSONARRAY:
                jsonArrayRequest(myRequest, new VolleyInterface<T>(myRequest.getContext()) {
                    @Override
                    public void onMySuccess(T result) {
                        if (myVolleyListener != null) {
                            myVolleyListener.onSuccess(result);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError volleyError) {
                        if (myVolleyListener != null) {
                            myVolleyListener.onError(volleyError);
                        }
                    }
                });
                break;
            default:
                break;
        }

    }

    private static <T> void stringRequest(final MyRequest myRequest, VolleyInterface<T> volleyInterface) {
        StringRequest stringRequest = new StringRequest(myRequest.getMethod(),
                myRequest.getUrl(),
                volleyInterface.LoadingListener(),
                volleyInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (myRequest.getMethod() == Method.POST) {
                    return myRequest.getParams();
                }
                return super.getParams();
            }
        };
        stringRequest.setTag(myRequest.getTag());
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }

    private static <T> void jsonObjectRequest(final MyRequest myRequest, VolleyInterface<T> volleyInterface) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(myRequest.getMethod(),
                myRequest.getUrl(),
                myRequest.getMethod() == Request.Method.GET ? null : new JSONObject(myRequest.getParams()),
                volleyInterface.LoadingListener(),
                volleyInterface.errorListener());
        jsonObjectRequest.setTag(myRequest.getTag());
        MyApplication.getHttpQueues().add(jsonObjectRequest);
        MyApplication.getHttpQueues().start();
    }

    private static <T> void jsonArrayRequest(final MyRequest myRequest, VolleyInterface<T> volleyInterface) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myRequest.getUrl(),
                volleyInterface.LoadingListener(),
                volleyInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (myRequest.getMethod() == Method.POST) {
                    return myRequest.getParams();
                }
                return super.getParams();
            }
        };
        jsonArrayRequest.setTag(myRequest.getTag());
        MyApplication.getHttpQueues().add(jsonArrayRequest);
        MyApplication.getHttpQueues().start();
    }
}
