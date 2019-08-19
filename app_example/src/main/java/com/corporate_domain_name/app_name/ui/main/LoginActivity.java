package com.corporate_domain_name.app_name.ui.main;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.corporate_domain_name.app_name.common.util.AdaptScreenUtils;
import com.corporate_domain_name.app_name.common.widget.dialog.RxDialogTool;
import com.corporate_domain_name.app_name.R;
import com.corporate_domain_name.app_name.app.Constants;
import com.corporate_domain_name.app_name.base.BaseActivity;
import com.corporate_domain_name.app_name.base.contract.main.LoginContract;
import com.corporate_domain_name.app_name.componet.model.bean.LoginResultBean;
import com.corporate_domain_name.app_name.persenter.main.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: Caoy
 * @created on: 2018/12/27
 * @description: 登录界面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.ed_account)
    TextInputEditText edAccount;
    @BindView(R.id.il_account)
    TextInputLayout ilAccount;
    @BindView(R.id.ed_check_code)
    TextInputEditText edCheckCode;
    @BindView(R.id.il_check_code)
    TextInputLayout ilCheckCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.tv_user_protocol)
    TextView tvUserProtocol;
    @BindView(R.id.ed_password)
    TextInputEditText edPassword;
    @BindView(R.id.il_password)
    TextInputLayout ilPassword;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.rl_check_code)
    RelativeLayout rlCheckCode;
    @BindView(R.id.lay_user_protocol)
    LinearLayout layUserProtocol;

    private String iphoneStr;
    private String checkCode;
    private String password;
    private int loginWay;
    private static final int REQ_PERMISSION_CODE = 0x100;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptHeight(super.getResources(), 1334);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getSingKey();
        checkPermission(this);
    }

    //权限检查
    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                String[] permissionsArray = permissions.toArray(new String[1]);
                ActivityCompat.requestPermissions(activity,
                        permissionsArray, REQ_PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    /**
     * 系统请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.toastLongMessage("未全部授权，部分功能可能无法使用！");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void accountError() {
    }

    @Override
    public void passwordError() {
        RxDialogTool.loadingHttpCancel();
    }

    @Override
    public void loginSuccess(LoginResultBean loginResultBean) {
        RxDialogTool.loadingHttpCancel();
        startMainActivity(loginResultBean);
    }

    private void startMainActivity(LoginResultBean loginResultBean) {
        android.content.Intent intent = new android.content.Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra(Constants.Intent.IT_LOGIN_BEAN, loginResultBean);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void loginFailure() {
        RxDialogTool.loadingHttpCancel();
    }

    /**
     * 获取验证码
     */
    @OnClick(R.id.tv_get_code)
    public void onTvGetCodeClicked() {
        updateLoginInfo();
        if (!validateAccount(iphoneStr)) {
            return;
        }
        mPresenter.getCheckCode(iphoneStr);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        gotoActivity(MainActivity.class);
//        updateLoginInfo();
//        if (!validateAccount(iphoneStr)) {
//            return;
//        }
//        //密码登录
//        if (loginWay == 0) {
//            if (!validatePassword(password)) {
//                return;
//            }
//            RxDialogTool.loadingHttp(this, "正在登录...");
//            mPresenter.loginInternal(iphoneStr, password);
//
//            //验证码登录
//        } else if (loginWay == 1) {
//            if (!validateCheckCode(checkCode)) {
//                return;
//            }
//        }
    }

    @OnClick(R.id.tv_user_protocol)
    public void onTvUserProtocolClicked() {
//        android.content.Intent intent = new android.content.Intent();
//        intent.setClass(this, UseAgreementActivity.class);
//        startActivity(intent);
    }


    private void updateLoginInfo() {
        iphoneStr = ilAccount.getEditText().getText().toString();
        password = ilPassword.getEditText().getText().toString();
        checkCode = ilCheckCode.getEditText().getText().toString();
        loginWay = rlCheckCode.getVisibility() == View.VISIBLE ? 1 : 0;
    }

    /**
     * 显示错误提示，并获取焦点
     *
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    private boolean validateAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            showError(ilAccount, getString(R.string.login_iphone_null));
            return false;
        } else if (account.length() != 11) {
            showError(ilAccount, getString(R.string.login_iphone_hint));
            return false;
        } else {
            ilAccount.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * 验证验证码
     *
     * @param checkCode
     * @return
     */
    private boolean validateCheckCode(String checkCode) {
        if (TextUtils.isEmpty(checkCode)) {
            showError(ilCheckCode, getString(R.string.login_code_null));
            return false;
        }

        if (checkCode.length() != 6) {
            showError(ilCheckCode, getString(R.string.login_code_length));
            return false;
        }
        ilCheckCode.setErrorEnabled(false);
        return true;
    }

    /**
     * 验证密码
     *
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(ilPassword, getString(R.string.login_password_null));
            return false;
        }
        if (password.length() < 4) {
            showError(ilPassword, getString(R.string.login_password_length));
            return false;
        }
        ilPassword.setErrorEnabled(false);
        return true;
    }


}
