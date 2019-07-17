package com.tianbao.tclive.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.rtmp.TXLiveBase;
import com.tianbao.tclive.common.app.TBApplicationProxy;
import com.tianbao.tclive.di.component.AppComponent;
import com.tianbao.tclive.di.component.DaggerAppComponent;
import com.tianbao.tclive.di.module.AppModule;
import com.tianbao.tclive.di.module.HttpModule;

import io.realm.Realm;

/**
 * @author: Caoy
 * @created on: 2019/7/3 21:05
 * @description:
 */
public class TBLiveApplication extends Application {

    private static TBLiveApplication applicationInstance;
    public static AppComponent appComponent;

    /**
     * 获取到的 licence url
     */
    String licenceURL = "http://license.vod2.myqcloud.com/license/v1/a300508cf2a97f1ba5aa9f2a23ea6e58/TXLiveSDK.licence";

    /**
     * 获取到的 licence key
     */
    String licenceKey = "311b393827c52f734a2b3118d4b11462";

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(applicationInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationInstance = this;
        TBApplicationProxy.getInstance().init(this);
        //初始化数据库
        Realm.init(getApplicationContext());
        //配置 license 授权
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
