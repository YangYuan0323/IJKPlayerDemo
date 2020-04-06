package com.johnyang.ijkplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import com.johnyang.ijkplayer.widget.media.AndroidMediaController;
import com.johnyang.ijkplayer.widget.media.IjkVideoView;
import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private IjkVideoView mVideoView;
    private TableLayout mHudView;
    private  Button startPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = findViewById(R.id.video_view);
        mHudView = findViewById(R.id.hud_view);

        startPlay = findViewById(R.id.startPlay);

        initPlayer();

        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                mVideoView.start();
            }
        });
    }


    private void initPlayer() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoView.setHudView(mHudView);
        AndroidMediaController controller = new AndroidMediaController(this, false);
        mVideoView.setMediaController(controller);
        String url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";
//        String url = "http://5815.liveplay.myqcloud.com/live/5815_89aad37e06ff11e892905cb9018cf0d4.flv";
        mVideoView.setVideoPath(url);
    }

}
