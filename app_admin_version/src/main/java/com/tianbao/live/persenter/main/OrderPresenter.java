package com.tianbao.live.persenter.main;

import com.tianbao.live.model.DataManager;
import com.tianbao.live.componet.rx.RxPresenter;
import com.tianbao.live.base.contract.main.OrderContract;

import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2019/7/8 10:51
 * @description:
 */
public class OrderPresenter extends RxPresenter<OrderContract.View> implements OrderContract.Presenter {
    private  DataManager mDataManager;

    @Inject
    public OrderPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }
}
