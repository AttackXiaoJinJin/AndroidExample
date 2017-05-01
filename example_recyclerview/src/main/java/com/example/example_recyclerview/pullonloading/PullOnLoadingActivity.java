package com.example.example_recyclerview.pullonloading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.example_recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class PullOnLoadingActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout swipeRefreshLayout;
    private PullOnLoadingAdapter adapter;
     private RecyclerView recyclerView;
    private List<String> list;

    private int lastVisibleItem=0;
    private final int PAGE_COUNT=10;
    private GridLayoutManager gridLayoutManager;
    private Handler handler =new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.pull_on_loading);

        initData();
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefreshLayout);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        initSwipeRefreshLayout();
       initRecyclerView();




    }

    private void initData()
    {
        list =new ArrayList<>();
        for(int i=1;i<=40; i++)
        {
            list.add("条目" + i);
        }
    }

    private void initSwipeRefreshLayout()
    {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void onRefresh()
    {
        //设置可见
        swipeRefreshLayout.setRefreshing(true);
        //重置adapter的数据源为空
        adapter.resetDatas();
        //获取第0条到第PAGE_COUNT（值为10）条的数据
        updateRecyclerView(0,PAGE_COUNT);
        handler.postDelayed(new Runnable(){
            public void run()
            {
                //模拟网络加载时间，设置不可见
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void initRecyclerView()
    {
        /*初始化RecyclerView的Adapter
        * 第一个参数为数据，上拉加载的原理就是分页，故设置PAGE_COUNT=10,即每次加载10个数据
        * 第二个参数为context
        * 第三个参数为hasMore,是否有新数据*/
        //getDatas(0, PAGE_COUNT).size() > 0 ,为真返回true,反之返回false
        adapter=new PullOnLoadingAdapter(getDatas(0, PAGE_COUNT), this, getDatas(0, PAGE_COUNT).size() > 0  );
        gridLayoutManager= new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //实现上拉加载重要步骤，设置滑动监听器，RecyclerView自带ScrollListener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //在newState滑动到底部时
                if(newState==RecyclerView.SCROLL_STATE_IDLE)
                {
                    //如果没有隐藏footView,那最后一个条目的位置就比我们的getItemCount少1
                    if(!adapter.isFadeTips() && (lastVisibleItem+1) ==adapter.getItemCount())
                    {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //调用undataRecyclerView方法更新RecyclerView
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition()+PAGE_COUNT);
                            }
                        }, 1000);
                    }
                    else
                    {
                        Log.e("a","footView隐藏了");
                    }

                    //如果隐藏了提示条，我们又向上加载时，那么最后一个条目要比getItemCount少2
                    if(adapter.isFadeTips() && (lastVisibleItem+2) == adapter.getItemCount())
                    {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //调用undataRecyclerView方法更新RecyclerView
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition()+PAGE_COUNT);
                            }
                        }, 1000);
                    }
                    else
                    {
                        Log.e("a","提示条隐藏了");
                    }
                }
            }


            public void onScrolled(RecyclerView recyclerView, int dx,int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                //在滑动完成后，拿到最后一个可见的item的位置
                lastVisibleItem=gridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private List<String> getDatas(final int firstIndex, final int lastIndex)
    {
        List<String> resList=new ArrayList<>();
        for(int i=firstIndex;i<lastIndex;i++)
        {
            if(i<list.size())
            {
                resList.add(list.get(i));
            }
        }
        return resList;
    }




    //上拉加载时调用的updateRecycleView的方法
    private void updateRecyclerView(int fromIndex, int toIndex)
    {
        //获取从fromIndex到toIndex数据
        List<String> newDatas=getDatas(fromIndex, toIndex);
        if(newDatas.size()>0)
        {
            //传给adapter，并设置hasMore为true
            adapter.updateList(newDatas, true);
        }
        else
        {
            adapter.updateList(null, false);
        }
    }










}
