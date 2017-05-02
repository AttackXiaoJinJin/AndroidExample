package com.example.materialdesign.collapsingtoolbarlayout;

/*
 * Created by chenjin on 2017/4/29.
 */

import android.content.Context;
import android.content.Intent;
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
   // private static final String TAG = "FruitAdapter";

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
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                int position=holder.getAdapterPosition();
                Fruit fruit=mfruitList.get(position);
                Intent intent=new Intent(context, FruitActivity.class);

                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                context.startActivity(intent);

            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Fruit fruit=mfruitList.get(position);
       // viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(viewHolder.fruitImage);

    }

    @Override
    public int getItemCount()
    {
        return mfruitList.size();
    }


}
