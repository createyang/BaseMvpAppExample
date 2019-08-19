package com.corporate_domain_name.app_name.ui.main;

import android.os.Bundle;

import com.corporate_domain_name.app_name.R;
import com.corporate_domain_name.app_name.base.BaseFragment;
import com.corporate_domain_name.app_name.base.contract.main.OrderContract;
import com.corporate_domain_name.app_name.persenter.main.OrderPresenter;

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
