package com.huang.superbracelet.db.exam;

import android.content.Context;

import com.huang.bean.ChildSubject;
import com.huang.dao.ChildSubjectDao;
import com.huang.superbracelet.base.MyApplication;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 *
 * Created by 黄家远 on 16/5/29.
 */
public class ExamDb {

    private ChildSubjectDao mChildSubjectDao;

    public ExamDb(Context context) {
        mChildSubjectDao = MyApplication.getDaoSession(context).getChildSubjectDao();
    }

    public void insertChildSubject(ChildSubject childSubject){
        if (queryChildSubject(childSubject.getId())==null){
            mChildSubjectDao.insertOrReplace(childSubject);
        }
    }

    public ChildSubject queryChildSubject(String Cid){
        QueryBuilder<ChildSubject> qb = mChildSubjectDao.queryBuilder();
        qb.where(ChildSubjectDao.Properties.Id.eq(Cid));
        List<ChildSubject> childSubjectList = qb.list();
        if(childSubjectList.size()>0){
            return childSubjectList.get(0);
        }
        return null;
    }
}
