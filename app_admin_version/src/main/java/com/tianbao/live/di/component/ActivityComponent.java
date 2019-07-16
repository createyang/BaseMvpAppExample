package com.tianbao.live.di.component;

import android.app.Activity;

import com.tianbao.live.componet.di.module.ActivityModule;
import com.tianbao.live.componet.di.scope.ActivityScope;
import com.tianbao.live.ui.main.LoginActivity;
import com.tianbao.live.ui.main.MainActivity;
import com.tianbao.live.ui.main.SplashActivity;

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
