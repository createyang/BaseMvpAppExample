package com.corporate_domain_name.app_name.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corporate_domain_name.app_name.common.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author: Caoy
 * @created on: 2019/1/2 19:29
 * @description:
 */
public abstract class SimpleFragment extends SwipeBackFragment {

    private View view;
    public boolean isInited;
    private Unbinder unbinder;
    protected Activity mActivity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), null);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setSwipeBackEnable(false);
        initView();
    }

    public void setToolBar(String s, String s1, Toolbar toolbar) {
        if (getView() == null) return;
        TextView title1 = (TextView) getView().findViewById(R.id.tv_title_c);
        if (title1 != null) {
            title1.setText(s);
        }

        TextView title2 = (TextView) getView().findViewById(R.id.tv_title_r);
        if (title2 != null) {
            if (s1.isEmpty()) {
                title2.setVisibility(View.GONE);
            } else {
                title2.setVisibility(View.VISIBLE);
                title2.setText(s1);
            }
        }

        if (toolbar != null) {
            initToolbarNav(toolbar);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
    }

    protected void initToolbarNav(Toolbar toolbar) {
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.btn_return);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initEventAndData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract void initView();

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();


}
