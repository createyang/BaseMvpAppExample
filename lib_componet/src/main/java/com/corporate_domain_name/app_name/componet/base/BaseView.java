package com.corporate_domain_name.app_name.componet.base;

/**
 * Created by codeest on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
