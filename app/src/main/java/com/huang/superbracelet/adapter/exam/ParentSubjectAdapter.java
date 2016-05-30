package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huang.bean.ParentSubject;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;
import com.huang.superbracelet.listener.OnClickChildSubjectListener;
import com.huang.superbracelet.view.MyGridView;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ParentSubjectAdapter extends MyBaseAdapter<ParentSubjectAdapter.MyViewHolder,ParentSubject> {

    private int[] shows;
    private OnClickChildSubjectListener mListener;

    public ParentSubjectAdapter(Context context, List<ParentSubject> datas, OnClickChildSubjectListener onClickChildSubjectListener) {
        super(context, datas);
        shows = new int[mDatas.size()];
        this.mListener = onClickChildSubjectListener;
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_subject_item,null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        ParentSubject parentSubject = (ParentSubject)mDatas.get(position);
        myViewHolder.textView.setText(parentSubject.getName());
        if (shows[position] % 2 == 1) {
            myViewHolder.tip_iv.setImageResource(R.drawable.arrow_right);
            myViewHolder.myGridView.setVisibility(View.GONE);
        } else {
            myViewHolder.tip_iv.setImageResource(R.drawable.arrow_down);
            myViewHolder.myGridView.setVisibility(View.VISIBLE);
        }
        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shows[position]++;
                notifyDataSetInvalidated();
            }
        });
        ChildSubjectAdapter childSubjectAdapter = new ChildSubjectAdapter(mContext,parentSubject.getKulist(),mListener);
        myViewHolder.myGridView.setAdapter(childSubjectAdapter);
    }

    class MyViewHolder extends BaseViewHoler{
        RelativeLayout relativeLayout;
        TextView textView;
        ImageView tip_iv;
        MyGridView myGridView;

        public MyViewHolder(View root) {
            super(root);
            relativeLayout = (RelativeLayout) root.findViewById(R.id.title_layout);
            textView = (TextView) root.findViewById(R.id.parent_subject_tv);
            tip_iv = (ImageView) root.findViewById(R.id.tip_iv);
            myGridView = (MyGridView) root.findViewById(R.id.child_subject_gv);
        }
    }
}
