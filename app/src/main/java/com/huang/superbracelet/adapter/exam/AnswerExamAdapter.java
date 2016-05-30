package com.huang.superbracelet.adapter.exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/28.
 */
public class AnswerExamAdapter extends MyBaseAdapter<AnswerExamAdapter.MyViewHolder, String> {

    public static String[] flagArray = {"A", "B", "C", "D", "E", "F", "E", "G", "H"};
    private int mAnswerPosition;
    private int selectPosition=-1;
    private boolean isAsked;

    public AnswerExamAdapter(Context context, List<String> datas, int answerPosition) {
        super(context, datas);
        this.mAnswerPosition = answerPosition;
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_question_layout, null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        if (isAsked) {
            if (selectPosition == mAnswerPosition) {
                if (position == mAnswerPosition) {
                    myViewHolder.select_iv.setImageResource(R.drawable.ic_image_right);
                } else {
                    myViewHolder.select_iv.setImageResource(R.drawable.ic_answer_nomal);
                }
            } else {
                if (selectPosition == position) {
                    myViewHolder.select_iv.setImageResource(R.drawable.ic_answer_wrong);
                } else {
                    myViewHolder.select_iv.setImageResource(R.drawable.ic_answer_nomal);
                }
            }
        } else {
            myViewHolder.select_iv.setImageResource(R.drawable.ic_answer_nomal);
        }
        myViewHolder.flag_tv.setText(flagArray[position]+".");
        myViewHolder.content_tv.setText(mDatas.get(position) + "");
        myViewHolder.getmRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAsked) {
                    isAsked = true;
                    selectPosition = position;
                    if (mOnAnswerClick != null) {
                        mOnAnswerClick.answer(selectPosition == mAnswerPosition);
                    }
                }
            }
        });
    }

    public interface OnAnswerClick {
        void answer(boolean isRight);
    }

    private OnAnswerClick mOnAnswerClick;

    public void setOnAnswerClick(OnAnswerClick onAnswerClick) {
        this.mOnAnswerClick = onAnswerClick;
    }

    public void changeDatas(List<String> datas,int answerPosition){
        isAsked=false;
        mDatas = datas;
        this.mAnswerPosition = answerPosition;
        notifyDataSetChanged();
    }

    class MyViewHolder extends BaseViewHoler {

        TextView flag_tv;
        TextView content_tv;
        ImageView select_iv;
        ImageView content_iv;

        public MyViewHolder(View root) {
            super(root);
            select_iv = (ImageView) root.findViewById(R.id.select_iv);
            flag_tv = (TextView) root.findViewById(R.id.flag_tv);
            content_tv = (TextView) root.findViewById(R.id.content_tv);
            content_iv = (ImageView) root.findViewById(R.id.content_iv);
        }
    }

    public boolean isAsked() {
        return isAsked;
    }
}
