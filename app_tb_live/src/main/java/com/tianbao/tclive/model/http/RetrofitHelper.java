package com.tianbao.tclive.model.http;


import com.google.gson.Gson;
import com.tianbao.tclive.componet.model.bean.VersionBean;
import com.tianbao.tclive.componet.model.http.response.MyHttpResponse;
import com.tianbao.tclive.componet.model.bean.LoginResultBean;
import com.tianbao.tclive.model.bean.SignKeyBean;
import com.tianbao.tclive.model.http.api.TBLiveApis;

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


}
