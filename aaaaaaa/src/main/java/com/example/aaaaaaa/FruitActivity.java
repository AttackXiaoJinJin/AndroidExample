package com.example.aaaaaaa;

/*
 * Created by chenjin on 2017/4/28.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME="fruit_name";
    public static final String FRUIT_IMAGE_ID="fruit_image_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent=getIntent();
        String fruitName=intent.getStringExtra(FRUIT_NAME);
        int fruit_image_Id=intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView imageView=(ImageView)findViewById(R.id.fruit_image_view);
        TextView textView=(TextView)findViewById(R.id.fruit_name);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(this).load(fruit_image_Id).into(imageView);
        String fruitContent=generateFruitContent(fruitName);
        textView.setText(fruitContent);


    }

    private String generateFruitContent(String fruitName)
    {
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<500;i++)
        {
            stringBuilder.append(fruitName);
        }
        return stringBuilder.toString();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case android.R.id.home:finish();return true;
        }
        return  super.onOptionsItemSelected(menuItem);
    }


}