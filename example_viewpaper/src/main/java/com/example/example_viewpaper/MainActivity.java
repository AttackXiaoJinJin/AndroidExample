package com.example.example_viewpaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.example_viewpaper.countlesstab.CountlessTabActivity;
import com.example.example_viewpaper.maintab.MainTabActivity;
import com.example.example_viewpaper.moretab.MoreTabActivity;
import com.example.example_viewpaper.moretab.MoreTabActivityTwo;
import com.example.example_viewpaper.recyclerview.HomeActivity;
import com.example.example_viewpaper.recyclerview.StaggeredGridLayoutActivity;
import com.example.example_viewpaper.springtab.SpringTabActivity;


public class MainActivity extends FragmentActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //复制其他activity后，记得修改！！
        setContentView(R.layout.activity_main);


    }

    /*protected void onDestory()
    {
        super.onDestroy();
    }*/

    //BannerComponent（无限轮播Banner）
    //FixedIndicatorView

    public void onClickBanner(View view)
    {
        startActivity(new Intent(getApplicationContext(), BannerActivity.class));
    }

    //引导界面Guide
    //fixedIndicatorView
    public void onClickGuide(View view)
    {
        startActivity(new Intent(getApplicationContext(), GuideActivity.class));
    }

    //标签主界面
    //fixedIndicatorView
    public void onClickMainTab(View view)
    {
        startActivity(new Intent(getApplicationContext(), MainTabActivity.class));
    }

    //可滑动标签界面
    //ScrollIndicatorView
    public void onClickSlideTabTwo(View view)
    {
        startActivity(new Intent(getApplicationContext(), MoreTabActivityTwo.class));
    }

   //可滑动标签界面
    //ScrollIndicatorView详细配置
    public void onClickSlideTab(View view)
    {
        startActivity(new Intent(getApplicationContext(), MoreTabActivity.class));
    }

    //跃动标签(SpringTab)
    //ScrollIndicatorView
    //实现拖拽效果的圆形滑动块
    public void onClickSpringTab(View view)
    {
        startActivity(new Intent(getApplicationContext(), SpringTabActivity.class));
    }

    //无数tab的界面
    //RecyclerIndicatorView
    public void onClickYear(View view)
    {
        startActivity(new Intent(getApplicationContext(),CountlessTabActivity.class));
    }

    //不集成ViewPager的Indicator的使用方式
    public void onClickTabs(View view)
    {
        startActivity(new Intent(getApplicationContext(), SingleTabActivity.class));
    }

    public void onClickSimpleRecyclerView(View view)
    {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    public void onClickStaggeredGridLayoutActivity(View view)
    {
        startActivity(new Intent(getApplicationContext(), StaggeredGridLayoutActivity.class));
    }


}
