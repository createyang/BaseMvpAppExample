package com.tianbao.live.common.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Caoy
 * @created on: 2019/7/3 21:07
 * @description:
 */
public class TBApplicationProxy {
    private static TBApplicationProxy instance;
    private static Application tbApplication;
    private Set<Activity> allActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    public static synchronized Application getInstanceApplication() {
        return TBApplicationProxy.tbApplication;
    }

    private TBApplicationProxy() {
    }

    public static TBApplicationProxy getInstance() {
        if (instance == null) {
            synchronized (TBApplicationProxy.class) {
                if (instance == null) {
                    instance = new TBApplicationProxy();
                }
            }
        }
        return instance;
    }

//    public static AppComponent appComponent;
//    public static AppComponent getAppComponent() {
//        if (appComponent == null) {
//            appComponent = DaggerAppComponent.builder()
//                    .appModule(new AppModule(instance))
//                    .httpModule(new HttpModule())
//                    .build();
//        }
//        return appComponent;
//    }

    public void init(Application tbApplication) {
        TBApplicationProxy.tbApplication = tbApplication;
        getScreenSize();
        MultiDex.install(tbApplication);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) tbApplication.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

}
