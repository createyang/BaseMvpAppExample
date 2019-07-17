package com.tianbao.tclive.di.component;

import android.app.Activity;


import com.tianbao.tclive.componet.di.module.FragmentModule;
import com.tianbao.tclive.componet.di.scope.FragmentScope;
import com.tianbao.tclive.ui.main.MessageTabFragment;
import com.tianbao.tclive.ui.main.MyTabFragment;
import com.tianbao.tclive.ui.main.OrderTabFragment;

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
