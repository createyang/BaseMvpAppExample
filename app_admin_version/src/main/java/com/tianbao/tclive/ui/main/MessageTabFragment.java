package com.tianbao.tclive.ui.main;

import android.os.Bundle;

import com.tianbao.tblive.R;
import com.tianbao.tclive.base.BaseFragment;
import com.tianbao.tclive.base.contract.main.MessageContract;
import com.tianbao.tclive.persenter.main.MessagePresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author: Caoy
 * @created on: 2019/7/8 11:19
 * @description:
 */
public class MessageTabFragment extends BaseFragment<MessagePresenter> implements MessageContract.View {

    public static SupportFragment newInstance() {
        Bundle args = new Bundle();
        MessageTabFragment fragment = new MessageTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message_tab;
    }
}
