package com.huang.superbracelet.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.huang.bean.ChildSubject;
import com.huang.bean.Student;
import com.huang.superbracelet.utils.MyToastUtils;
import com.huang.superbracelet.utils.NetUtils;
import com.huang.superbracelet.utils.SPUtils;

/**
 * Created by 黄家远 on 16/4/14.
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected SPUtils spUtils;

    protected String studentId;
    protected String childSubjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = SPUtils.getInstance(this);
        studentId = (String) spUtils.get("studentId", "");
        childSubjectId = (String) spUtils.get("ChildSubjectId", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void initIntent() {

    }

    protected void initIntance() {

    }

    protected void initWedgit(){

    }

    protected void initData() {

    }

    protected boolean checkNetwork(){
        if (!NetUtils.isConnected(this)){
            MyToastUtils.showShort(this, "网络未连接");
            return false;
        }
        return true;
    }

    protected void saveStudentToSp(Student student){
        spUtils.put("studentId", student.getId());
    }

    protected void removeStudent(String studentId){
        spUtils.remove("studentId");
    }

    protected void saveChildSubjectId(ChildSubject childSubject){
        spUtils.put("ChildSubjectId", childSubject.getId());
    }

    protected void removeChildSubjectId(String childSubjectId){
        spUtils.remove("ChildSubjectId");
    }
}