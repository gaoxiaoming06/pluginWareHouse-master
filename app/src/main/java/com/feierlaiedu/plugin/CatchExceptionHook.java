package com.feierlaiedu.plugin;

import android.util.Log;

import com.michael.pluginwarehouse.BuildConfig;

/**
 * 需要被asm注入的类，误删 {@link com.feierlaiedu.plugin.catchall.CatchAllMethodAdapter}
 */
public class CatchExceptionHook {

    private static final String TAG = CatchExceptionHook.class.getSimpleName();

    public static void handleException(Exception throwable) throws Throwable {
        if (!BuildConfig.DEBUG) {
            //debug包 抛出异常给CrashActivity展示
            Log.d(TAG, "call method error:", throwable);
            throwable.printStackTrace();
            throw throwable;
        } else {
            //上报bugly
            Log.d(TAG, "call method error: 上报", throwable);
//            CrashReport.postCatchedException(throwable);
        }
    }
}
