package com.huang.superbracelet.bean.exam;

import java.io.Serializable;

/**
 * Created by 黄家远 on 16/6/1.
 */
public class RankingTotalScore implements Serializable {

    private String grade;
    private String userid;
    private String username;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
