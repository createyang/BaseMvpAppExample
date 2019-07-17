package com.tianbao.tclive.di.component;


import com.tianbao.tclive.app.TBApplication;
import com.tianbao.tclive.di.module.AppModule;
import com.tianbao.tclive.model.DataManager;
import com.tianbao.tclive.componet.model.db.RealmHelper;
import com.tianbao.tclive.model.http.RetrofitHelper;
import com.tianbao.tclive.componet.model.prefs.ImplPreferencesHelper;
import com.tianbao.tclive.di.module.HttpModule;

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
