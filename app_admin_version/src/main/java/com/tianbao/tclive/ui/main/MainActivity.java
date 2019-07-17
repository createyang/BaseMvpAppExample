package com.tianbao.tclive.ui.main;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tianbao.tclive.common.widget.bottombar.BottomBar;
import com.tianbao.tclive.common.widget.bottombar.BottomBarTab;
import com.tianbao.tblive.R;
import com.tianbao.tclive.base.BaseActivity;
import com.tianbao.tclive.base.contract.main.MainContract;
import com.tianbao.tclive.persenter.main.MainPresenter;

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
}
