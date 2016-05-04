package com.huang.superbracelet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 黄家远 on 16/5/4.
 */
public class MyDateUtils {
    /**
     * get currentTime format "HH:mm:ss"
     * @return "HH:mm:ss"
     */
    public static String nowTimeFormat(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }
}
