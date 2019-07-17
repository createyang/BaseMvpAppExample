package com.tianbao.tclive.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.tianbao.live.lib_comlive.LivePlayerActivity;
import com.tianbao.live.lib_comlive.TCCameraAnchorActivity;
import com.tianbao.live.lib_comlive.TCUtils;
import com.tianbao.tclive.R;
import com.tianbao.tclive.common.base.SimpleFragment;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * @author Caoy
 */
public class MyTabFragment extends SimpleFragment {
    @BindView(R.id.btn_create_live_room)
    Button btnCreateLiveRoom;
    @BindView(R.id.btn_enter_live_room)
    Button btnEnterLiveRoom;

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

    @OnClick({R.id.btn_create_live_room, R.id.btn_enter_live_room})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_live_room:
                startPublish();
                break;
            case R.id.btn_enter_live_room:
                startPlayer();
                break;
            default:
                break;
        }
    }

    /**
     * 发起拉流
     */
    private void startPlayer() {
        Intent intent = new Intent(getContext(), LivePlayerActivity.class);
        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * 发起推流
     */
    private void startPublish() {
        if (!TCUtils.checkRecordPermission(getActivity())) {
            return;
        }
        Intent intent = new Intent(getContext(), TCCameraAnchorActivity.class);
        if (intent != null) {
//            intent.putExtra(TCConstants.ROOM_TITLE,
//                    TextUtils.isEmpty(mTvTitle.getText().toString()) ? TCUserMgr.getInstance().getNickname() : mTvTitle.getText().toString());
//            intent.putExtra(TCConstants.USER_ID, TCUserMgr.getInstance().getUserId());
//            intent.putExtra(TCConstants.USER_NICK, TCUserMgr.getInstance().getNickname());
//            intent.putExtra(TCConstants.USER_HEADPIC, TCUserMgr.getInstance().getAvatar());
//            intent.putExtra(TCConstants.COVER_PIC, TCUserMgr.getInstance().getCoverPic());
//            intent.putExtra(TCConstants.USER_LOC,
//                    mTvLocation.getText().toString().equals(getString(R.string.text_live_lbs_fail)) ||
//                            mTvLocation.getText().toString().equals(getString(R.string.text_live_location)) ?
//                            getString(R.string.text_live_close_lbs) : mTvLocation.getText().toString());
            startActivity(intent);
//            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                for (int ret : grantResults) {
                    if (ret != PackageManager.PERMISSION_GRANTED) {
//                        showErrorAndQuit(-1314, "获取权限失败");
                        return;
                    }
                }
                startPublish();
                break;
            default:
                break;
        }
    }
}
