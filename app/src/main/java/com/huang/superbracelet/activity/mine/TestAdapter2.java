package com.huang.superbracelet.activity.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.huang.bean.User;
import com.huang.superbracelet.base.BaseRVAdapter;

import java.util.List;

/**
 * Created by 黄家远 on 16/5/4.
 */
public class TestAdapter2 extends BaseRVAdapter<TestAdapter2.MyViewHolder,User> {


    public TestAdapter2(Context context, List<User> datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
