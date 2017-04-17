package com.example.example_viewpaper.maintab;

/*
 * Created by chenjin on 2017/4/14.
 */

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.example_viewpaper.R;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.LayoutBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/*tab_main_center.xml
* fragment_maintab.xml
* layout_slide_bar.xml
* */
/*一个Activity里面可能会以viewpager（或其他容器）与多个Fragment来组合使用，
而如果每个fragment都需要去加载数据，或从本地加载，或从网络加载，
那么在这个activity刚创建的时候就变成需要初始化大量资源,使用lazyfragment
当切换到这个fragment的时候，它才去初始化*/
public class FirstLayerFragment extends LazyFragment
{
    public static final String INTENT_STRING_TABNAME = "intent_String_TabName";
    public static final String INTENT_INT_INDEX = "intent_Int_Index";


    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater layoutInflater;

    private String tabName;
    private int index;

    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);


        setContentView(R.layout.fragment_maintab);
        //从MainTabActivity中传递数据到FirstLayerFragment
        Resources resources=getResources();
        Bundle bundle=getArguments();
        tabName=bundle.getString(INTENT_STRING_TABNAME);
        index=bundle.getInt(INTENT_INT_INDEX);

        ViewPager viewPager=(ViewPager)findViewById(R.id.fragment_maintab_viewPager);
        Indicator indicator=(Indicator)findViewById(R.id.fragment_maintab_indicator);
        //index指向MainTabActivity的tabIcons位置,根据其位置在顶部显示相应的样式
        switch (index)
        {
            case 0:
                //微博标签上的scrollbar的样式
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED,5));
                break;
            case 1:
                //消息标签上的scrollbar的样式
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
                break;
            case 2:
                //发现标签上的scrollbar的样式
                indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5, ScrollBar.Gravity.TOP));
                break;
            case 3:
                //我标签上的scrollbar的样式
                indicator.setScrollBar(new LayoutBar(getApplicationContext(),R.layout.layout_slide_bar, ScrollBar.Gravity.CENTENT_BACKGROUND));
                break;
        }

        //设置顶部IndicatorViewPager的字体大小
        float unselectSize=16;
        float selectSize=unselectSize*1.2f;
        //设置顶部IndicatorViewPager的字体颜色
        int unselectColor=resources.getColor(R.color.tab_top_text_1,null);
        int selectColor=resources.getColor(R.color.tab_top_text_2,null);

        //设置触击监听器
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor,unselectColor).setSize(selectSize,unselectSize));
        //设置viewPager保留界面而不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);

        //创建对象
        indicatorViewPager = new IndicatorViewPager(indicator,viewPager);
        layoutInflater = LayoutInflater.from(getApplicationContext());

        // 注意,在Fragment里，FragmentManager是getChildFragmentManager()
        // 而在activity里面用FragmentManager 是 getSupportFragmentManager()
        indicatorViewPager.setAdapter(new SinaAdapter(getChildFragmentManager()));

        Log.d("cccc", "Fragment 将要创建View " + this);

    }

    //Fragment的生命周期及其动作
  /*  protected void onResumeLazy() {
        super.onResumeLazy();
        Log.d("cccc", "Fragment所在的Activity onResume, onResumeLazy " + this);
    }

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();
        Log.d("cccc", "Fragment 显示 " + this);
    }

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        Log.d("cccc", "Fragment 掩藏 " + this);
    }

    @Override
    protected void onPauseLazy() {
        super.onPauseLazy();
        Log.d("cccc", "Fragment所在的Activity onPause, onPauseLazy " + this);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        Log.d("cccc", "Fragment View将被销毁 " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("cccc", "Fragment 所在的Activity onDestroy " + this);
    }*/

    private class SinaAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public SinaAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            //类库是4，试试
            //tabName.length()
            return 4;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabName + " " + position);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            SecondLayerFragment mainFragment = new SecondLayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, tabName);
            bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
    }






}
