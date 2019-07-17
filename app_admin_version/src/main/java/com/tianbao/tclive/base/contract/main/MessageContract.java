package com.tianbao.tclive.base.contract.main;

import com.tianbao.tclive.componet.base.BasePresenter;
import com.tianbao.tclive.componet.base.BaseView;

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
