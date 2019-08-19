package com.corporate_domain_name.app_name.model.http.api;

import com.corporate_domain_name.app_name.componet.BuildConfig;
import com.corporate_domain_name.app_name.componet.model.bean.VersionBean;
import com.corporate_domain_name.app_name.componet.model.http.response.MyHttpResponse;
import com.corporate_domain_name.app_name.componet.model.bean.LoginResultBean;
import com.corporate_domain_name.app_name.model.bean.SignKeyBean;
import com.corporate_domain_name.live.lib_comlive.demo.TestStreamBean;

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
    @POST("user/loginInternal")
    Flowable<MyHttpResponse<LoginResultBean>> postLogin(@Body RequestBody route);

    @POST("startup/getsignkey")
    Flowable<MyHttpResponse<SignKeyBean>> fetchNetSingKey(@Body RequestBody requestBody);

    @GET("utils/get_test_pushurl")
    Flowable<TestStreamBean> fetchTestStream();
}
