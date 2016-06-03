package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;
import com.huang.superbracelet.bean.exam.RankingJindu;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/31.
 */
public class JinduAdapter extends MyBaseAdapter<JinduAdapter.MyViewHolder, RankingJindu> {

    private int[] rankIcons = {R.drawable.ic_rank_first, R.drawable.ic_rank_second, R.drawable.ic_rank_third};
    private int mGuanShu;

    public JinduAdapter(Context context, List<RankingJindu> datas, int guanshu) {
        super(context, datas);
        mGuanShu = guanshu;
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_jindu, null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        RankingJindu rankingJindu = (RankingJindu) mDatas.get(position);
        myViewHolder.name_tv.setText(rankingJindu.getUsername());
        float progress = Math.round(rankingJindu.getNum() * 100f / mGuanShu);
        myViewHolder.progress_tv.setText(progress + "%");

        if (position < 3) {
            myViewHolder.jindu_tv.setVisibility(View.GONE);
            myViewHolder.jindu_iv.setVisibility(View.VISIBLE);
            myViewHolder.jindu_iv.setBackgroundResource(rankIcons[position]);
        } else {
            int grade = position + 1;
            myViewHolder.jindu_iv.setVisibility(View.GONE);
            myViewHolder.jindu_tv.setVisibility(View.VISIBLE);
            myViewHolder.jindu_tv.setText(grade + "");
        }
        myViewHolder.jindu_pb.setMax(mGuanShu);
        myViewHolder.jindu_pb.setProgress(rankingJindu.getNum());
    }

    class MyViewHolder extends BaseViewHoler {

        ImageView jindu_iv;
        TextView jindu_tv;
        TextView name_tv;
        TextView progress_tv;
        ProgressBar jindu_pb;

        public MyViewHolder(View root) {
            super(root);
            jindu_iv = (ImageView) root.findViewById(R.id.jindu_iv);
            jindu_tv = (TextView) root.findViewById(R.id.jindu_tv);
            name_tv = (TextView) root.findViewById(R.id.name_tv);
            progress_tv = (TextView) root.findViewById(R.id.progress_tv);
            jindu_pb = (ProgressBar) root.findViewById(R.id.jindu_pb);
        }
    }
}
