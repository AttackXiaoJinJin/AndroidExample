package com.example.example_viewpaper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.RecyclerIndicatorView;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

//banner轮播
/*用到的resource
activity_singletab.xml
* */
public class SingleTabActivity extends Activity
{
    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletab);

        ScrollIndicatorView scrollIndicatorView = (ScrollIndicatorView) findViewById(R.id.singleTab_scrollIndicatorView);
        FixedIndicatorView fixedIndicatorView = (FixedIndicatorView) findViewById(R.id.singleTab_fixedIndicatorView);
        RecyclerIndicatorView recyclerIndicatorView = (RecyclerIndicatorView) findViewById(R.id.singleTab_reyclerIndicatorView);

        set(scrollIndicatorView, 16);
        set(fixedIndicatorView, 5);
        set(recyclerIndicatorView, 100);
    }

    private void set(Indicator indicator, int count) {
        indicator.setAdapter(new MyAdapter(count));

        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.tab_top_text_2,null);
        int unSelectColor = getResources().getColor(R.color.tab_top_text_1,null);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

        indicator.setCurrentItem(2,true);
    }

    private class MyAdapter extends Indicator.IndicatorAdapter {

        private final int count;

        public MyAdapter(int count) {
            super();
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, parent, false);
            }
            TextView textView = (TextView) convertView;
            //用了固定宽度可以避免TextView文字大小变化，tab宽度变化导致tab抖动现象
            textView.setWidth(dipToPix(getApplicationContext(),50));
            textView.setText(String.valueOf(position));
            return convertView;
        }
    }




}
