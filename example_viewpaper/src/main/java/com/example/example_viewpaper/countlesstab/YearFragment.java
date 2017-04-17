package com.example.example_viewpaper.countlesstab;

/*
 * Created by chenjin on 2017/4/14.
 */

import android.os.Bundle;
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
public class YearFragment extends LazyFragment
{
    public static final String INTENT_INT_POSITION = "intent_int_position";

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        //Log.d("pppp", "onCreateViewLazy  " + this+" "+savedInstanceState);

        int position = getArguments().getInt(INTENT_INT_POSITION);

        setContentView(R.layout.fragment_maintab_item);
        final TextView textView = (TextView) findViewById(R.id.fragment_mainTab_item_textView);
        textView.setText(" " + position + " 界面加载完毕");
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.fragment_mainTab_item_progressBar);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

}
