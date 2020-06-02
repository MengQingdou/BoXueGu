package com.example.boxuegu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController controller;
    private String videoPath;       // 本地视频地址
    private int position;           // 传递视频详情界面点击的视频位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);
        // 设置此界面为横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 获取从课程详情界面或者播放记录界面传递过来的视频地址，两个活动都有进行传递
        videoPath = getIntent().getStringExtra("videoPath");
        position = getIntent().getIntExtra("position", 0);
        init();
    }

    /**
     * 初始化UI控件
     */
    private void init() {
        videoView = (VideoView) findViewById(R.id.videoView);
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }

    /**
     * 播放视频
     */
    private void play() {
        if(TextUtils.isEmpty(videoPath)){
            Toast.makeText(VideoPlayActivity.this, "本地没有此视频， 暂无法播放", Toast.LENGTH_SHORT).show();
            return;
        }
        // 在这里的处理方式，是直接使用的默认的，就是说无论videoPath中得到的是什么，只要不是空的，那么就播放这个video11这个视频
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.video11;    // res/raw中的文件会被映射到R文件中，直接使用资源ID即可
        videoView.setVideoPath(uri);
        videoView.start();
    }
    /**
     * 点击后退按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 把视频详情界面传递过来的被点击视频的位置传递回去，传递给谁呢？就是上一个界面呗，从哪里传过来的就再传回去呗
        // 从这个返回的上一个界面有可能是 视频详情界面 也有可能是 播放记录界面呐！！！
        // 在视频详情界面代码里，添加了应该怎么处理这个接收到的位置的信息
        Intent data = new Intent();
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        return super.onKeyDown(keyCode, event);
    }
}
