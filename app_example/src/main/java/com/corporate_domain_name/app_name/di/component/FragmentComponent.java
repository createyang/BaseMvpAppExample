package com.corporate_domain_name.app_name.di.component;

import android.app.Activity;


import com.corporate_domain_name.app_name.componet.di.module.FragmentModule;
import com.corporate_domain_name.app_name.componet.di.scope.FragmentScope;
import com.corporate_domain_name.app_name.ui.main.MessageTabFragment;
import com.corporate_domain_name.app_name.ui.main.MyTabFragment;
import com.corporate_domain_name.app_name.ui.main.OrderTabFragment;

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
