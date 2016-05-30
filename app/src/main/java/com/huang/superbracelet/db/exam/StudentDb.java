package com.huang.superbracelet.db.exam;

import android.content.Context;

import com.huang.bean.Student;
import com.huang.dao.StudentDao;
import com.huang.superbracelet.base.MyApplication;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by 黄家远 on 16/5/27.
 */
public class StudentDb {
    private StudentDao mStudentDao;

    public StudentDb(Context context) {
        mStudentDao = MyApplication.getDaoSession(context).getStudentDao();
    }

    public void insertStudent(Student student) {
        if (queryStudent(student.getId()) == null) {
            mStudentDao.insert(student);
        }
    }

    public Student queryStudent(String Id) {
        QueryBuilder<Student> qb = mStudentDao.queryBuilder();
        qb.where(StudentDao.Properties.Id.eq(Id));
        List<Student> studentList = qb.list();
        if(studentList.size()>0){
            return studentList.get(0);
        }
        return null;
    }

    public List<Student> queryStudentList() {
//        List<Student> students = mStudentDao.queryBuilder().list();
        List<Student> students = mStudentDao.loadAll();
        return students;
    }

}
