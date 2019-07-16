package com.tianbao.live.base.contract.main;

import com.tianbao.live.componet.base.BasePresenter;
import com.tianbao.live.componet.base.BaseView;

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
