package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpURLActivity extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_httpurl);
        Button sendRequest=(Button)findViewById(R.id.send_reqiest);
        responseText=(TextView)findViewById(R.id.textresponse);
        sendRequest.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.send_reqiest)
        {
            sendRequestWithHttpURLConnection();
        }
    }

    private void sendRequestWithHttpURLConnection()
    {
        //开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection=null;
                BufferedReader bufferedReader=null;
                try
                {
                    URL url=new URL("https://www.baidu.com/");
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream inputStream=httpURLConnection.getInputStream();
                    //读取获取到的输入流
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    showResponse(response.toString());
                }
                catch (Exception e)
                {e.printStackTrace();}
                finally {
                    if(bufferedReader!=null) {
                        try {
                               bufferedReader.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                    }

                    if(httpURLConnection!=null)
                    {httpURLConnection.disconnect();}
                }
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
