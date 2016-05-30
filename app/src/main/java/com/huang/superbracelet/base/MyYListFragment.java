package com.huang.superbracelet.base;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huang.superbracelet.R;
import com.huang.superbracelet.utils.MyDateUtils;
import com.huang.superbracelet.utils.NetUtils;
import com.huang.superbracelet.view.YListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/4/19.
 */
public abstract class MyYListFragment extends BaseFragment implements YListView.IXListViewListener {

    //主YListView
    protected YListView main_ylv;
    // 提示内容
    private ProgressBar progressBar;
    private RelativeLayout rl_nodata;
    //暂无数据
    private TextView none;

    //全部数据
    protected List allList = new ArrayList();
    //单页数据
    protected List newList = new ArrayList();

    protected int mPage = 1;
    //每页条数,子类可自定义
    protected int everyPageNum = 10;

    //更新数据系统广播管理
    protected LocalBroadcastManager broadcastManager;
    //更新广播
    protected MyUpdateRb myUpdateRb;

    //更新父标识
    protected static final String YLIST_UPDATE = "ylist.update.";
    //更新子标识
    protected String receiveFlag = "";

    protected Handler myHandler = new MyHandler();

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:// 没有网络
                    loadfailed(0);
                    break;
                case 1:// 获取失败
                    loadfailed(1);
                    break;
                case 2://删除成功
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                    gotoLoading();
                    loadData(1);
                    break;
                case 3://删除失败
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                    gotoLoading();
                    loadData(1);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        myUpdateRb = new MyUpdateRb();
        receiveFlag = YLIST_UPDATE;
    }

    @Override
    protected View buildMainView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.my_ylistview_layout, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gotoLoading();
        loadData(1);
    }

    @Override
    protected void initWidget() {
        main_ylv = (YListView) mainLayout.findViewById(R.id.main_ylv);
        rl_nodata = (RelativeLayout) mainLayout.findViewById(R.id.nodata_layout);
        progressBar = (ProgressBar) mainLayout.findViewById(R.id.loading_pb);
        none = (TextView) mainLayout.findViewById(R.id.nodata_tv);

        main_ylv.setXListViewListener(this);
    }

    /**
     * 加载失败
     * @param types
     */
    protected void loadfailed(int types) {//加载失败，加载圈隐藏，无数据显示
        main_ylv.setVisibility(View.GONE);
        rl_nodata.setVisibility(View.VISIBLE);
        none.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if (types == 0) {
            none.setText("检查网络！");
        } else {
            none.setText("暂无数据！");
        }
    }

    /**
     * 加载成功
     */
    protected void loadSuccessful() {//加载成功，加载圈隐藏，无数据隐藏
        rl_nodata.setVisibility(View.GONE);
        main_ylv.setVisibility(View.VISIBLE);
        if (mPage == 1) {
            allList.clear();
        }
        main_ylv.setRefreshTime(MyDateUtils.nowTimeFormat());
        main_ylv.stopRefresh();
        if (newList.size() < everyPageNum) {
            // "查看更多"无效
            main_ylv.setPullLoadEnable(false, true);
        } else {// 当前页的数据超过10条
            main_ylv.setPullLoadEnable(true, true);
        }
        allList.addAll(newList);
    }

    /**
     * 加载中，加载圈显示，无数据隐藏
     */
    protected void gotoLoading() {
        //全都隐藏，显示加载
        main_ylv.setVisibility(View.GONE);
        rl_nodata.setVisibility(View.VISIBLE);
        none.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 加载数据,先进行网路判断,具体加载交给子类
     * @param page 1为首次加载或刷新操作,大于1为加载更多
     */
    protected void loadData(int page) {
        if (!NetUtils.isConnected(mContext)) {// 没网络
            myHandler.sendEmptyMessage(0);
            if (page == 1) {
                main_ylv.stopRefresh();
            } else {
                main_ylv.stopLoadMore();
            }
            return;
        }
        mPage = page;
        realLoadData();
    }

    protected abstract void realLoadData();

    @Override
    public void onRefresh() {
        loadData(1);
    }

    @Override
    public void onLoadMore() {
        loadData(++mPage);
    }

    /**
     * 删除弹窗
     */
    protected void deleteDialogShow() {
        if(getActivity()==null)
            return;
        new AlertDialog.Builder(getActivity())
                .setTitle("是否删除所选内容？")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mOnDeleteListener != null) {
                            mOnDeleteListener.delete(true);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mOnDeleteListener != null) {
                            mOnDeleteListener.delete(false);
                        }
                    }
                }).show();
    }

    /**
     * 删除item接口
     */
    public interface OnDeleteListener {
        void delete(boolean success);
    }

    public OnDeleteListener mOnDeleteListener;

    protected void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }

    /**
     * 改变编辑按钮状态的接口
     */
    public interface OnEditStateListener {
        void state(TextState state);
    }

    protected OnEditStateListener mOnEditStateListener;

    public void setOnEditStateListener(OnEditStateListener onEditStateListener) {
        this.mOnEditStateListener = onEditStateListener;
    }

    public enum TextState {
        EDIT,
        DELETE,
    }

    /**
     * 更新广播
     */
    class MyUpdateRb extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            loadData(1);
        }
    }
}
