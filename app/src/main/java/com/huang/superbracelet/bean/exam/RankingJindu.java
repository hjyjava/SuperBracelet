package com.huang.superbracelet.bean.exam;

import java.io.Serializable;

/**
 * Created by 黄家远 on 16/6/1.
 */
public class RankingJindu implements Serializable {

    private int num;
    private String userid;
    private String username;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
