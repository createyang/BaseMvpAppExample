package com.corporate_domain_name.app_name.persenter.main;

import com.corporate_domain_name.live.lib_comlive.TCHTTPMgr;
import com.corporate_domain_name.live.lib_comlive.TCUserMgr;
import com.corporate_domain_name.live.lib_comlive.demo.TCUtils;
import com.corporate_domain_name.app_name.common.app.Constants;
import com.corporate_domain_name.app_name.common.util.Md5Utils;
import com.corporate_domain_name.app_name.componet.model.http.response.MyHttpResponse;
import com.corporate_domain_name.app_name.componet.rx.CommonSubscriber;
import com.corporate_domain_name.app_name.componet.rx.RxPresenter;
import com.corporate_domain_name.app_name.componet.rx.RxUtil;
import com.corporate_domain_name.app_name.base.contract.main.LoginContract;
import com.corporate_domain_name.app_name.model.DataManager;
import com.corporate_domain_name.app_name.model.bean.SignKeyBean;

import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2018/12/29 10:04
 * @description: 登录界面控制类
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSingKey() {
        String md5Encode = Md5Utils.Md5Encode("appId:" + Constants.Key.APP_ID + "appKey:" + Constants.Key.APP_KEY);
        addSubscribe(dataManager.fetchNetSingKey(md5Encode)
                .compose(RxUtil.<MyHttpResponse<SignKeyBean>>rxSchedulerHelper())
                .compose(RxUtil.<SignKeyBean>handleMyResult())
                .subscribeWith(
                        new CommonSubscriber<SignKeyBean>(mView) {
                            @Override
                            public void onNext(SignKeyBean commonProcessBean) {
//                                dataManager.setSignKey(commonProcessBean.getSignKey());
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        }
                ));
    }


    /**
     * 登录
     *
     * @param userId
     * @param password 密码
     */
    @Override
    public void loginInternal(final String userId, String password) {
        final String pwd = TCUtils.md5(TCUtils.md5(password) + userId);
        HashMap<String, String> parMap = new HashMap<>();
        parMap.put("mobile", "");
        parMap.put("code", "");
        String json = dataManager.getGson().toJson(parMap);
        TCUserMgr.getInstance().login(userId, password, new TCHTTPMgr.Callback() {
            @Override
            public void onSuccess(JSONObject data) {

            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });

//        addSubscribe(
//                dataManager.postLogin(json)
//                        .compose(RxUtil.<MyHttpResponse<LoginResultBean>>rxSchedulerHelper())
//                        .compose(RxUtil.<LoginResultBean>handleMyResult())
//                        .subscribeWith(new CommonSubscriber<LoginResultBean>(mView) {
//                            @Override
//                            public void onNext(LoginResultBean loginResultBean) {
                                //1. 从后台获取usersig ，通过账号密码
//                                if (!TextUtils.isEmpty(iphoneStr)) {
////                                    dataManager.setMobile(iphoneStr);
//                                }
//                                if (!TextUtils.isEmpty(loginResultBean.getToken())) {
////                                    dataManager.setToken(loginResultBean.getToken());
//                                }
//                                if (!TextUtils.isEmpty(loginResultBean.getUserId() + "")) {
////                                    dataManager.setUserId(loginResultBean.getUserId() + "");
//                                }
////                                dataManager.setLoginState(true);
////                                dataManager.setUserInfoJson(RequestUtil.gson.toJson(loginResultBean));

//                                // 登录到 MLVB 组件
//
//                                mView.loginSuccess(loginResultBean);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                super.onError(e);
//                                mView.loginFailure();
//                            }
//                        })
//        );
    }




    public void getCheckCode(String iphoneStr) {
        HashMap<String, String> hashMap = new HashMap<>(2);
        hashMap.put("mobile", iphoneStr);
        hashMap.put("codeType", "2");
//        RequestBody requestBody = RequestUtil.getRequestBody(hashMap);
//        addSubscribe(dataManager.fetchCheckCode(requestBody)
//                .compose(RxUtil.<CheckPostBean>rxSchedulerHelper())
//                .subscribe(new Consumer<CheckPostBean>() {
//                    @Override
//                    public void accept(CheckPostBean checkPostBean) throws Exception {
//                        RxToast.success("checkPostBean");
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        RxToast.error("throwable");
//                    }
//                })
//        );
    }


}
