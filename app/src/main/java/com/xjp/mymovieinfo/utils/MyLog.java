package com.xjp.mymovieinfo.utils;

import android.util.Log;

import com.xjp.mymovieinfo.BuildConfig;

/**
 * 打印日志的控制
 * User: xjp
 */
public class MyLog {

    private static boolean DEBUG = BuildConfig.DEBUG;

    /**
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
