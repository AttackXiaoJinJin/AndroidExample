package com.example.example_recyclerview.implerecyclerview;

/*
 * Created by chenjin on 2017/4/29.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_recyclerview.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private List<Fruit> mfruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        View fruitView;

        public ViewHolder(View view)
        {
            super(view);
            fruitView=view;
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.fruitView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=mfruitList.get(position);

                Toast.makeText(view.getContext(), "you clicked view"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.fruitImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=mfruitList.get(position);

                Toast.makeText(view.getContext(), "you clicked image"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Fruit fruit=mfruitList.get(position);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount()
    {
        return mfruitList.size();
    }




}
