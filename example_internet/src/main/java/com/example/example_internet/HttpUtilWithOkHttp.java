package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/28.
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtilWithOkHttp {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build();
        okHttpClient.newCall(request).enqueue(callback);


    }
}
