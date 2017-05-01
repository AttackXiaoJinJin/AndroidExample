package com.example.chatroom;

/*
 * Created by chenjin on 2017/4/30.
 */

public class Message {
    public static final int TYPE_RECEIVED=0;//左
    public static final int TYPE_SEND=1;//右
    private String content;
    private int type;

    public Message(String content, int type)
    {
        this.content=content;
        this.type=type;
    }

    public String getContent()
    {
        return content;
    }

    public int getType()
    {
        return type;
    }

}
