package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huang.bean.ChildSubject;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;
import com.huang.superbracelet.listener.OnClickChildSubjectListener;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/26.
 */
public class ChildSubjectAdapter extends MyBaseAdapter<ChildSubjectAdapter.MyViewHolder,ChildSubject>{

    private OnClickChildSubjectListener mListener;

    public ChildSubjectAdapter(Context context, List<ChildSubject> datas,OnClickChildSubjectListener onClickChildSubjectListener) {
        super(context, datas);
        this.mListener = onClickChildSubjectListener;
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.layout_childsubject_item,null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        final ChildSubject childSubject = (ChildSubject) mDatas.get(position);
        myViewHolder.textView.setText(childSubject.getName());
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.click(childSubject);
                }
            }
        });
    }

    class MyViewHolder extends BaseViewHoler{
        TextView textView;

        public MyViewHolder(View root) {
            super(root);
            textView = (TextView) root.findViewById(R.id.child_subject_tv);
        }
    }
}
