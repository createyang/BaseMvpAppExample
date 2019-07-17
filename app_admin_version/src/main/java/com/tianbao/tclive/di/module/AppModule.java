package com.tianbao.tclive.di.module;

import com.tianbao.tclive.componet.model.db.DBHelper;
import com.tianbao.tclive.componet.model.db.RealmHelper;
import com.tianbao.tclive.componet.model.prefs.ImplPreferencesHelper;
import com.tianbao.tclive.componet.model.prefs.PreferencesHelper;
import com.tianbao.tclive.app.TBApplication;
import com.tianbao.tclive.model.DataManager;
import com.tianbao.tclive.model.http.HttpHelper;
import com.tianbao.tclive.model.http.RetrofitHelper;

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
    private  TBApplication application;

    public AppModule(TBApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    TBApplication provideApplicationContext(){
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
