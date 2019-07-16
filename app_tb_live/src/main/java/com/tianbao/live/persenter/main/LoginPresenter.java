package com.tianbao.live.persenter.main;

import com.tianbao.live.common.app.Constants;
import com.tianbao.live.common.util.Md5Utils;
import com.tianbao.live.componet.model.http.response.MyHttpResponse;
import com.tianbao.live.componet.rx.CommonSubscriber;
import com.tianbao.live.componet.rx.RxPresenter;
import com.tianbao.live.componet.rx.RxUtil;
import com.tianbao.live.base.contract.main.LoginContract;
import com.tianbao.live.model.DataManager;
import com.tianbao.live.componet.model.bean.LoginResultBean;
import com.tianbao.live.model.bean.SignKeyBean;
import java.util.HashMap;
import javax.inject.Inject;

/**
 * @author: Caoy
 * @created on: 2018/12/29 10:04
 * @description: 登录界面控制类
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

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


    @Override
    public void login(final String iphoneStr, String password) {
        HashMap<String, String> parMap = new HashMap<>();
        parMap.put("mobile","");
        parMap.put("code","");
        String json = dataManager.getGson().toJson(parMap);

        addSubscribe(
                dataManager.postLogin(json)
                        .compose(RxUtil.<MyHttpResponse<LoginResultBean>>rxSchedulerHelper())
                        .compose(RxUtil.<LoginResultBean>handleMyResult())
                        .subscribeWith(new CommonSubscriber<LoginResultBean>(mView) {
                            @Override
                            public void onNext(LoginResultBean loginResultBean) {
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
                                mView.loginSuccess(loginResultBean);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                mView.loginFailure();
                            }
                        })
        );
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
