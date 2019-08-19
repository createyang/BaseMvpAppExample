package com.corporate_domain_name.app_name.di.component;


import com.corporate_domain_name.app_name.app.TBLiveApplication;
import com.corporate_domain_name.app_name.di.module.AppModule;
import com.corporate_domain_name.app_name.model.DataManager;
import com.corporate_domain_name.app_name.componet.model.db.RealmHelper;
import com.corporate_domain_name.app_name.model.http.RetrofitHelper;
import com.corporate_domain_name.app_name.componet.model.prefs.ImplPreferencesHelper;
import com.corporate_domain_name.app_name.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    TBLiveApplication getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类

    OkHttpClient getOkHttpClient();
}
