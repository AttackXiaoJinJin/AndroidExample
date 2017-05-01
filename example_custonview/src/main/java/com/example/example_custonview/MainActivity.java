package com.example.example_custonview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.example_custonview.property_animator.SimpleAnimatorActivity;

public class MainActivity extends AppCompatActivity {
    Button simpleimageview;
    Button customview;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleimageview=(Button)findViewById(R.id.simpleimageview);
        customview=(Button)findViewById(R.id.customtitleview);



        customview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                setContentView(R.layout.custom_title_view);
            }
        });

        simpleimageview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                setContentView(R.layout.simple_image_view);
            }
        });







    }

    public void startSimpleAnimator(View view)
    {
        startActivity(new Intent(getApplicationContext(), SimpleAnimatorActivity.class));
    }


}
