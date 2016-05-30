package com.huang.superbracelet.db;

import android.content.Context;

import com.huang.dao.DaoSession;
import com.huang.superbracelet.base.MyApplication;

/**
 * Created by 黄家远 on 16/5/27.
 */
public class MyDbUtils {

    protected Context mContext;
    private static final String TAG = MyDbUtils.class.getSimpleName();
    private static MyDbUtils instance;
    private static Context appContext;
    private DaoSession mDaoSession;

    public MyDbUtils() {
        // TODO Auto-generated constructor stub
    }

    public static MyDbUtils getInstance(Context context){
        if(instance == null){
            instance = new MyDbUtils();
            if(appContext == null){
                appContext = context.getApplicationContext();
            }
            instance.mDaoSession = MyApplication.getDaoSession(context);
        }
        return instance;
    }
}
