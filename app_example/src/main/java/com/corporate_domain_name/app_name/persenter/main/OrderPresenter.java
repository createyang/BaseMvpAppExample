package com.corporate_domain_name.app_name.persenter.main;

import com.corporate_domain_name.app_name.model.DataManager;
import com.corporate_domain_name.app_name.componet.rx.RxPresenter;
import com.corporate_domain_name.app_name.base.contract.main.OrderContract;

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
