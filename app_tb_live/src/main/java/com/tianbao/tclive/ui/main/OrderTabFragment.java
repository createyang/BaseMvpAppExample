package com.tianbao.tclive.ui.main;

import android.os.Bundle;

import com.tianbao.tclive.R;
import com.tianbao.tclive.base.BaseFragment;
import com.tianbao.tclive.base.contract.main.OrderContract;
import com.tianbao.tclive.persenter.main.OrderPresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Caoy
 */
public class OrderTabFragment extends BaseFragment<OrderPresenter> implements OrderContract.View {

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        OrderTabFragment fragment = new OrderTabFragment();
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
        return R.layout.fragment_order_tab;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

}
