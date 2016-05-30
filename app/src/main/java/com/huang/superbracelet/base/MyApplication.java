package com.huang.superbracelet.base;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.huang.dao.DaoMaster;
import com.huang.dao.DaoSession;
import com.huang.superbracelet.db.MyDevOpenHelper;

/**
 * Created by 黄家远 on 16/4/15.
 */
public class MyApplication extends Application{

    private static Context mContext;
    private static RequestQueue queues;

    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
        mContext = getApplicationContext();
    }

    public static DaoSession getDaoSession(Context context){
        if(daoSession == null){
            DaoMaster.OpenHelper helper = new MyDevOpenHelper(context,MyDevOpenHelper.DB_NAME,null);
            DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static RequestQueue getHttpQueues(){
        return queues;
    }

    public static Context getmContext() {
        return mContext;
    }
}
