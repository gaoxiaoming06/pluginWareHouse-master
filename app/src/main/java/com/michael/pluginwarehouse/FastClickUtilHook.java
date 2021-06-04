package com.michael.pluginwarehouse;

import android.util.Log;
import android.view.View;

public class FastClickUtilHook {

    static String TAG = "test";


    private static final int FAST_CLICK_TIME_DISTANCE = 1000;
    private static long sLastClickTime = 0;
    private static int viewHash = 0;

    public static boolean isFastDoubleClick(View view) {
        if (view == null) {
            return false;
        }
        Log.e(TAG, "view: " + view.toString());
        long time = System.currentTimeMillis();
        long timeDistance = time - sLastClickTime;
        sLastClickTime = time;
        if (viewHash != 0) {
            if (viewHash == view.hashCode()) {
                if(0 < timeDistance && timeDistance < FAST_CLICK_TIME_DISTANCE){
                    Log.e(TAG, "isFastDoubleClick: 拦截");
                    return true;
                }
            } else {
                viewHash = view.hashCode();
            }
        } else {
            viewHash = view.hashCode();
        }
        return false;
    }

}
