package com.example.example_viewpaper.maintab;

/*
 * Created by chenjin on 2017/4/14.
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.example_viewpaper.R;
import com.shizhefei.fragment.LazyFragment;



/*中间缓存的内容
fragment_maintab_item.xml
layout_preview.xml
* */
public class SecondLayerFragment extends LazyFragment
{
    public static final String INTENT_STRING_TABNAME = "intent_String_TabName";
    public static final String INTENT_INT_POSITION = "intent_Int_Position";


    private String tabName;
    private int position;



    private TextView textView;
    private ProgressBar progressBar;

    //layoutInflater,container在外面定义的话，会识别不出来
    protected View getPreviewLayout(LayoutInflater layoutInflater,ViewGroup container)
    {
        return layoutInflater.inflate(R.layout.layout_preview,container,false);
    }

    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_maintab_item);

        tabName=getArguments().getString(INTENT_STRING_TABNAME);
        position=getArguments().getInt(INTENT_INT_POSITION);

       textView = (TextView)findViewById(R.id.fragment_mainTab_item_textView);
        textView.setText(tabName+" "+position+"界面加载完毕");
        progressBar=(ProgressBar)findViewById(R.id.fragment_mainTab_item_progressBar);
        handler.sendEmptyMessageDelayed(1, 2000);

    }

    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeMessages(1);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {

        //progressbar之后显示信息
        public void handleMessage(Message msg) {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    };











}
