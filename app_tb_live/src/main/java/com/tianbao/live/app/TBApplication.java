package com.tianbao.live.app;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.tencent.rtmp.TXLiveBase;
import com.tianbao.live.common.app.TBApplicationProxy;
import com.tianbao.live.di.component.AppComponent;
import com.tianbao.live.di.component.DaggerAppComponent;
import com.tianbao.live.di.module.AppModule;
import com.tianbao.live.di.module.HttpModule;

import io.realm.Realm;

/**
 * @author: Caoy
 * @created on: 2019/7/3 21:05
 * @description:
 */
public class TBApplication extends Application {

    private static TBApplication applicationInstance;
    public static AppComponent appComponent;

    // 获取到的 licence url
    String licenceURL = "http://license.vod2.myqcloud.com/license/v1/5f12ce4062b0c2bcfc8551a686c202d2/TXLiveSDK.licence";
    // 获取到的 licence key
    String licenceKey = "7aec2714730956fc7a035fd18cb395eb";

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
}
