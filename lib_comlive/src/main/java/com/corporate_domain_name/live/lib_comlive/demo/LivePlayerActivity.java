package com.corporate_domain_name.live.lib_comlive.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.corporate_domain_name.live.lib_comlive.R;

import java.io.UnsupportedEncodingException;

public class LivePlayerActivity extends AppCompatActivity {
    String flvUrl = "";
    String rtmpUrl = "";
    private TXLivePlayer mLivePlayer;
    private TXLivePlayConfig mTXLivePlayConfig;
    private TXCloudVideoView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_player);

        //mPlayerView 即 step1 中添加的界面 view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);
        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        //关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mView);
        //推荐 FLV
        flvUrl = TestData.getTestStreamBean().getUrl_play_flv();
        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV);
//        mLivePlayer.startPlay(rtmpUrl, TXLivePlayer.PLAY_TYPE_LIVE_RTMP);

        mTXLivePlayConfig = new TXLivePlayConfig();
        mTXLivePlayConfig = new TXLivePlayConfig();
        mTXLivePlayConfig.setAutoAdjustCacheTime(true);
        mTXLivePlayConfig.setMaxAutoAdjustCacheTime(2.0f);
        mTXLivePlayConfig.setMinAutoAdjustCacheTime(2.0f);
        mLivePlayer.setConfig(mTXLivePlayConfig);
        //Android 示例代码
        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int event, Bundle param) {
                if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {
//                    roomListenerCallback.onDebugLog("[AnswerRoom] 拉流失败：网络断开");
//                    roomListenerCallback.onError(-1, "网络断开，拉流失败");
                }
                else if (event == TXLiveConstants.PLAY_EVT_GET_MESSAGE) {
                    String msg = null;
                    try {
                        msg = new String(param.getByteArray(TXLiveConstants.EVT_GET_MSG), "UTF-8");
//                        roomListenerCallback.onRecvAnswerMsg(msg);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onNetStatus(Bundle status) {
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLivePlayer != null) {
            // true 代表清除最后一帧画面
            mLivePlayer.stopPlay(true);
            mView.onDestroy();
        }
    }
}
