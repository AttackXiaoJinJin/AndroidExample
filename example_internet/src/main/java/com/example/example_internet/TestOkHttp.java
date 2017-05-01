package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkHttp extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_okhttp);
        Button sendRequest=(Button)findViewById(R.id.send_reqiest);
        responseText=(TextView)findViewById(R.id.textresponse);
        sendRequest.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.send_reqiest)
        {
            sendRequestWithOhHttp();
        }
    }

    private void sendRequestWithOhHttp()
    {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                OkHttpClient okHttpClient=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("https://www.baidu.com/")
                            .build();
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);


                }
                catch (Exception e)
                {e.printStackTrace();}

                }

        }).start();
    }

    private void showResponse(final String response)
    {
        runOnUiThread(new Runnable(){
            @Override
            public void run()
            {
                //进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }


}
