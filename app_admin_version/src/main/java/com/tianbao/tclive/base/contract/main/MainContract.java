package com.tianbao.tclive.base.contract.main;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tianbao.tclive.componet.base.BasePresenter;
import com.tianbao.tclive.componet.base.BaseView;

/**
 * @author: Caoy
 * @created on: 2018/12/28 11:04
 * @description:
 */
public interface MainContract {
    interface View extends BaseView {
//        void showUpdateDialog(VersionBean.AppVersionBean versionContent);

//        void startDownloadService();

        void outLogin();
    }

    interface Presenter extends BasePresenter<View> {
//        void checkVersion(String currentVersion);

        void checkPermissions(RxPermissions rxPermissions);

        boolean checkUser();

//        void setCurrentItem(int index);
//
//        int getCurrentItem();

//        void setVersionPoint(boolean b);
//
//        boolean getVersionPoint();
    }
}
