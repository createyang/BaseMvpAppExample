package com.tianbao.tclive.ui.main;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianbao.tclive.R;
import com.tianbao.tclive.common.util.AdaptScreenUtils;
import com.tianbao.tclive.componet.ImageLoader;
import com.tianbao.tclive.componet.model.bean.SplashBean;
import com.tianbao.tclive.base.BaseActivity;
import com.tianbao.tclive.base.contract.main.SplashContract;
import com.tianbao.tclive.persenter.main.SplashPresenter;

import butterknife.BindView;

/**
 * @author: Caoy
 * @created on: 2018/12/27 15:15
 * @description: 欢迎界面
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_author)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        initStatus();
//      mPresenter.getSplashData();
        mPresenter.getTinkerPatch();
        mPresenter.startCountDown();
    }

    private void initStatus() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void showContent(SplashBean mList) {
        ImageLoader.load(this, mList.getImg(), ivWelcomeBg);
        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        tvWelcomeAuthor.setText(mList.getText());
    }

    @Override
    public void jumpToMain() {
        gotoActivity(MainActivity.class);
    }

    @Override
    public void jumpToLogin() {
        gotoActivity(LoginActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptHeight(super.getResources(), 1334);
    }

}
