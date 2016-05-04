package com.huang.superbracelet.base;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家远 on 16/4/25.
 */
public abstract class EditBaseAdapter extends MyBaseAdapter {

    protected int limitDeleteNum = -1;
    protected String receiveFlag = "";

    /**false：编辑，true：删除*/
    protected boolean isDelete;

    protected List<String> seletedIds = new ArrayList<>();

    public <T> EditBaseAdapter(Context context, List<T> datas) {
        super(context, datas);
    }


    public abstract void setisDelete(boolean b);
    public abstract List<String> getSeletedIds();

    protected void checkDelete(CheckBox check, String id) {
        if(seletedIds.contains(id)){
            check.toggle();
            seletedIds.remove(id);
        }else{
            if(limitDeleteNum==-1){
                check.toggle();
                seletedIds.add(id);
            }else{
                if (seletedIds.size()<limitDeleteNum){
                    check.toggle();
                    seletedIds.add(id);
                }else{
                    Toast.makeText(mContext, "一次最多只能删除"+limitDeleteNum+"条数据", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
