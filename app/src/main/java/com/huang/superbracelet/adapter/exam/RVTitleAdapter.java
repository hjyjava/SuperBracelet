package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.listener.RVItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by 黄家远 on 16/5/13.
 */
public class RVTitleAdapter extends RecyclerView.Adapter<RVTitleAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> mNames = new ArrayList<>();
    private LayoutInflater mInflater;
    private int mCheckNum;
    private RVItemClickListener rvItemClickListener;

    public RVTitleAdapter(Context context, List<String> names) {
        this.mContext = context;
        this.mNames = names;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.title_text_item,null));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckNum(position);
                if(rvItemClickListener!=null){
                    rvItemClickListener.onItemClick(holder.root,position);
                }
            }
        });
        holder.title_tv.setText(mNames.get(position));
        if(position == mCheckNum){
            holder.title_tv.setTextColor(0xff29b8ee);
            holder.bottom_view.setVisibility(View.VISIBLE);
        }else{
            holder.title_tv.setTextColor(0xff000000);
            holder.bottom_view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        View root;
        TextView title_tv;
        View bottom_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            bottom_view =  itemView.findViewById(R.id.bottom_view);
        }
    }

    public void setCheckNum(int checkNum) {
        if (checkNum==mCheckNum)
            return;
        this.mCheckNum = checkNum;
        notifyDataSetChanged();
    }

    public void setRvItemClickListener(RVItemClickListener rvItemClickListener) {
        this.rvItemClickListener = rvItemClickListener;
    }
}
