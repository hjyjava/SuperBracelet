package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.bean.exam.ExamGate;
import com.huang.superbracelet.listener.RVItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/25.
 */
public class ExamGridAdapter extends RecyclerView.Adapter<ExamGridAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ExamGate> mExamGateList = new ArrayList<>();
    private RVItemClickListener mRvItemClickListener;

    public ExamGridAdapter(Context context, List<ExamGate> examGateList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mExamGateList = examGateList;
    }

    public void update(List<ExamGate> examGateList){
        mExamGateList.clear();
        this.mExamGateList = examGateList;
        notifyDataSetChanged();
    }

    public void setRvItemClickListener(RVItemClickListener rvItemClickListener) {
        this.mRvItemClickListener = rvItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_exam, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ExamGate examGate = mExamGateList.get(position);
        if (examGate.isAsked()) {
            holder.gate_tv.setText(position + 1 + "");
            holder.gate_iv.setImageResource(R.drawable.exam_unlocked_icon);
        } else {
            holder.gate_tv.setText("");
            holder.gate_iv.setImageResource(R.drawable.exam_locked_icon);
        }
        if (examGate.getErrorNum() != 0) {
            holder.red_layout.setVisibility(View.VISIBLE);
            holder.red_tv.setText(examGate.getErrorNum()+"");
        }else {
            holder.red_layout.setVisibility(View.GONE);
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRvItemClickListener!=null){
                    mRvItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExamGateList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View root;
        ImageView gate_iv;
        TextView gate_tv;
        RelativeLayout red_layout;
        TextView red_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            gate_iv = (ImageView) itemView.findViewById(R.id.gate_iv);
            gate_tv = (TextView) itemView.findViewById(R.id.gate_tv);

            red_layout = (RelativeLayout) itemView.findViewById(R.id.red_layout);
            red_tv = (TextView) itemView.findViewById(R.id.red_tv);
        }

    }
}
