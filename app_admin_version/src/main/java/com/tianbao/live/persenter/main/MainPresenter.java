package com.tianbao.live.persenter.main;


import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tianbao.live.componet.rx.RxPresenter;
import com.tianbao.live.model.DataManager;
import com.tianbao.live.base.contract.main.MainContract;

import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2019/7/4 17:12
 * @description:
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }

    @Override
    public void checkPermissions(RxPermissions rxPermissions) {

    }

    @Override
    public boolean checkUser() {
        return false;
    }


}
