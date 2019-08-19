package com.corporate_domain_name.app_name.di.component;

import android.app.Activity;

import com.corporate_domain_name.app_name.componet.di.module.ActivityModule;
import com.corporate_domain_name.app_name.componet.di.scope.ActivityScope;
import com.corporate_domain_name.app_name.ui.main.LoginActivity;
import com.corporate_domain_name.app_name.ui.main.MainActivity;
import com.corporate_domain_name.app_name.ui.main.SplashActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

//
//    void inject(ThemeActivity themeActivity);
//
//    void inject(SectionActivity sectionActivity);
//
//    void inject(RepliesActivity repliesActivity);
//
//    void inject(NodeListActivity nodeListActivity);
}
