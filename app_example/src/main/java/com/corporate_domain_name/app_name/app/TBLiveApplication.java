package com.corporate_domain_name.app_name.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDex;

import com.tencent.imsdk.TIMBackgroundParam;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.session.SessionWrapper;
import com.tencent.qcloud.tim.uikit.ConfigHelper;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.rtmp.TXLiveBase;
import com.corporate_domain_name.app_name.common.app.TBApplicationProxy;
import com.corporate_domain_name.app_name.common.util.LogUtil;
import com.corporate_domain_name.app_name.di.component.AppComponent;
import com.corporate_domain_name.app_name.di.component.DaggerAppComponent;
import com.corporate_domain_name.app_name.di.module.AppModule;
import com.corporate_domain_name.app_name.di.module.HttpModule;

import java.util.List;

import io.realm.Realm;

/**
 * @author: Caoy
 * @created on: 2019/7/3 21:05
 * @description:
 */
public class TBLiveApplication extends Application {

    private static String TAG = "TBLiveApplication";
    private static TBLiveApplication applicationInstance;
    public static AppComponent appComponent;

    /**
     * 获取到的 licence url
     */
    String licenceURL = "";

    /**
     * 获取到的 licence key
     */
    String licenceKey = "";

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

        //判断是否是在主线程
        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            /**
             * TUIKit的初始化函数
             *
             * @param context  应用的上下文，一般为对应应用的ApplicationContext
             * @param sdkAppID 您在腾讯云注册应用时分配的sdkAppID
             * @param configs  TUIKit的相关配置项，一般使用默认即可，需特殊配置参考API文档
             */
            TUIKit.init(this, Constants.SDKAPPID, new ConfigHelper().getConfigs());

//            if (IMFunc.isBrandXiaoMi()) {
//                // 小米离线推送
//                MiPushClient.registerPush(this, Constants.XM_PUSH_APPID, Constants.XM_PUSH_APPKEY);
//            }
//            if (IMFunc.isBrandHuawei()) {
//                // 华为离线推送
//                HMSAgent.init(this);
//            }
//            if (MzSystemUtils.isBrandMeizu(this)) {
//                // 魅族离线推送
//                PushManager.register(this, Constants.MZ_PUSH_APPID, Constants.MZ_PUSH_APPKEY);
//            }
//            if (IMFunc.isBrandVivo()) {
//                // vivo离线推送
//                PushClient.getInstance(getApplicationContext()).initialize();
//            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                registerActivityLifecycleCallbacks(new StatisticActivityLifecycleCallback());
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    class StatisticActivityLifecycleCallback implements ActivityLifecycleCallbacks {
        private int foregroundActivities = 0;
        private boolean isChangingConfiguration;

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {
            foregroundActivities++;
            if (foregroundActivities == 1 && !isChangingConfiguration) {
                // 应用切到前台
                LogUtil.i(TAG, "application enter foreground");
                TIMManager.getInstance().doForeground(new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        LogUtil.e(TAG, "doForeground err = " + code + ", desc = " + desc);
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.i(TAG, "doForeground success");
                    }
                });
            }
            isChangingConfiguration = false;
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            foregroundActivities--;
            if (foregroundActivities == 0) {
                // 应用切到后台
                LogUtil.i(TAG, "application enter background");
                int unReadCount = 0;
                List<TIMConversation> conversationList = TIMManager.getInstance().getConversationList();
                for (TIMConversation timConversation : conversationList) {
                    unReadCount += timConversation.getUnreadMessageNum();
                }
                TIMBackgroundParam param = new TIMBackgroundParam();
                param.setC2cUnread(unReadCount);
                TIMManager.getInstance().doBackground(param, new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        LogUtil.e(TAG, "doBackground err = " + code + ", desc = " + desc);
                    }

                    @Override
                    public void onSuccess() {
                        LogUtil.i(TAG, "doBackground success");
                    }
                });
            }
            isChangingConfiguration = activity.isChangingConfigurations();
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }

}
