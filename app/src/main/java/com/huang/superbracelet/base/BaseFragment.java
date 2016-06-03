package com.huang.superbracelet.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huang.bean.ChildSubject;
import com.huang.bean.Student;
import com.huang.superbracelet.utils.SPUtils;

/**
 * Created by 黄家远 on 16/4/14.
 */
public abstract class BaseFragment extends Fragment {

    protected View mainLayout;
    protected Context mContext;
    protected SPUtils spUtils;

    protected String studentId;
    protected String childSubjectId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        } else {
            mContext = MyApplication.getmContext();
        }
        spUtils = SPUtils.getInstance(mContext);
        studentId = (String) spUtils.get("studentId", "");
        childSubjectId = (String) spUtils.get("ChildSubjectId", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainLayout == null) {
            mainLayout = buildMainView(inflater);
            initWidget();
        } else {
            ViewGroup parent = (ViewGroup) mainLayout.getParent();
            if (parent != null) {
                parent.removeView(mainLayout);
            }
        }
        return mainLayout;
    }

    protected abstract View buildMainView(LayoutInflater inflater);

    protected abstract void initWidget();


    protected void saveStudentToSp(Student student) {
        spUtils.put("studentId", student.getId());
    }

    protected void removeStudent(String studentId) {
        spUtils.remove("studentId");
    }

    protected void saveChildSubjectId(ChildSubject childSubject) {
        spUtils.put("ChildSubjectId", childSubject.getId());
    }
    protected void removeChildSubjectId(String childSubjectId) {
        spUtils.remove("ChildSubjectId");
    }
}
