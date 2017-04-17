package com.example.example_viewpaper.moretab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.example_viewpaper.R;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

//可滑动tab界面（详细配置）
/*包含activity_moretab.xml
     round_border_white_selector.xml
     tab_top.xml
     tabshadow.png
*
* */
public class MoreTabActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = {"花蕊", "真心相对", "彩云", "风雨相随", "不遥远的地方", "跌跌撞撞", "鼓掌", "小桥流水"};
    //滚动视图
    private ScrollIndicatorView scrollIndicatorView;
    //开关按钮，是一个具有选中和未选择状态双状态的按钮，并且需要为不同的状态设置不同的显示文本。
    private ToggleButton pinnedToggleButton;

    private ToggleButton splitAutotoggleButton;
    private  int unSelectTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //加载moretab界面
        setContentView(R.layout.activity_moretab);
        //自动布局，不自动布局的按钮
        splitAutotoggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        //固定tab，不固定tab的按钮
        pinnedToggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        //滚动bar的背景颜色为红色
        scrollIndicatorView.setBackgroundColor(Color.RED);
        //设置scrollbar样式，位置是居中作为背景色
        scrollIndicatorView.setScrollBar(new DrawableBar(this, R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {

            public int getHeight(int tabHeight)
            {
                return tabHeight - dipToPix(12);
            }


            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(12);
            }
        });

       // scrollIndicatorView.setScrollBar(new SpringBar(getApplicationContext(), Color.GRAY));



        //未选中的文字设置为白色
        unSelectTextColor = Color.WHITE;
        // 设置滚动监听,textView选中时设为红色
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, unSelectTextColor));

        //viewPager保留界面而不重新加载的页面数量
        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        // 默认true ，自动布局
        //自动布局即把indicator的3个tab平均占满width
        splitAutotoggleButton.setChecked(scrollIndicatorView.isSplitAuto());
        //设置按钮1监听
        splitAutotoggleButton.setOnCheckedChangeListener(onCheckedChangeListener);
        //设置按钮2监听
        pinnedToggleButton.setOnCheckedChangeListener(onCheckedChangeListener);

    }
     //CompoundButton,ToggleButton
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == splitAutotoggleButton) {
                // 设置是否自动布局
                scrollIndicatorView.setSplitAuto(isChecked);
            } else if (buttonView == pinnedToggleButton) {
                scrollIndicatorView.setPinnedTabView(isChecked);
                // 设置固定tab的shadow，这里不设置的话会使用默认的shadow绘制
                scrollIndicatorView.setPinnedShadow(R.drawable.tabshadow, dipToPix(4));
                scrollIndicatorView.setPinnedTabBgColor(Color.RED);
            }
        }
    };

    //初始化tab个数
    private int count = 3;

    public void on3(View view) {
        count = 3;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }



    public void on5(View view) {
        count = 5;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    public void on12(View view) {
        count = 12;
        indicatorViewPager.getAdapter().notifyDataSetChanged();
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        public int getCount() {
            return count;
        }


        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            //根据position来确定该显示几个textView
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            //padding间距，左上右下
            textView.setPadding(padding, 0, padding, 0);



            return convertView;
        }


        public Fragment getFragmentForPage(int position) {
            MoreFragment fragment = new MoreFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(MoreFragment.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
            return fragment;
        }


        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }

    }

    ;

    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    /*1.px （pixels）（像素）：是屏幕的物理像素点，与密度相关，密度大了，单位面积上的px会比较多。通常不推荐使用这个。
      2.dip或dp（与密度无关的像素）：一个基于density的抽象单位，这个和设备硬件有关，通常在开发中设置一些view的宽高推荐用这个，
      一般情况下，在不同分辨率，都不会有缩放的感觉。在运行时, Android根据使用中的屏幕的实际密度, 透明地处理任何所需dip单位的缩放。
      3.sp（与刻度无关的像素）：同dip/dp相似，会根据用户的字体大小偏好来缩放，主要用于设置字体的大小。*/
    private int dipToPix(float dip) {
        int pxSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return pxSize;
    }



    }






