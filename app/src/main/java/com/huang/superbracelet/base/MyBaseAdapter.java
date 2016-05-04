package com.huang.superbracelet.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/4/25.
 */
public abstract class MyBaseAdapter<VH extends BaseViewHoler, T> extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List mDatas = new ArrayList();

    public MyBaseAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH viewHolder;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    protected abstract VH onCreateViewHolder(ViewGroup parent);

    protected abstract void onBindViewHolder(VH vh, int position);

//    public class ViewHolder {
//        protected View root;
//
//        public ViewHolder(int layout_id) {
//            root = mInflater.inflate(layout_id, null);
//        }
//    }
}
