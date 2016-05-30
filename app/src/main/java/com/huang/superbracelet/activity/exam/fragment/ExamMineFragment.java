package com.huang.superbracelet.activity.exam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.huang.bean.Student;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseFragment;
import com.huang.superbracelet.db.exam.StudentDb;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ExamMineFragment extends BaseFragment {

    TextView username_tv,xingming_tv,xuehao_tv,
            nianji_tv,banji_tv,xingbie_tv;
    private StudentDb studentDb;
    private Student mStudent;

    public static ExamMineFragment newInstance() {
        ExamMineFragment fragment = new ExamMineFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentDb = new StudentDb(mContext);
    }

    @Override
    protected void initWidget() {
        username_tv = (TextView) mainLayout.findViewById(R.id.username_tv);
        xingming_tv = (TextView) mainLayout.findViewById(R.id.xingming_tv);
        xuehao_tv = (TextView) mainLayout.findViewById(R.id.xuehao_tv);
        nianji_tv = (TextView) mainLayout.findViewById(R.id.nianji_tv);
        banji_tv = (TextView) mainLayout.findViewById(R.id.banji_tv);
        xingbie_tv = (TextView) mainLayout.findViewById(R.id.xingbie_tv);
        initData();
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_exam_mine, null);
    }

    private void initData(){
        mStudent = studentDb.queryStudent(studentId);
        if (mStudent==null)
            return;
        username_tv.setText("账号:"+mStudent.getUsername());
        xingming_tv.setText("姓名:"+mStudent.getXingming());
        xuehao_tv.setText("学号:"+mStudent.getXuehao());
        nianji_tv.setText("年级:"+mStudent.getNianji());
        banji_tv.setText("班级:"+mStudent.getBanji());
        xingbie_tv.setText("性别:"+mStudent.getXingming());
    }
}
