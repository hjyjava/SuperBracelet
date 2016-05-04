package com.huang.superbracelet.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/5/4.
 */
public abstract class BaseRVAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter{

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List mDatas = new ArrayList();

    public BaseRVAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
