package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/28.
 */

public interface HttpCallbackListener
{
    void onFinish(String response);
    void onError(Exception e);
}