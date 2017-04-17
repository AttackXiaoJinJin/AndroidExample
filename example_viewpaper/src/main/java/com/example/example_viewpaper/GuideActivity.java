package com.example.example_viewpaper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorPagerAdapter;

//引导界面
/*activity_guide.xml
*tab_guide.xml*/
public class GuideActivity extends FragmentActivity
{
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater layoutInflater;


    private int[] images={R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4};

    private IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        public int getCount() {
            return images.length;
        }


        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.tab_guide, container, false);
            }
            return convertView;
        }


        public View getViewForPage(int position, View convertView, ViewGroup container)
        {
            if (convertView == null) {
                convertView = new View((getApplicationContext()));
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            }
            convertView.setBackgroundResource(images[position]);
            return convertView;
        }

        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;

        }
    };

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_guide);

        ViewPager viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.guide_indicator);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        //layoutinflater加载布局,一般是用来动态添加View的
        layoutInflater = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(adapter);

       /* Toast.makeText(getApplicationContext(), "3S后更新界面适配器", Toast.LENGTH_SHORT).show();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        images = new int[]{R.drawable.p1, R.drawable.p2};
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();

*/
    }


    }






