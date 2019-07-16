package com.tianbao.live.di.module;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianbao.live.common.BuildConfig;
import com.tianbao.live.common.adapter.ListNullAdapter;
import com.tianbao.live.common.adapter.LongDefaultAdapter;
import com.tianbao.live.common.adapter.StringNullAdapter;
import com.tianbao.live.common.app.Constants;
import com.tianbao.live.common.util.LogUtil;
import com.tianbao.live.common.util.SystemUtil;
import com.tianbao.live.componet.di.qualifier.TBLiveUrl;
import com.tianbao.live.model.http.api.TBLiveApis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Caoy
 * @created on: 2018/12/26 16:07
 * @description: HttpModule创建的对象有
 * <p>
 * OkHttpClient.Builder
 * OkHttpClient 使用okhttp框架进行网络请求 并配置相关请求参数
 * Retrofit.Builder
 * Retrofit 使用retrofit改造
 * MyApis
 */
@Module
public class HttpModule {

    private static Gson gson;
    private static int timeout = 10 * 6;

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        LogUtil.d("cacheFile 1: " + Constants.Path.PATH_NET_CACHE);
        File cacheFile = new File(Constants.Path.PATH_NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周 只支持get请求
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        }
//        设置统一的请求头部参数
//        builder.addInterceptor(apikey);

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        builder.writeTimeout(timeout, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 增加后台返回""和"null"的处理
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     *
     * @return
     */
    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(long.class, new LongDefaultAdapter())
                    .registerTypeAdapter(String.class, new StringNullAdapter())
                    .registerTypeHierarchyAdapter(List.class, new ListNullAdapter())

                    //序列化null
                    .serializeNulls()
                    // 设置日期时间格式，另有2个重载方法
                    // 在序列化和反序化时均生效
                    .setDateFormat("yyyy-MM-dd")
                    // 禁此序列化内部类
//                    .disableInnerClassSerialization()
                    //生成不可执行的Json（多了 )]}' 这4个字符）
                    .generateNonExecutableJson()
                    //禁止转义html标签
                    .disableHtmlEscaping()
                    //格式化输出
                    .setPrettyPrinting()
                    .create();
        }
        return gson;
    }

    @Singleton
    @Provides
    @TBLiveUrl
    Retrofit provideMyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, TBLiveApis.HOST);
    }

    @Singleton
    @Provides
    TBLiveApis provideMyService(@TBLiveUrl Retrofit retrofit) {
        return retrofit.create(TBLiveApis.class);
    }


}
