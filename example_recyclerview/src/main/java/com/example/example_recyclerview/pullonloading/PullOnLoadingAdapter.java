package com.example.example_recyclerview.pullonloading;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.example_recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class PullOnLoadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    //数据源
    private List<String> datas;
    //上下文Context
    private Context context;
    //第一种ViewType,正常的item
    private int normalType=0;
    //第二种，footview
    private int footType=1;
    //是否有更多数据
    private boolean hasMore=true;
    //是否隐藏了底部的提示
    private boolean fadeTips=false;
    //获取主线程的handler
    private Handler handler=new Handler(Looper.getMainLooper());

    public PullOnLoadingAdapter(List<String> datas, Context context, boolean hasMore)
    {
        //初始化变量
        this.datas=datas;
        this.context=context;
        this.hasMore=hasMore;
    }

    //获取条目数量，之所以要加1是因为增加了一条footview
    public int getItemCount()
    {
        return datas.size()+1;
    }

    //自定义方法，获取列表中数据源的最后一个位置，不计上footview
    public int getRealLastPosition()
    {
        return datas.size();
    }

    //根据条目位置返回ViewType,以供onCreateViewHolder方法内获取不同的Holder
    public int getItemViewType(int position)
    {
        if(position==getItemCount()-1)
        {
            return footType;
        }
        else
        {
            return normalType;
        }
    }

    //正常的item的viewholder,用以缓存findview操作
    class NormalHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        public  NormalHolder(View itemView)
        {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.text1);
        }
    }

    //底部footView的viewholder,用以缓存findView操作
    class FootHolder extends RecyclerView.ViewHolder
    {
        private TextView footView;

        public FootHolder(View itemView)
        {
            super(itemView);
            footView=(TextView)itemView.findViewById(R.id.footview);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        //根据返回的viewType,绑定不同的布局文件
        if(viewType==normalType)
        {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.item_pull_on_loading, null));
        }
        else
        {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.footview, null));
        }
    }


    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder,int position)
    {
        //如果是正常的item,直接设置TextView的值
        if(viewHolder instanceof NormalHolder)
        {
            ((NormalHolder)viewHolder).textView.setText(datas.get(position));
        }
        else
        {
            //之所以设置可见，是在没有更多数据时隐藏footView
            ((FootHolder)viewHolder).footView.setVisibility(View.VISIBLE);
            //只有获取数据为空时，hasMore为false,所以拉到底部基本会首先显示“正在加载更多”
            if(hasMore==true) {
                //不隐藏footView提示
                fadeTips = false;
                if (datas.size() > 0) {
                    //若查询数据发现增加后，就显示正在加载更多
                    ((FootHolder) viewHolder).footView.setText("正在加载更多。。。");
                }
                }
                else {
                    if (datas.size() > 0)
                    { ((FootHolder) viewHolder).footView.setText("没有更多数据了。。。");


                    //通过延时加载模拟网络请求时间，1000ms后执行
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //隐藏提示条
                            ((FootHolder) viewHolder).footView.setVisibility(View.GONE);
                            fadeTips = true;
                            //true,是为再次拉到底，会优先显示加载更多
                            hasMore = true;
                        }
                    }, 1000);
                }
                }

        }
    }






    //暴露接口，改变fadeTips的方法
    public boolean isFadeTips()
    {
        return fadeTips;
    }

    //暴露接口，下拉刷新时，通过暴露方法将数据源置为空
    public void resetDatas()
    {
        datas=new ArrayList<>();
    }


    //暴露接口，更新数据源，并修改hasMore的值，若有增加数据，hasMore为true,否则为false
    public void updateList(List<String> newDatas, boolean hasMore)
    {
        //在原有的数据上增加新数据
        if(newDatas !=null)
        {
            datas.addAll(newDatas);
        }

        this.hasMore=hasMore;
        notifyDataSetChanged();
    }

}

