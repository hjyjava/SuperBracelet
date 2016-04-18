package com.huang.superbracelet.exception;

/**
 * Created by 黄家远 on 16/4/18.
 */
public class BraceletExecption extends Exception {
    private String mMessage;
    private int mResponseCode;

    public BraceletExecption(String message, int statusCode) {
        this.mMessage = message;
        this.mResponseCode = statusCode;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
