package com.example.example_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.example_recyclerview.implerecyclerview.SimpleRecyclerViewActivity;
import com.example.example_recyclerview.pullonloading.PullOnLoadingActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void PullOnLoadingActivity(View view)
    {
        startActivity(new Intent(getApplicationContext(), PullOnLoadingActivity.class));
    }

    public void SimpleRecycler(View view)
    {
        startActivity(new Intent(getApplicationContext(), SimpleRecyclerViewActivity.class));
    }





}
