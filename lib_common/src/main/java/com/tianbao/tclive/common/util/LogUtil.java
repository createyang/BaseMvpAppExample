package com.tianbao.tclive.common.util;

import com.orhanobut.logger.Logger;
import com.tianbao.tclive.common.BuildConfig;

public class LogUtil {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = "com.tianbao.live";

    public static void e(Object o) {
        LogUtil.e(TAG, o);
    }

    public static void e(String tag, Object o) {
        if (isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(String tag, Object o, Throwable throwable) {
        if (isDebug) {
            Logger.e(throwable, tag, o);
        }
    }


    public static void w(String tag, Object o) {
        if (isDebug) {
            Logger.w(tag, o);
        }
    }



    public static void w(Object o) {
        LogUtil.w(TAG, o);
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg);
        }
    }

    public static void d(String message, Object... args) {
        if (isDebug) {
            Logger.d(message, args);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg);
        }
    }

    public static void i(String msg, Object... args) {
        if (isDebug) {
            Logger.i(msg, args);
        }
    }
}
