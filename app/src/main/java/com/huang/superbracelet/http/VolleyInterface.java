package com.huang.superbracelet.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.huang.superbracelet.utils.MyToastUtils;

/**
 * Created by 黄家远 on 16/4/15.
 */
public abstract class VolleyInterface<T> {

    private Context mContext;
    private Response.Listener mSuccessListener;
    private Response.ErrorListener mErrorListener;

    private ProgressDialog waitDialog;


    protected void waitShow() {
        if (waitDialog != null && !waitDialog.isShowing()) {
            waitDialog.show();
        }
    }

    protected void waitHide() {
        if (waitDialog != null && waitDialog.isShowing()) {
            waitDialog.hide();
        }
    }

    public VolleyInterface(Context context) {
        this.mContext = context;
        waitDialog = new ProgressDialog(mContext);
        waitDialog.setMessage("请稍等");
        waitShow();
    }

    public abstract void onMySuccess(T result);

    public abstract void onMyError(VolleyError volleyError);

    public Response.Listener LoadingListener() {
        mSuccessListener = new Response.Listener<T>() {
            @Override
            public void onResponse(T t) {
                waitHide();
                onMySuccess(t);
            }
        };
        return mSuccessListener;
    }

    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                waitHide();
                if (!TextUtils.isEmpty(volleyError.getMessage())) {
                    MyToastUtils.showShort(mContext, volleyError.getMessage());
                }
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }

}
