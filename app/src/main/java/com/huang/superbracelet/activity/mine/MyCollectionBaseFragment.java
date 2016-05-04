package com.huang.superbracelet.activity.mine;

import android.os.Bundle;
import android.widget.Toast;

import com.huang.superbracelet.base.EditBaseAdapter;
import com.huang.superbracelet.base.MyYListFragment;
import com.huang.superbracelet.utils.NetUtils;

import java.util.List;

/**
 *
 * Created by 黄家远 on 16/4/19.
 */
public class MyCollectionBaseFragment extends MyYListFragment {

    protected CollectionDataType mCollectionDataType = CollectionDataType.ESSAY;
    protected EditBaseAdapter adapter;
//    private MyCollectionBusiness myCollectionBusiness;
    protected TextState mEditSate = TextState.EDIT;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mOnEditStateListener != null) {
                mOnEditStateListener.state(mEditSate);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        myCollectionBusiness = MyCollectionBusinessImpl.getInstance(context);
        setOnLoadDataListener();
        everyPageNum = 10;

    }

    @Override
    protected void loadData(int page) {
        super.loadData(page);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getDataByType();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (newList != null && newList.size() > 0) {
                    myHandler.sendEmptyMessage(1);
                } else {
                    myHandler.sendEmptyMessage(0);
                }
            }
        });
        thread.start();
    }

    private void setOnLoadDataListener() {
        super.setOnLoadDataListener(new OnLoadDataListener() {
            @Override
            public void isSuccess(boolean b) {
                if (b) {
                    if (adapter == null) {
                        buildAdapterByType();
                        main_ylv.setAdapter(adapter);
                    }
                    resetAdapter();
                } else {
//                    mTextSate = TextState.GONE;
                }
            }

            @Override
            public void toLoadData(int page) {
                loadData(page);
            }
        });
    }

    public void clickEdit() {
        if (adapter == null || allList == null || allList.size() == 0)
            return;
        switch (mEditSate) {
            case EDIT:
                mEditSate = TextState.DELETE;
                if (mOnEditStateListener != null) {
                    mOnEditStateListener.state(mEditSate);
                }
                adapter.setisDelete(true);
                break;
            case DELETE:
                if (adapter.getSeletedIds().size() > 0) {
                    setOnDeleteListener(new OnDeleteListener() {
                        @Override
                        public void delete(boolean success) {
                            if (success) {
                                deleteItem(adapter.getSeletedIds());
                            }
                        }
                    });
                    deleteDialogShow();
                } else {
                    resetAdapter();
                }
                break;
            default:
                break;
        }
    }

    protected void resetAdapter() {
        if (adapter != null) {
            adapter.setisDelete(false);
        }
        if (mEditSate == TextState.DELETE) {
            mEditSate = TextState.EDIT;
            if (mOnEditStateListener != null) {
                mOnEditStateListener.state(mEditSate);
            }
        }
    }

    private void getDataByType() throws Exception {
        switch (mCollectionDataType) {
//            case ESSAY:
//                newList = myCollectionBusiness.getCollectionByType(userId, token, 0, mPage);
//                break;
//            case CASE:
//                newList = myCollectionBusiness.getCollectionByType(userId, token, 1, mPage);
//                break;
//            case TEST:
//                newList = myCollectionBusiness.getCollectionByType(userId, token, 2, mPage);
//                break;
            default:
                break;
        }
    }

    private void buildAdapterByType() {
        switch (mCollectionDataType) {
//            case ESSAY:
//                adapter = new MyCollectionEssayAdapter(getActivity(), allList);
//                break;
//            case CASE:
//                adapter = new MyCollectionCaseAdapter(userId, token, getActivity(), allList);
//                break;
//            case TEST:
//                adapter = new MedicalTCollectionAdapter(getActivity(), allList);
//                break;
            default:
                break;
        }
    }

    protected void deleteItem(final List<String> list) {
        if (NetUtils.isConnected(mContext)) {
            Toast.makeText(mContext, "请检查网络链接", Toast.LENGTH_SHORT).show();
        } else {
            gotoLoading();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int result = -1;
                    try {
                        result = deleteByType(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result == 0) {
                        myHandler.sendEmptyMessage(7);
                    } else {
                        myHandler.sendEmptyMessage(8);
                    }
                }
            }).start();
        }
    }

    private int deleteByType(List<String> list) {
        try {
            switch (mCollectionDataType) {
//                case ESSAY:
//                    return myCollectionBusiness.deleteMyCollectionToWeb(userId, token, list, 0);
//                case CASE:
//                    return myCollectionBusiness.deleteMyCollectionToWeb(userId, token, list, 1);
//                case TEST:
//                    return myCollectionBusiness.deleteMyCollectionToWeb(userId, token, list, 2);
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public enum CollectionDataType {
        //论坛
        ESSAY,
        //病例
        CASE,
        //检验
        TEST,
    }


    public boolean changeEditState() {
        if (adapter != null && mEditSate == MyYListFragment.TextState.DELETE) {
            mEditSate = TextState.EDIT;
            if (mOnEditStateListener != null) {
                mOnEditStateListener.state(mEditSate);
            }
            adapter.setisDelete(false);
            return true;
        }
        return false;
    }

}
