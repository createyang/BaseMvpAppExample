package com.corporate_domain_name.app_name.model.http;


import com.google.gson.Gson;
import com.corporate_domain_name.app_name.componet.model.bean.VersionBean;
import com.corporate_domain_name.app_name.componet.model.http.response.MyHttpResponse;
import com.corporate_domain_name.app_name.componet.model.bean.LoginResultBean;
import com.corporate_domain_name.app_name.model.bean.SignKeyBean;
import com.corporate_domain_name.live.lib_comlive.demo.TestStreamBean;
import com.corporate_domain_name.app_name.model.http.api.TBLiveApis;

import javax.inject.Inject;
import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {
    private TBLiveApis tbLiveApis;
    private Gson mGson;
    private RequestBody mRequestBody;

    @Inject
    public RetrofitHelper(TBLiveApis tbLiveApis) {
        this.tbLiveApis = tbLiveApis;
    }

    @Override
    public Gson getGson() {
        if (mGson == null) {
            this.mGson = new Gson();
        }
        return mGson;
    }

    @Override
    public RequestBody getRequestBody(String json) {
        if (mRequestBody == null) {
            mRequestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), json);
        }
        return mRequestBody;
    }

    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return tbLiveApis.fetchVersionInfo();
    }

    @Override
    public Flowable<MyHttpResponse<LoginResultBean>> postLogin(String json) {
        return tbLiveApis.postLogin(getRequestBody(json));
    }

    @Override
    public Flowable<MyHttpResponse<SignKeyBean>> fetchNetSingKey(String sign) {
        return tbLiveApis.fetchNetSingKey(getRequestBody(sign));
    }

    @Override
    public Flowable<TestStreamBean> fetchTestStream() {
        return tbLiveApis.fetchTestStream();
    }




}
