package com.example.example_viewpaper.springtab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.example_viewpaper.R;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

//可滑动tab界面（详细配置）
/*包含activity_spring.xml
*    fragment_maintab_item.xml
* */
public class SpringTabActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater layoutInflater;
    private int unSelectColor;

    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);
        ViewPager viewPager = (ViewPager) findViewById(R.id.spring_viewPager);
        Indicator indicator = (ScrollIndicatorView) findViewById(R.id.spring_indicator);
        int selectColor = Color.parseColor("#f8f8f8");
        unSelectColor = Color.parseColor("#010101");
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));

        /*关键*/
        //设置scrollbar样式
        indicator.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));

        viewPager.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        layoutInflater = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(adapter);
        //目前页面显示的item
        indicatorViewPager.setCurrentItem(5, false);
    }

    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = dipToPix(getApplicationContext(), 30);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(String.valueOf(position));
            textView.setTextColor(unSelectColor);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.fragment_maintab_item, container, false);
            }
            final TextView textView = (TextView) convertView.findViewById(R.id.fragment_mainTab_item_textView);

            textView.setText(" " + position + " 界面加载完毕");
            final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.fragment_mainTab_item_progressBar);


            new Handler() {
                public void handleMessage(android.os.Message msg) {
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }.sendEmptyMessageDelayed(1, 2000);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        @Override
        public int getCount() {
            return 16;
        }
    };

    }






