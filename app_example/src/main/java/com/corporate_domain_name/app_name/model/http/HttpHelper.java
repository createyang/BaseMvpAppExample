package com.corporate_domain_name.app_name.model.http;


import com.google.gson.Gson;
import com.corporate_domain_name.app_name.componet.model.bean.VersionBean;
import com.corporate_domain_name.app_name.componet.model.http.response.MyHttpResponse;
import com.corporate_domain_name.app_name.componet.model.bean.LoginResultBean;
import com.corporate_domain_name.app_name.model.bean.SignKeyBean;
import com.corporate_domain_name.live.lib_comlive.demo.TestStreamBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {
    Gson getGson();

    RequestBody getRequestBody(String json);

    Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo();

    Flowable<MyHttpResponse<LoginResultBean>> postLogin(String json);

    Flowable<MyHttpResponse<SignKeyBean>> fetchNetSingKey(String sign);

    Flowable<TestStreamBean> fetchTestStream();

}
