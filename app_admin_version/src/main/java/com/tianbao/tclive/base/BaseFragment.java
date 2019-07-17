package com.tianbao.tclive.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.tianbao.tclive.common.base.SimpleFragment;
import com.tianbao.tclive.common.widget.SnackbarUtil;
import com.tianbao.tclive.componet.base.BasePresenter;
import com.tianbao.tclive.componet.base.BaseView;
import com.tianbao.tclive.componet.di.module.FragmentModule;
import com.tianbao.tclive.app.TBApplication;
import com.tianbao.tclive.di.component.DaggerFragmentComponent;
import com.tianbao.tclive.di.component.FragmentComponent;

import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2019/1/2 19:27
 * @description:
 */
public abstract class BaseFragment<P extends BasePresenter> extends SimpleFragment implements BaseView {
    public P getmPresenter() {
        return mPresenter;
    }

    @Inject
    protected P mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(TBApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }


    @Override
    public void stateError() {
    }

    @Override
    public void stateEmpty() {
    }

    @Override
    public void stateLoading() {
    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();

    @Override
    public void useNightMode(boolean isNight) {

    }
}
