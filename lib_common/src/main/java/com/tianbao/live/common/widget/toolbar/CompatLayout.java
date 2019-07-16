package com.tianbao.live.common.widget.toolbar;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.tianbao.live.common.util.AdaptScreenUtils;
import com.tianbao.live.common.util.StatusBarUtils;

/**
 * Created by xiaoyanger on 2017/3/1.
 * 沉浸式、版本兼容的Toolbar，状态栏透明.
 */
public class CompatLayout extends View {

    private int compatPadingTop;

    public CompatLayout(Context context) {
        this(context, null);
    }

    public CompatLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setup();
    }

    public CompatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup() {
        compatPadingTop = 0;
        // android 4.4以上将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            compatPadingTop = SystemUtil.px2dp(StatusBarUtils.getStatusBarHeight(getContext()));
            compatPadingTop = AdaptScreenUtils.px2Pt(StatusBarUtils.getStatusBarHeight(getContext()));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightSize = compatPadingTop;
        }
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            heightSize = compatPadingTop;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize =compatPadingTop;
        }
        int maxHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        super.onMeasure(widthMeasureSpec, maxHeightMeasureSpec);
    }

}