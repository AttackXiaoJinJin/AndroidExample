package com.example.example_viewpaper.countlesstab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.example_viewpaper.R;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.slidebar.SpringBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

//可滑动tab界面（详细配置）
/*包含activity_countlesstab.xml
     tab_top.xml
*
* */
public class CountlessTabActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;

    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countlesstab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.countlesstab_viewPager);
        Indicator indicator = (RecyclerIndicatorView) findViewById(R.id.countlesstab_indicator);
        int selectColorId = Color.parseColor("#f8f8f8");
        int unSelectColorId = Color.parseColor("#010101");
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColorId, unSelectColorId));
        indicator.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));
        viewPager.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        //数字，从1800到2050
        indicatorViewPager.setAdapter(new YearAdapter(1800, 2050));
        indicatorViewPager.setCurrentItem(2017-1800, false);
    }

    private class YearAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private int startYear;
        private int endYear;

        public YearAdapter(int startYear, int endYear) {
            super(getSupportFragmentManager());
            this.startYear = startYear;
            this.endYear = endYear;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            int padding = dipToPix(getApplicationContext(), 12);
            textView.setPadding(padding, 0, padding, 0);
            textView.setText(String.valueOf(startYear + position));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            YearFragment yearFragment = new YearFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(YearFragment.INTENT_INT_POSITION, position);
            yearFragment.setArguments(bundle);
            return yearFragment;
        }

        @Override
        public int getCount() {
            return endYear - startYear;
        }
    }


    }






