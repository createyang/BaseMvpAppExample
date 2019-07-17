package com.tianbao.tclive.model.http.api;

import com.tianbao.tclive.componet.BuildConfig;
import com.tianbao.tclive.componet.model.bean.VersionBean;
import com.tianbao.tclive.componet.model.http.response.MyHttpResponse;
import com.tianbao.tclive.componet.model.bean.LoginResultBean;
import com.tianbao.tclive.model.bean.SignKeyBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by codeest on 16/10/10.
 * https://github.com/codeestX/my-restful-api
 */

public interface TBLiveApis {

    String HOST = BuildConfig.HOST_URL;

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo();

    /**
     * 登录
     * @return
     */
    @POST("user/login")
    Flowable<MyHttpResponse<LoginResultBean>> postLogin(@Body RequestBody route);

    @POST("startup/getsignkey")
    Flowable<MyHttpResponse<SignKeyBean>> fetchNetSingKey(@Body RequestBody requestBody);
}
