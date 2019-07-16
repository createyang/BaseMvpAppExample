package com.tianbao.live.ui.main;

import android.os.Bundle;
import android.widget.TextView;

import com.tianbao.tblive.R;
import com.tianbao.live.common.base.SimpleFragment;
import com.tianbao.live.common.util.ShareUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Caoy
 */
public class MyTabFragment extends SimpleFragment {

    @BindView(R.id.tv_my)
    TextView tvMy;
    Unbinder unbinder;

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        MyTabFragment fragment = new MyTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_tab;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.tv_my)
    public void onViewClicked() {
        ShareUtil.shareText(getContext(),"www.baidu.com","分享app");
    }
}
