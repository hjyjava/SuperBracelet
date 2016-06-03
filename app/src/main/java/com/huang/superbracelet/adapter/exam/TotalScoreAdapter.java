package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;
import com.huang.superbracelet.bean.exam.RankingTotalScore;

import java.util.List;

/**
 *
 * Created by 黄家远 on 16/5/31.
 */
public class TotalScoreAdapter extends MyBaseAdapter<TotalScoreAdapter.MyViewHolder,RankingTotalScore>{

    private int[] rankIcons = {R.drawable.ic_rank_first,R.drawable.ic_rank_second,R.drawable.ic_rank_third};

    public TotalScoreAdapter(Context context, List<RankingTotalScore> datas) {
        super(context, datas);
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_total_score,null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        RankingTotalScore rankingTotalScore = (RankingTotalScore) mDatas.get(position);
        myViewHolder.name_tv.setText(rankingTotalScore.getUsername());
        myViewHolder.score_tv.setText(rankingTotalScore.getGrade());

        if (position < 3) {
            myViewHolder.total_score_tv.setVisibility(View.GONE);
            myViewHolder.total_score_iv.setVisibility(View.VISIBLE);
            myViewHolder.total_score_iv.setBackgroundResource(rankIcons[position]);
        } else {
            int grade = position + 1;
            myViewHolder.total_score_iv.setVisibility(View.GONE);
            myViewHolder.total_score_tv.setVisibility(View.VISIBLE);
            myViewHolder.total_score_tv.setText(grade + "");
        }
    }

    class MyViewHolder extends BaseViewHoler {

        ImageView total_score_iv;
        TextView total_score_tv;
        TextView name_tv;
        TextView score_tv;

        public MyViewHolder(View root) {
            super(root);
            total_score_iv = (ImageView) root.findViewById(R.id.total_score_iv);
            total_score_tv = (TextView) root.findViewById(R.id.total_score_tv);
            name_tv = (TextView) root.findViewById(R.id.name_tv);
            score_tv = (TextView) root.findViewById(R.id.score_tv);
        }
    }
}
