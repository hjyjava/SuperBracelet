package com.huang.superbracelet.bean.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/6/1.
 */
public class ExamRanking implements Serializable {

    private String accuracyranklistname;
    private List<RankingMoni> accuracyranklist = new ArrayList<>();
    private String graderanklistname;
    private List<RankingTotalScore> graderanklist = new ArrayList<>();
    private String numranklistname;
    private List<RankingJindu> numranklist = new ArrayList<>();
    private int guanshu;

    private String typename;

    public List<RankingMoni> getAccuracyranklist() {
        return accuracyranklist;
    }

    public void setAccuracyranklist(List<RankingMoni> accuracyranklist) {
        this.accuracyranklist = accuracyranklist;
    }

    public String getAccuracyranklistname() {
        return accuracyranklistname;
    }

    public void setAccuracyranklistname(String accuracyranklistname) {
        this.accuracyranklistname = accuracyranklistname;
    }

    public List<RankingTotalScore> getGraderanklist() {
        return graderanklist;
    }

    public void setGraderanklist(List<RankingTotalScore> graderanklist) {
        this.graderanklist = graderanklist;
    }

    public String getGraderanklistname() {
        return graderanklistname;
    }

    public void setGraderanklistname(String graderanklistname) {
        this.graderanklistname = graderanklistname;
    }

    public List<RankingJindu> getNumranklist() {
        return numranklist;
    }

    public void setNumranklist(List<RankingJindu> numranklist) {
        this.numranklist = numranklist;
    }

    public String getNumranklistname() {
        return numranklistname;
    }

    public void setNumranklistname(String numranklistname) {
        this.numranklistname = numranklistname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getGuanshu() {
        return guanshu;
    }

    public void setGuanshu(int guanshu) {
        this.guanshu = guanshu;
    }
}
