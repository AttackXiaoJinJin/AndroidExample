package com.project.chenjin.example_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAnimationActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_animation);
        textView=(TextView)findViewById(R.id.textview);
        imageView=(ImageView)findViewById(R.id.imageview);
    }

    /*
    * 1.1缩放动画
    * ScaleAnimation*/
    public void startScaleWithCode(View v){
     textView.setText("缩放动画：宽度从0.5到1.5,高度从0.0到1.0，缩放的圆心为顶部中心点，延迟1s开始，持续2s。最终还原");
    }


}
