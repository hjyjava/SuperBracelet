package com.huang.superbracelet.bean.exam;

import java.io.Serializable;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamGate implements Serializable{
    private String Cid;
    private String userid;
    private String grade;
    private String zongfen;
    private String num;
    private boolean isAsked;
    private int errorNum;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getZongfen() {
        return zongfen;
    }

    public void setZongfen(String zongfen) {
        this.zongfen = zongfen;
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
