package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener httpCallbackListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);

                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    InputStream inputStream = httpURLConnection.getInputStream();
                    //读取获取到的输入流
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    if (httpCallbackListener != null) {
                        //回调onFinish方法
                        httpCallbackListener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (httpCallbackListener != null) {
                        //回调onError()方法
                        httpCallbackListener.onError(e);
                    }
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }


        }).start();

    }
}
