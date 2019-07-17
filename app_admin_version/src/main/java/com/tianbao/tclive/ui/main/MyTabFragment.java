package com.tianbao.tclive.ui.main;

import android.os.Bundle;

import com.tianbao.tclive.common.base.SimpleFragment;
import com.tianbao.tblive.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Caoy
 */
public class MyTabFragment extends SimpleFragment {

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



}
