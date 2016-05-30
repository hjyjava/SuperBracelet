package com.huang.superbracelet.activity.home.fragment;

import android.os.Bundle;

import com.huang.superbracelet.base.MyYListFragment;


/**
 *
 * Created by 黄家远 on 16/4/19.
 */
public class InfoFragment extends MyYListFragment {

//    private NewsInfoHttp newsInfoHttp;
//    private NewsInfoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        newsInfoHttp = new NewsInfoHttp(mContext);
    }

    public static InfoFragment newInstance(){
        InfoFragment infoFragment = new InfoFragment();
        return infoFragment;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        main_ylv.setDividerHeight(0);
    }

    @Override
    protected void realLoadData() {
//        newsInfoHttp.getDynamicList(userId, token, 1, mPage, new MyVolleyListener<List<Magazine>>() {
//            @Override
//            public void onSuccess(List<Magazine> magazineList) {
//                newList = magazineList;
//                loadSuccessful();
//                if(adapter == null){
//                    adapter = new NewsInfoAdapter(mContext,allList);
//                    main_ylv.setAdapter(adapter);
//                }
//                adapter.notifyDataSetChanged();
////                main_ylv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                    @Override
////                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                        Intent intent = new Intent(mContext, NewsInfoDetailsActivity.class);
////                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity());
////                        mContext.startActivity(intent,activityOptions.toBundle());
////                    }
////                });
//            }
//
//            @Override
//            public void onError(VolleyError volleyError) {
//                myHandler.sendEmptyMessage(1);
//            }
//        });
    }
}
