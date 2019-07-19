package com.tianbao.live.lib_comlive.demo;

import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tianbao.live.lib_comlive.R;

public class IMActivity extends AppCompatActivity {

    public static final int sdkAppId = 1;
    private String identifier = "test";
    private String userSig = "";
    public static final String TAG = "IMActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        initImSDK();

// identifier为用户名，userSig 为用户登录凭证
        TIMManager.getInstance().login(identifier, userSig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(TAG, "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.d(TAG, "login succ");
            }
        });


    }

    private void initImSDK() {
        //初始化 SDK 基本配置
        TIMSdkConfig config = new TIMSdkConfig(sdkAppId)
                // .setAccoutType(accountType)     // 该接口已废弃
                .enableLogPrint(true)              // 是否在控制台打印 Log?
                .setLogLevel(TIMLogLevel.DEBUG)    // Log 输出级别（debug 级别会很多）
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
        // Log 文件存放在哪里？
        //初始化 SDK
        TIMManager.getInstance().init(getApplicationContext(), config);
    }

    public void loginClick(View view) {
        // identifier为用户名，userSig 为用户登录凭证
        TIMManager.getInstance().login(identifier, userSig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(TAG, "login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.d(TAG, "login succ");
            }
        });
    }

    public void logoutClick(View view) {
//登出
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(TAG, "logout failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                //登出成功
                Log.d(TAG, "logout succ");
            }
        });
    }
}
