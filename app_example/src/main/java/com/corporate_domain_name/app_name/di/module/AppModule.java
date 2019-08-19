package com.corporate_domain_name.app_name.di.module;

import com.corporate_domain_name.app_name.componet.model.db.DBHelper;
import com.corporate_domain_name.app_name.componet.model.db.RealmHelper;
import com.corporate_domain_name.app_name.componet.model.prefs.ImplPreferencesHelper;
import com.corporate_domain_name.app_name.componet.model.prefs.PreferencesHelper;
import com.corporate_domain_name.app_name.app.TBLiveApplication;
import com.corporate_domain_name.app_name.model.DataManager;
import com.corporate_domain_name.app_name.model.http.HttpHelper;
import com.corporate_domain_name.app_name.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: Caoy
 * @created on: 2018/12/26 17:29
 * @description:
 * Module是创建提供需要注入的对象的地方
 * AppModule是全局单例对象创建提供的地方
 * 创建提供的单例对象有
 * application
 * RetrofitHelper 改造请求助手
 * ImplPreferencesHelper sp数据文件
 */
@Module
public class AppModule {
    private TBLiveApplication application;

    public AppModule(TBLiveApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    TBLiveApplication provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, dbHelper, preferencesHelper);
    }

}
