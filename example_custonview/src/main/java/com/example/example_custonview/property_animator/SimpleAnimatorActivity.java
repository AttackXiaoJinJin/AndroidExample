package com.example.example_custonview.property_animator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.example_custonview.R;

public class SimpleAnimatorActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_property_animator);

    }

    public void rotateAnimation(View view)
    {
        ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F)
                .setDuration(500)
                .start();
    }
}
