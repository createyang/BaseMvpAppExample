package com.tianbao.live.base.contract.main;

import com.tianbao.live.componet.base.BasePresenter;
import com.tianbao.live.componet.base.BaseView;
import com.tianbao.live.componet.model.bean.SplashBean;


/**
 * @author: Caoy
 * @created on: 2018/12/27 15:15
 * @description: 欢迎界面显示和控制类接口
 */

public interface SplashContract {

    interface View extends BaseView {
        /**
         * 显示欢迎数据
         *
         * @param splashBean
         */
        void showContent(SplashBean splashBean);

        /**
         * 跳转到主界面
         */
        void jumpToMain();

        /**
         * 跳转到登录界面
         */
        void jumpToLogin();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取tinker热修复补丁
         */
        void getTinkerPatch();

        /**
         * 获取欢迎数据
         */
        void getSplashData();

        /**
         * 计时延迟跳转界面
         */
        void startCountDown();
    }

}
