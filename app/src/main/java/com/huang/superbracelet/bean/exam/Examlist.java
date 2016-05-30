package com.huang.superbracelet.bean.exam;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class Examlist {

    private List<Exam> Examlist;
    private String name;

    public List<Exam> getExamlist() {
        return Examlist;
    }

    public void setExamlist(List<Exam> examlist) {
        Examlist = examlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
