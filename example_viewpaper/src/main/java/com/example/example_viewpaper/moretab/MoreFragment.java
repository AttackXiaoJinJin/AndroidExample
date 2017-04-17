package com.example.example_viewpaper.moretab;

/*
 * Created by chenjin on 2017/4/14.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.example_viewpaper.R;
import com.shizhefei.fragment.LazyFragment;

/*fragment_maintab_item.xml
* */
/*一个Activity里面可能会以viewpager（或其他容器）与多个Fragment来组合使用，
而如果每个fragment都需要去加载数据，或从本地加载，或从网络加载，
那么在这个activity刚创建的时候就变成需要初始化大量资源,使用lazyfragment
当切换到这个fragment的时候，它才去初始化*/
public class MoreFragment extends LazyFragment
{
    private ProgressBar progressBar;
    private TextView textView;
    //private int tabIndex;
    public static final String INTENT_INT_INDEX = "intent_int_index";

    private Handler handler;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        /*1.要刷新UI，handler要用到主线程的looper。那么在主线程 Handler handler = new Handler();
         如果在其他线程，也要满足这个功能的话，要Handler handler = new Handler(Looper.getMainLooper());
         2.不用刷新ui,只是处理消息。
         当前线程如果是主线程的话，Handler handler = new Handler();不是主线程的话，Looper.prepare();
         Handler handler = new Handler();Looper.loop();或者Handler handler = new Handler(Looper.getMainLooper());
         若是实例化的时候用Looper.getMainLooper()就表示放到主UI线程去处理。
         如果不是的话，因为只有UI线程默认Loop.prepare();Loop.loop();过，其他线程需要手动调用这两个，否则会报错。*/
        //在onCreateViewLazy里面Looper.getMainLooper()
        handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(android.os.Message msg) {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
        };
        int tabIndex;
        setContentView(R.layout.fragment_maintab_item);
        tabIndex = getArguments().getInt(INTENT_INT_INDEX);
        progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);
        textView = (TextView) findViewById(R.id.fragment_mainTab_item_textView);
        textView.setText("界面" + " " + tabIndex + " 加载完毕");
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeCallbacksAndMessages(null);
    }


}
