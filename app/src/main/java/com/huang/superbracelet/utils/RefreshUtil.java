package com.huang.superbracelet.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄家远 on 16/6/2.
 */
public class RefreshUtil {

    private LocalBroadcastManager localBroadcastManager;
    private static RefreshUtil intance;
    private Map<String ,UpdateReceiver> updateReceiverMap = new HashMap<>();
    private Map<String ,UpdateReceiverListener> updateReceiverListenerMap = new HashMap<>();

    public static RefreshUtil getIntance(Context context) {
        if (intance == null) {
            intance = new RefreshUtil(context);
        }
        return intance;
    }

    private RefreshUtil(Context context) {
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public void register(Class clz,UpdateReceiverListener updateReceiverListener){
        UpdateReceiver updateReceiver = new UpdateReceiver();
        String flag = updateFlag(clz);
        localBroadcastManager.registerReceiver(updateReceiver, new IntentFilter(flag));
        if(!updateReceiverMap.containsKey(flag)){
            updateReceiverMap.put(flag,updateReceiver);
        }
        if(!updateReceiverListenerMap.containsKey(flag)){
            updateReceiverListenerMap.put(flag,updateReceiverListener);
        }
    }

    public void unregister(Class clz){
        String flag = updateFlag(clz);
        localBroadcastManager.unregisterReceiver(updateReceiverMap.remove(flag));
        if(updateReceiverListenerMap.containsKey(flag)){
            updateReceiverListenerMap.remove(flag);
        }
    }

    private class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(updateReceiverListenerMap.containsKey(intent.getAction())){
                updateReceiverListenerMap.get(intent.getAction()).updateData();
            }
        }
    }

    public void send(Class clz){
        if(updateReceiverMap.containsKey(updateFlag(clz))){
            localBroadcastManager.sendBroadcast(new Intent(updateFlag(clz)));
        }
    }

    public interface UpdateReceiverListener {
        void updateData();
    }

    private String updateFlag(Class clz){
        return "update_"+clz.getSimpleName();
    }
}
