package com.example.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.materialdesign.cardview.CardViewActivity;
import com.example.materialdesign.collapsingtoolbarlayout.FruitActivity;
import com.example.materialdesign.collapsingtoolbarlayout.MainFruitActivity;
import com.example.materialdesign.drawerlayout.DrawerLayoutActivity;
import com.example.materialdesign.navigation.NavigationActivity;
import com.example.materialdesign.toolbar.ToolBarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClickToolBar(View view)
    {
        startActivity(new Intent(getApplicationContext(), ToolBarActivity.class));
    }



    public void onClickDrawerLayout(View view)
    {
        startActivity(new Intent(getApplicationContext(), DrawerLayoutActivity.class));
    }


    public void onClickNavigationView(View view)
    {
        startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
    }


    public void onClickCardView(View view)
    {
        startActivity(new Intent(getApplicationContext(), CardViewActivity.class));
    }



    public void onClickCollapsingToolbarLayout(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainFruitActivity.class));
    }


    public void onClickFruitActivity(View view)
    {
        startActivity(new Intent(getApplicationContext(), FruitActivity.class));
    }

}
