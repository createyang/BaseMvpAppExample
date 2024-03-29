package com.corporate_domain_name.app_name.base.contract.main;

import com.corporate_domain_name.app_name.componet.base.BasePresenter;
import com.corporate_domain_name.app_name.componet.base.BaseView;
import com.corporate_domain_name.app_name.componet.model.bean.LoginResultBean;

/**
 * @author: Caoy
 * @created on: 2018/12/28 11:04
 * @description: 登录界面显示和控制类
 */
public interface LoginContract {

    interface View extends BaseView {
        /**
         * 账号错误
         */
        void accountError();

        /**
         * 密码错误
         */
        void passwordError();

        /**
         * 登录成功显示
         *
         * @param loginResultBean
         */
        void loginSuccess(LoginResultBean loginResultBean);

        /**
         * 登录失败显示
         */
        void loginFailure();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取初始化数据 singkey
         */
        void getSingKey();

        /**
         * 登录
         *
         * @param iphoneStr 手机号
         * @param password  密码
         */
        void loginInternal(String iphoneStr, String password);
    }
}
