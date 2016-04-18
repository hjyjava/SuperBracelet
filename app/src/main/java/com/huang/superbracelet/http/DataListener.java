package com.huang.superbracelet.http;

/**
 * Created by 黄家远 on 16/4/15.
 */
public interface DataListener<T> {

    void onDataChanged(T data);
    void onError(String errorCode,String errorMessage);
}
