package com.tianbao.live.lib_comlive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tianbao.tclive.common.util.LogUtil;

/**
 * @author: Caoy
 * @created on: 2019/7/16 15:19
 * @description: 1. 下载 SDK 开发包
 * 2. 给 SDK 配置 License 授权
 * 3. 初始化 TXLivePusher 组件
 * 4. 开启摄像头预览
 * 5. 启动和结束推流
 */
public class TCCameraAnchorActivity extends AppCompatActivity {
    private static final String TAG = "TCCameraAnchorActivity";
    /**
     * 此处填写您的 rtmp 推流地址
     */
    String rtmpURL = "rtmp://55940.livepush.myqcloud.com/live/test?txSecret=6e128a1c7a5658df2025106a3271bf3a&txTime=5D2F45FF";
    private TXLivePusher mLivePusher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tccamera_anchor);

        TXLivePushConfig mLivePushConfig = new TXLivePushConfig();
        mLivePusher = new TXLivePusher(this);
        // 一般情况下不需要修改 config 的默认配置
        mLivePusher.setConfig(mLivePushConfig);

        //启动本地摄像头预览
        TXCloudVideoView mPusherView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
        mLivePusher.startCameraPreview(mPusherView);

        int ret = mLivePusher.startPusher(rtmpURL.trim());
        if (ret == -5) {
            LogUtil.i("startRTMPPush: license 校验失败");
        }

        mLivePusher.setPushListener(new ITXLivePushListener() {
            @Override
            public void onPushEvent(int event, Bundle bundle) {
                if (event == TXLiveConstants.PUSH_EVT_PUSH_BEGIN) {
                    TXCLog.d(TAG, "推流成功");
//                    callbackOnThread(mCallback, "onSuccess");
                } else if (event == TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL) {
                    String msg = "[LivePusher] 推流失败[打开摄像头失败]";
                    TXCLog.e(TAG, msg);
//                    callbackOnThread(mCallback, "onError", event, msg);
                } else if (event == TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL) {
                    String msg = "[LivePusher] 推流失败[打开麦克风失败]";
                    TXCLog.e(TAG, msg);
//                    callbackOnThread(mCallback, "onError", event, msg);
                } else if (event == TXLiveConstants.PUSH_ERR_NET_DISCONNECT || event == TXLiveConstants.PUSH_ERR_INVALID_ADDRESS) {
                    String msg = "[LivePusher] 推流失败[网络断开]";
                    TXCLog.e(TAG,msg);
//                    callbackOnThread(mCallback, "onError", event, msg);
                }
            }

            @Override
            public void onNetStatus(Bundle bundle) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLivePusher != null) {
            mLivePusher.stopPusher();
            //如果已经启动了摄像头预览，请在结束推流时将其关闭。
            mLivePusher.stopCameraPreview(true);
        }
    }


}
