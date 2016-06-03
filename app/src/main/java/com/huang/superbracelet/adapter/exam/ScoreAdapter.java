package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;
import com.huang.superbracelet.bean.exam.ExamGrade;

import java.util.List;

/**
 *
 * Created by 黄家远 on 16/5/31.
 */
public class ScoreAdapter extends MyBaseAdapter<ScoreAdapter.MyViewHolder,ExamGrade>{

    public ScoreAdapter(Context context, List<ExamGrade> datas) {
        super(context, datas);
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_score,null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        ExamGrade examGrade = (ExamGrade) mDatas.get(position);
        myViewHolder.gate_tv.setText(examGrade.getRounds());
        myViewHolder.score_tv.setText(examGrade.getGrade());
        myViewHolder.rate_tv.setText(examGrade.getAccuracy()+"%");
    }

    class MyViewHolder extends BaseViewHoler {

        TextView gate_tv;
        TextView score_tv;
        TextView rate_tv;

        public MyViewHolder(View root) {
            super(root);
            gate_tv = (TextView) root.findViewById(R.id.gate_tv);
            score_tv = (TextView) root.findViewById(R.id.score_tv);
            rate_tv = (TextView) root.findViewById(R.id.rate_tv);
        }
    }
}
