package com.corporate_domain_name.app_name.common.widget.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.corporate_domain_name.app_name.common.R;
import com.corporate_domain_name.app_name.common.util.AdaptScreenUtils;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

    private TextView mTvUnreadCount;
    private boolean isSingleTextSelector;

    public BottomBarTab(Context context, @DrawableRes int icon, CharSequence title) {
        this(context, null, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, CharSequence title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title, attrs);
    }

    private void init(Context context, int icon, CharSequence title, AttributeSet attrs) {
        mContext = context;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomBarTab, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.common_tab_bottom, this, true);
//        View view = inflate(context, R.layout.common_tab_bottom, this);
//        View view = LayoutInflater.from(context).inflate(R.layout.common_tab_bottom,this);
        TypedArray typedArray2 = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray2.getDrawable(0);
        if (drawable != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        }

        mIcon = (ImageView) view.findViewById(R.id.iv_Icon);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title_c);
        mTvUnreadCount = (TextView) view.findViewById(R.id.tv_unread_count);
        if (icon == 0) {
            isSingleTextSelector = true;
            mIcon.setVisibility(GONE);
            mTvTitle.setTextSize( AdaptScreenUtils.px2Pt(16));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.c_ff3333));
        } else {
            isSingleTextSelector = false;
            mIcon.setVisibility(VISIBLE);
            mIcon.setImageResource(icon);
        }

        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        mTvTitle.setText(title);
        mTvUnreadCount.setVisibility(INVISIBLE);

        typedArray.recycle();
        typedArray2.recycle();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (isSingleTextSelector) {
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.c_ff3333));
        } else {
            if (selected) {
                mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.c_ff3333));
                mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.common_tab_select));
            } else {
                mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
                mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
            }
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }

    /**
     * 设置未读数量
     */
    public void setUnreadCount(int num) {
        if (num <= 0) {
            mTvUnreadCount.setText(String.valueOf(0));
            mTvUnreadCount.setVisibility(GONE);
        } else {
            mTvUnreadCount.setVisibility(VISIBLE);
            if (num > 99) {
                mTvUnreadCount.setText("99+");
            } else {
                mTvUnreadCount.setText(String.valueOf(num));
            }
        }
    }

    /**
     * 获取当前未读数量
     */
    public int getUnreadCount() {
        int count = 0;
        if (TextUtils.isEmpty(mTvUnreadCount.getText())) {
            return count;
        }
        if ("99+".equals(mTvUnreadCount.getText().toString())) {
            return 99;
        }
        try {
            count = Integer.valueOf(mTvUnreadCount.getText().toString());
        } catch (Exception ignored) {
        }
        return count;
    }


    public String getTitle() {
        String title = "";
        if (mTvTitle != null) {
            title = mTvTitle.getText().toString();
        }
        return title;
    }
}
