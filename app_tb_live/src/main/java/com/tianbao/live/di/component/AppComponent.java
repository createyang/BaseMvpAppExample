package com.tianbao.live.di.component;


import com.tianbao.live.app.TBApplication;
import com.tianbao.live.di.module.AppModule;
import com.tianbao.live.model.DataManager;
import com.tianbao.live.componet.model.db.RealmHelper;
import com.tianbao.live.model.http.RetrofitHelper;
import com.tianbao.live.componet.model.prefs.ImplPreferencesHelper;
import com.tianbao.live.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    TBApplication getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类

    OkHttpClient getOkHttpClient();
}
