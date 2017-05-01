package com.example.chatroom;

/*
 * Created by chenjin on 2017/4/30.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
   private List<Message> mMessageList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftlayout;
        LinearLayout rightlayout;

        TextView leftMessage;
        TextView rightMessage;

        public ViewHolder(View view)
        {
            super(view);
            leftlayout=(LinearLayout)view.findViewById(R.id.left_layout);
            rightlayout=(LinearLayout)view.findViewById(R.id.right_layout);
            leftMessage=(TextView)view.findViewById(R.id.left_message);
            rightMessage=(TextView)view.findViewById(R.id.right_message);

        }

    }

    public MessageAdapter(List<Message> messageList)
    {
        mMessageList=messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position)
    {
        Message message=mMessageList.get(position);
        if(message.getType()==Message.TYPE_RECEIVED)
        {
            //若是接收信息，则显示左边布局，将右边布局隐藏
            viewHolder.leftlayout.setVisibility(View.VISIBLE);
            viewHolder.rightlayout.setVisibility(View.GONE);
            viewHolder.leftMessage.setText(message.getContent());
        }
        else if (message.getType()==Message.TYPE_SEND)
        {

            viewHolder.rightlayout.setVisibility(View.VISIBLE);
            viewHolder.leftlayout.setVisibility(View.GONE);
            viewHolder.rightMessage.setText(message.getContent());
        }

    }


    @Override
    public int getItemCount()
    {
        return mMessageList.size();
    }



}
