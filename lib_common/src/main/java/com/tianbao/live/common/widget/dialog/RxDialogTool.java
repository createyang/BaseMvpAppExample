package com.tianbao.live.common.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.tianbao.live.common.R;

/**
 * @author vondear
 * @date 2018/4/11 10:40:00
 */
public class RxDialogTool {

    /**
     * 加载进度 提示弹窗
     */
    @SuppressLint("StaticFieldLeak")
    private static RxDialogLoading mDialogLoading;


    /**
     * 网络请求加载框
     */
    public static void loadingHttp(Context context) {
        loadingHttp(context, "", false);
    }

    /**
     * 网络请求加载框
     */
    public static void loadingHttp(Context context, String hint) {
        loadingHttp(context, hint, false);
    }

    /**
     * 网络请求加载框
     *
     * @param context
     * @param hint    提示语
     */
    public static void loadingHttp(Context context, String hint, boolean backCancel) {
        if (mDialogLoading != null) {
            mDialogLoading.cancel();
        }
        mDialogLoading = new RxDialogLoading(context);
        mDialogLoading.setCanceledOnTouchOutside(false);
        mDialogLoading.setCancelable(backCancel);
        mDialogLoading.setLoadingColor(ContextCompat.getColor(context, R.color.c_ee4746));
        if (!TextUtils.isEmpty(hint)) {
            mDialogLoading.setLoadingText(hint);
        }
        if (!mDialogLoading.isShowing()) {
            mDialogLoading.show();
        }
    }

    /**
     * 网络请求加载框 取消
     */
    public static void loadingHttpCancel() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.cancel();
        }
    }

    /**
     * 网络请求加载框 取消
     */
    public static void loadingHttpCancel(String reminder) {
        if (mDialogLoading != null) {
            mDialogLoading.cancel(reminder);
        }
    }

}
