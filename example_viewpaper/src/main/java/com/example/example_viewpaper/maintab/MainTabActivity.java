package com.example.example_viewpaper.maintab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_viewpaper.R;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

//tab主界面

/*包含activity_maintab.xml
* tab_main.xml
* sinatab_selector_1.xml
* sinatab_selector_2.xml
* sinatab_selector_3.xml
* sinatab_selector_4.xml
* */
public class MainTabActivity extends FragmentActivity {
    private IndicatorViewPager indicatorViewPager;
    private View centerView;
    private FixedIndicatorView fixedIndicatorView;
    private SViewPager sViewPager;



    @Override
    protected void onCreate(Bundle arg0) {
        //调用Activity的onCreate(bundle arg0)方法,完成窗口初始化
        super.onCreate(arg0);
        //1、setContentView的作用是将View加载到根view之上，这样当显示view时，先显示根view，然后在显示子view，以此类推，最终将所有view显示出来。
        //2、setContentView必须要放在findviewbyid之前，因为view在加载之前是无法引用的。
        //3、setContentView最本质的作用是为要显示的view分配内存。
        setContentView(R.layout.activity_maintab);
        //获取id
        sViewPager=(SViewPager) findViewById(R.id.maintab_viewPager);
        fixedIndicatorView=(FixedIndicatorView)findViewById(R.id.maintab_indicator);
        //为FixedIndicatorView的selector设置为RED，unselector设置为GRAY
        fixedIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED,Color.GRAY));

        //centerView,相当于新浪微博的 + 按钮
        /*在实际开发中LayoutInflater这个类还是非常有用的，它的作用类似于findViewById()。
        不同点是LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；
        而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)。
        具体作用：
        1、对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
        2、对于一个已经载入的界面，就可以使用Activiyt.findViewById()方法来获得其中的界面元素。*/
        centerView=getLayoutInflater().inflate(R.layout.main_tab_center, fixedIndicatorView, false);
        //放在中间，index=getchildcount/2
        fixedIndicatorView.setCenterView(centerView);
        //设置一个监听器
        centerView.setOnClickListener(onClickListener);

        indicatorViewPager=new IndicatorViewPager(fixedIndicatorView,sViewPager);
        indicatorViewPager.setAdapter(new SinaAdapter(getSupportFragmentManager()));
        //禁止sviewPager滑动
        //若设为true则会滑动到下一tabName去
        sViewPager.setCanScroll(false);
        //设置sviewPager保留界面而不重新加载的页面数量
        sViewPager.setOffscreenPageLimit(4);

    }

    //触屏点击触发事件
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == centerView) {
                //去掉centerView
                //indicator.removeCenterView();
                Toast.makeText(getApplicationContext(), "点击了CenterView", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class SinaAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter
    {
        private String[] tabNames={"微博", "消息", "发现", "我"};
        private int[] tabIcons={R.drawable.sinatab_selector_1, R.drawable.sinatab_selector_2, R.drawable.sinatab_selector_3, R.drawable.sinatab_selector_4};
        private LayoutInflater layoutInflater;

        public SinaAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
            layoutInflater=LayoutInflater.from(getApplicationContext());
        }

        public int getCount() {
            return tabNames.length;
        }



        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null)
            {   //获取tab_main布局，让字体和图片均在中间位置
                convertView = layoutInflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            //设置字体和图片的位置
            //可以在左，上，右，下设置图标，如果不想在某个地方显示，则设置为null。
            // 图标的宽高将会设置为固有宽高，既自动通过getIntrinsicWidth和getIntrinsicHeight获取。
            //我是通过photoshop调试底部图片大小的
            textView.setCompoundDrawablesWithIntrinsicBounds( 0, tabIcons[position], 0, 0);

            return textView;
        }


        public Fragment getFragmentForPage(int position) {
            //new一个FirstLayerFragment对象，mainFragment
            FirstLayerFragment mainFragment = new FirstLayerFragment();
            //BUndle类是key-value键值匹配的，用于两个activity通讯，即一个Activity从另一Activity取数据
            Bundle bundle = new Bundle();
            //通过键来取值
            bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME, tabNames[position]);
            bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
            /*Activity重新创建时，会重新构建它所管理的Fragment，
            原先的Fragment的字段值将会全部丢失，
            但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来。
            所以尽量使用Fragment.setArguments(Bundle bundle)方式来传递参数*/
            mainFragment.setArguments(bundle);
            return mainFragment;


        }



    }





}
