package com.corporate_domain_name.app_name.base.contract.main;

import com.corporate_domain_name.app_name.componet.base.BasePresenter;
import com.corporate_domain_name.app_name.componet.base.BaseView;

/**
 * @author: Caoy
 * @created on: 2019/7/8 10:50
 * @description:
 */
public interface MessageContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
