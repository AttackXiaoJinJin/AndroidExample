package com.example.materialdesign.cardview;

/*
 * Created by chenjin on 2017/4/29.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialdesign.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private List<Fruit> mfruitList;
    private Context context;


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        CardView cardView;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
        }

    }

    public FruitAdapter(List<Fruit> fruitList)
    {
        mfruitList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder viewHolder=new ViewHolder(view);







        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Fruit fruit=mfruitList.get(position);
       // viewHolder.fruitImage.setImageResource(fruit.getImageId());
        Glide.with(context).load(fruit.getImageId()).into(viewHolder.fruitImage);
        viewHolder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount()
    {
        return mfruitList.size();
    }




}
