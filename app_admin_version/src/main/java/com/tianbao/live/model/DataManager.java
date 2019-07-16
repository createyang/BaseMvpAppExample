package com.tianbao.live.model;

import com.google.gson.Gson;
import com.tianbao.live.componet.model.bean.SplashBean;
import com.tianbao.live.componet.model.bean.VersionBean;
import com.tianbao.live.componet.model.db.DBHelper;
import com.tianbao.live.componet.model.http.response.MyHttpResponse;
import com.tianbao.live.componet.model.prefs.PreferencesHelper;
import com.tianbao.live.componet.model.bean.LoginResultBean;
import com.tianbao.live.model.bean.SignKeyBean;
import com.tianbao.live.model.http.HttpHelper;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Gson getGson() {
        return mHttpHelper.getGson();
    }

    @Override
    public RequestBody getRequestBody(String json) {
        return mHttpHelper.getRequestBody(json);
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return null;
    }

    @Override
    public Flowable<MyHttpResponse<LoginResultBean>> postLogin(String json) {
        return mHttpHelper.postLogin(json);
    }

    public Flowable<SplashBean> fetchSplashInfo(String res) {
        return null;
    }

    @Override
    public boolean getLoginState() {
        return false;
    }

    @Override
    public String getMobile() {
        return null;
    }

    @Override
    public Flowable<MyHttpResponse<SignKeyBean>> fetchNetSingKey(String sign) {
        return mHttpHelper.fetchNetSingKey(sign);
    }

}
