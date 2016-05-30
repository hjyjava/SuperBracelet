package com.huang.superbracelet.listener;

import android.view.View;

/**
 * Created by 黄家远 on 16/5/17.
 */
public interface RVItemListener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}
