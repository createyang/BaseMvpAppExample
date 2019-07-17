package com.tianbao.tclive.persenter.main;

import com.tianbao.tclive.model.DataManager;
import com.tianbao.tclive.componet.rx.RxPresenter;
import com.tianbao.tclive.base.contract.main.OrderContract;

import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2019/7/8 10:51
 * @description:
 */
public class MessagePresenter extends RxPresenter<OrderContract.View> implements OrderContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public MessagePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }
}
