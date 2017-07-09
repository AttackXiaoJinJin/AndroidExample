package com.project.chenjin.example_mediaplay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private VideoView videoView;

    /*//记住要在Manifest中声明用到的权限！！
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(VideoView)findViewById(R.id.video_view);
        Button play=(Button)findViewById(R.id.play);
        Button pause=(Button)findViewById(R.id.pause);
        Button replay=(Button)findViewById(R.id.replay);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);

        //动态权限申请，因为视频文件放在SD卡上
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1
            );
        }
        else {
            //初始化MediaPlayer
            initVideoPath();
        }

    }

    //事先放在SD卡的根目录上
    private void initVideoPath(){
        File file=new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        //指定视频文件的路径
        videoView.setVideoPath(file.getPath());
    }

    //权限申请
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }
                else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){
                    //开始播放
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying()){
                    //暂停播放
                    videoView.pause();
                }
                break;

            case R.id.replay:
                if(videoView.isPlaying()){
                    //重新播放
                    videoView.resume();
                }
                break;

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(videoView != null){
            //中止
            videoView.suspend();
        }
    }



}
