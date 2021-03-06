package com.huang.superbracelet.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 黄家远 on 16/4/15.
 */
public class MyApplication extends Application{

    private static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues(){
        return queues;
    }
}
