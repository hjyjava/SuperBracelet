package com.huang.superbracelet.http;

import com.android.volley.VolleyError;

/**
 * Created by 黄家远 on 16/4/17.
 */
public interface MyVolleyListener<T> {
    void onSuccess(T t);
    void onError(VolleyError volleyError);
}
