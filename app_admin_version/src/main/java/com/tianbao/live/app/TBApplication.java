package com.tianbao.live.app;

import android.app.Application;

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
    }
}
