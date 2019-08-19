package com.corporate_domain_name.app_name.ui.main;

import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.corporate_domain_name.app_name.common.widget.bottombar.BottomBar;
import com.corporate_domain_name.app_name.common.widget.bottombar.BottomBarTab;
import com.corporate_domain_name.app_name.R;
import com.corporate_domain_name.app_name.base.BaseActivity;
import com.corporate_domain_name.app_name.base.contract.main.MainContract;
import com.corporate_domain_name.app_name.persenter.main.MainPresenter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * @author Caoy
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;


    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.lay_main_top)
    LinearLayout layMainTop;
    @BindView(R.id.view_bottom_line)
    ImageView viewBottomLine;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private SupportFragment[] mFragments = new SupportFragment[3];

    @Override
    protected void initEventAndData() {
        //进入的动画
        setFragmentAnimator(new DefaultHorizontalAnimator());
        initBottomBar();
        MyTabFragment firstFragment = findFragment(MyTabFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = OrderTabFragment.newInstance();
            mFragments[SECOND] = MessageTabFragment.newInstance();
            mFragments[THIRD] = MyTabFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(OrderTabFragment.class);
            mFragments[THIRD] = findFragment(MessageTabFragment.class);
        }

        setCustomConfig();
        if (!TextUtils.isEmpty(TIMManager.getInstance().getLoginUser())) {
            // 如果账户不为空，就默认下次自动登录
//            handleLogin(true);
        }

    }

    private void initBottomBar() {
        bottomBar
                .addItem(new BottomBarTab(this, R.mipmap.icon_news, "订单"))
                .addItem(new BottomBarTab(this, R.mipmap.icon_news, "消息"/*getString(R.string.tab_workbench)*/))
                .addItem(new BottomBarTab(this, R.mipmap.icon_news, "我的"/*getString(R.string.tab_communication)*/));

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(title));
            }
        });
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void outLogin() {

    }

    private void setCustomConfig() {
        //注册IM事件回调，这里示例为用户被踢的回调，更多事件注册参考文档
        TUIKit.setIMEventListener(new IMEventListener() {
            @Override
            public void onForceOffline() {
                ToastUtil.toastLongMessage("您的帐号已在其它终端登录");
                login();
                finish();
            }

            @Override
            public void onDisconnected(int code, String desc) {
            }
        });
    }

    private void login() {

    }


}
