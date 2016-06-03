package com.huang.superbracelet.bean.exam;

import java.io.Serializable;

/**
 * Created by 黄家远 on 16/6/1.
 */
public class RankingMoni implements Serializable {

    private String accuracy;
    private String userid;
    private String username;

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
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
