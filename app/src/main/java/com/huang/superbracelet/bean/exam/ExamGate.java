package com.huang.superbracelet.bean.exam;

import java.io.Serializable;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamGate implements Serializable{
    private String Cid;
    private String userid;
    private String num;
    private boolean isAsked;
    private int errorNum;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    public boolean isAsked() {
        return isAsked;
    }

    public void setAsked(boolean asked) {
        isAsked = asked;
    }
}
