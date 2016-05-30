package com.huang.superbracelet.bean.exam;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class Exam {

    private String Tid;
    private String analysis;
    private String analysisimage;
    private List<String> answers;
    private String img1;
    private String img2;
    private String questionId;
    private String questionText;
    private String typename;
    private List<String> xuanxiangimg;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysisimage() {
        return analysisimage;
    }

    public void setAnalysisimage(String analysisimage) {
        this.analysisimage = analysisimage;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public List<String> getXuanxiangimg() {
        return xuanxiangimg;
    }

    public void setXuanxiangimg(List<String> xuanxiangimg) {
        this.xuanxiangimg = xuanxiangimg;
    }
}
