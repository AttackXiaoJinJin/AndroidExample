package com.example.example_viewpaper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

//banner轮播
/*用到的resource
* activity_banner.xml
* p1.png
* p2.png
* p3.png
* p4.png*/
public class BannerActivity extends Activity
{
    private BannerComponent bannerComponent;

    private int[] images={R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};

    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container)
        {
           if(convertView == null)
           {
               convertView =new View(container.getContext());
           }
           return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container)
        {
            if(convertView == null)
            {
                convertView = new ImageView((getApplicationContext()));
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView=(ImageView)convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[position]);
            return convertView;
        }
    };

    protected void onCreate(Bundle arg0)
    {
        super.onCreate(arg0);
        setContentView(R.layout.activity_banner);

        ViewPager viewPager=(ViewPager)findViewById(R.id.banner_viewPager);
        Indicator indicator=(Indicator)findViewById(R.id.banner_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        viewPager.setOffscreenPageLimit(2);


        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button4=(Button)findViewById(R.id.button4);

        bannerComponent= new BannerComponent(indicator,viewPager,false);
        bannerComponent.setAdapter(adapter);

        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                images =new int[]{};
                adapter.notifyDataSetChanged();
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                images =new int[]{R.drawable.p1, R.drawable.p2};

                adapter.notifyDataSetChanged();
            }
        });

        button4.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                images =new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};

                adapter.notifyDataSetChanged();
            }
        });


        //默认就是800毫秒，设置单页滑动效果的时间
        //bannerComponent.setScrollDuration(800);
        //设置播放间隔时间，默认情况是3000毫秒
        bannerComponent.setAutoPlayTime(2500);

    }

    protected void onStart()
    {
        super.onStart();
        bannerComponent.startAutoPlay();
    }


    protected void onStop()
    {
        super.onStop();
        bannerComponent.stopAutoPlay();
    }




}
