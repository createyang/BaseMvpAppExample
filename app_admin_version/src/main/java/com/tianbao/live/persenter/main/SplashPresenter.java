package com.tianbao.live.persenter.main;

import android.text.TextUtils;

import com.tianbao.live.model.DataManager;
import com.tianbao.live.componet.model.bean.SplashBean;
import com.tianbao.live.componet.rx.RxPresenter;
import com.tianbao.live.componet.rx.RxUtil;
import com.tianbao.live.base.contract.main.SplashContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * @author: Caoy
 * @created on: 2018/12/27 15:15
 * @description: 欢迎界面控制类
 */
public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {
    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 1000;

    private DataManager mDataManager;

    @Inject
    public SplashPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getTinkerPatch() {
        //加载tinker补丁包
//        String patchLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
//        File file = new File(patchLocation);
//        if (file.exists()) {
//            TinkerInstaller.onReceiveUpgradePatch(QrUDIApplicationLike.getInstanceApplication(), patchLocation);
////            file.delete();
////            ShareTinkerInternals.killAllOtherProcess(QrUDIApplicationLike.getInstanceApplication());
////            android.os.Process.killProcess(android.os.Process.myPid());
//        }

    }

    @Override
    public void getSplashData() {
        addSubscribe(mDataManager.fetchSplashInfo(RES)
                .compose(RxUtil.<SplashBean>rxSchedulerHelper())
                .subscribe(new Consumer<SplashBean>() {
                    @Override
                    public void accept(SplashBean splashBean) throws Exception {
                        mView.showContent(splashBean);
                        startCountDown();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        startCountDown();
                    }
                })
        );
    }

    @Override
    public void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        if (mDataManager.getLoginState() && !TextUtils.isEmpty(mDataManager.getMobile())) {
                            mView.jumpToMain();
                        } else {
                            mView.jumpToLogin();
                        }
                    }
                })
        );
    }
}
