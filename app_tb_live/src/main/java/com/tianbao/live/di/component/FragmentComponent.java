package com.tianbao.live.di.component;

import android.app.Activity;


import com.tianbao.live.componet.di.module.FragmentModule;
import com.tianbao.live.componet.di.scope.FragmentScope;
import com.tianbao.live.ui.main.MessageTabFragment;
import com.tianbao.live.ui.main.MyTabFragment;
import com.tianbao.live.ui.main.OrderTabFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(OrderTabFragment orderTabFragment);

    void inject(MessageTabFragment messageTabFragment);

    void inject(MyTabFragment myTabFragment);
}
