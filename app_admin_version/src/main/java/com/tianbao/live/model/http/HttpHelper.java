package com.tianbao.live.model.http;


import com.google.gson.Gson;
import com.tianbao.live.componet.model.bean.VersionBean;
import com.tianbao.live.componet.model.http.response.MyHttpResponse;
import com.tianbao.live.componet.model.bean.LoginResultBean;
import com.tianbao.live.model.bean.SignKeyBean;

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

}
