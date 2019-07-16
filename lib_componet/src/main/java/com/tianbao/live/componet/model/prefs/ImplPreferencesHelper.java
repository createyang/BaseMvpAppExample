package com.tianbao.live.componet.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.tianbao.live.common.app.TBApplicationProxy;

import javax.inject.Inject;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public class ImplPreferencesHelper implements PreferencesHelper {

    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = TBApplicationProxy.getInstanceApplication().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getLoginState() {
        return false;
    }

    @Override
    public String getMobile() {
        return null;
    }


}
