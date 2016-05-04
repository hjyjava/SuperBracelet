package com.huang.superbracelet.activity.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huang.bean.User;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.BaseViewHoler;
import com.huang.superbracelet.base.MyBaseAdapter;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/4.
 */
public class TestAdapter extends MyBaseAdapter<TestAdapter.MyViewHolder, User> {

    public TestAdapter(Context mContext, List<User> datas) {
        super(mContext, datas);
    }

    @Override
    protected MyViewHolder onCreateViewHolder(ViewGroup parent) {
        return new MyViewHolder(mInflater.inflate(R.layout.activity_login,null));
    }

    @Override
    protected void onBindViewHolder(MyViewHolder myViewHolder, int position) {

    }

    class MyViewHolder extends BaseViewHoler {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(View root) {
            super(root);
            textView = (TextView) root.findViewById(R.id.nodata_tv);
        }
    }
}
