package com.corporate_domain_name.app_name.common.widget.toolbar;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.corporate_domain_name.app_name.common.util.AdaptScreenUtils;
import com.corporate_domain_name.app_name.common.util.StatusBarUtils;


/**
 * Created by xiaoyanger on 2017/3/1.
 * 沉浸式、版本兼容的Toolbar，状态栏透明.
 */
public class CompatToolbar extends Toolbar {

    public CompatToolbar(Context context) {
        this(context, null);
    }

    public CompatToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompatToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setup();
    }

    public void setup() {
        int compatPadingTop = 0;
        // android 4.4以上将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            compatPadingTop = SystemUtil.px2dp(StatusBarUtils.getStatusBarHeight(getContext()));
            compatPadingTop =AdaptScreenUtils.px2Pt(StatusBarUtils.getStatusBarHeight(getContext()));
        }
        this.setPadding(getPaddingLeft(), getPaddingTop() + compatPadingTop, getPaddingRight(), getPaddingBottom());

    }

}