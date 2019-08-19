package com.corporate_domain_name.app_name.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.corporate_domain_name.app_name.app.TBLiveApplication;
import com.corporate_domain_name.app_name.common.base.SimpleActivity;
import com.corporate_domain_name.app_name.common.widget.SnackbarUtil;
import com.corporate_domain_name.app_name.componet.base.BasePresenter;
import com.corporate_domain_name.app_name.componet.base.BaseView;
import com.corporate_domain_name.app_name.componet.di.module.ActivityModule;
import com.corporate_domain_name.app_name.di.component.ActivityComponent;
import com.corporate_domain_name.app_name.di.component.DaggerActivityComponent;

import javax.inject.Inject;

/**
 * Created by codeest on 2016/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(TBLiveApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
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

}