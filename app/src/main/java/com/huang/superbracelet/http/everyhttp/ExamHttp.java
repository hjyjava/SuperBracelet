package com.huang.superbracelet.http.everyhttp;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;
import com.huang.bean.Jindu;
import com.huang.bean.ParentSubject;
import com.huang.bean.Student;
import com.huang.superbracelet.bean.exam.ExamGrade;
import com.huang.superbracelet.bean.exam.Examlist;
import com.huang.superbracelet.bean.exam.GateState;
import com.huang.superbracelet.bean.exam.ExamRanking;
import com.huang.superbracelet.common.constant.UrlConstant;
import com.huang.superbracelet.http.MyGlobalHttp;
import com.huang.superbracelet.http.MyRequest;
import com.huang.superbracelet.http.MyVolleyListener;
import com.huang.superbracelet.http.VolleyRequest;
import com.huang.superbracelet.utils.Base64Util;
import com.huang.superbracelet.utils.Md5Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 黄家远 on 16/5/25.
 */
public class ExamHttp extends MyGlobalHttp {
    public ExamHttp(Context mContext) {
        super(mContext);
    }

    /**
     * 考生登录
     *
     * @param name
     * @param pw
     * @param myVolleyListener
     */
    public void login(String name, String pw, final MyVolleyListener<Student> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("username", name);
        params.put("password", Md5Util.MD5(pw));
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_LOGIN);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                Student student = JSON.parseObject(s, Student.class);
                myVolleyListener.onSuccess(student);
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }

    /**
     * 获得科目
     *
     * @param myVolleyListener
     */
    public void getSubjectList(final MyVolleyListener<List<ParentSubject>> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        myRequest.setUrl(UrlConstant.KAOSHI_SUBJECT);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                List<ParentSubject> parentSubjectList = JSONArray.parseArray(s, ParentSubject.class);
                myVolleyListener.onSuccess(parentSubjectList);
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }


    /**
     * 获取题目
     *
     * @param type
     * @param num
     * @param myVolleyListener
     */
    public void getExamList(String type, String num, final MyVolleyListener<Examlist> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("num", num);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_EXAMLIST);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                Examlist examlist = JSON.parseObject(s, Examlist.class);
                myVolleyListener.onSuccess(examlist);
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }

    /**
     * 获取答题进度
     *
     * @param studentId
     * @param childSubjectId
     */
    public void getJindu(String studentId, String childSubjectId, final MyVolleyListener<Jindu> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("Cid", childSubjectId);
        params.put("userid", studentId);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_GET_GRADE);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                myVolleyListener.onSuccess(JSON.parseObject(s, Jindu.class));
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }

    /**
     * 提交分数
     *
     * @param childSubjectId
     * @param studentId
     * @param grade          考试得分
     * @param zongfen        总分
     * @param num            考试关数
     */
    public void submitScore(String childSubjectId, String studentId, String grade, String zongfen, String num, final MyVolleyListener<Boolean> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);

        String cGrade = Base64Util.encode(grade);
        String cZongfen = Base64Util.encode(zongfen);

        Map<String, String> params = new HashMap<>();
        params.put("Cid", childSubjectId);
        params.put("userid", studentId);
        params.put("grade", cGrade);
        params.put("zongfen", cZongfen);
        params.put("num", num);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_INSERT_GRADE);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                JSONObject jsonObject = JSON.parseObject(s);
                myVolleyListener.onSuccess(jsonObject.getInteger("status") == 0);
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }

    /**
     * 获取错题列表(未答)
     * @param studentId
     * @param childSubjectId
     * @param myVolleyListener
     */
    public void getErrorList(String studentId, String childSubjectId, final MyVolleyListener<List<GateState>> myVolleyListener) {
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("Cid", childSubjectId);
        params.put("userid", studentId);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_ERRORLIST_W);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                myVolleyListener.onSuccess(JSON.parseArray(s, GateState.class));
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        }, myRequest);
    }

    /**
     * 获取每关分数列表
     * @param studentId
     * @param childSubjectId
     * @param myVolleyListener
     */
    public void getGradeList(String studentId, String childSubjectId, final MyVolleyListener<List<ExamGrade>> myVolleyListener){
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("Cid", childSubjectId);
        params.put("userid", studentId);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_GRADE_SHOW);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                myVolleyListener.onSuccess(JSONArray.parseArray(s,ExamGrade.class));
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        },myRequest);
    }

    /**
     * 获取排行列表
     * @param studentId
     * @param childSubjectId
     * @param myVolleyListener
     */
    public void getRinkingList(String studentId, String childSubjectId, final MyVolleyListener<ExamRanking> myVolleyListener){
        MyRequest myRequest = new MyRequest();
        myRequest.setContext(mContext);
        Map<String, String> params = new HashMap<>();
        params.put("Cid", childSubjectId);
        params.put("userid", studentId);
        myRequest.setParams(params);
        myRequest.setUrl(UrlConstant.KAOSHI_GRADE_LIST);
        VolleyRequest.request(new MyVolleyListener<String>() {
            @Override
            public void onSuccess(String s) {
                myVolleyListener.onSuccess(JSON.parseObject(s,ExamRanking.class));
            }

            @Override
            public void onError(VolleyError volleyError) {
                myVolleyListener.onError(volleyError);
            }
        },myRequest);
    }
}
