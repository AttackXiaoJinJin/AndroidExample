package com.example.example_internet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.example_internet.json.GsonActivity;
import com.example.example_internet.json.JSONObjectActivity;
import com.example.example_internet.saxxml.SAXXmlActivity;
import com.example.example_internet.webview.WebViewActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用HttpUtil
        HttpUtil.sendHttpRequest("https://www.baidu.com/", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

            }

            @Override
            public void onError(Exception e) {

            }
        });


        //调用HttpUtilWithOkHttp
        HttpUtilWithOkHttp.sendOkHttpRequest("https://www.sina.com.cn/", new okhttp3.Callback()
        {
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                //得到服务器返回的具体内容
                String responseData=response.body().toString();
            }

            @Override
            public void onFailure(Call call, IOException e)
            {
                //对异常情况的处理
            }

        });



    }


    public void onClickWebView(View view)
    {
        startActivity(new Intent(getApplicationContext(), WebViewActivity.class));
    }


    public void onClickHttpURLConnection(View view)
    {
        startActivity(new Intent(getApplicationContext(), TestHttpURLActivity.class));
    }

    public void onClickOkHttp(View view)
    {
        startActivity(new Intent(getApplicationContext(), TestOkHttp.class));
    }



    public void  onClickPullXml(View view)
    {
        startActivity(new Intent(getApplicationContext(), TestPullXml.class));
    }


    public void  onClickSAXXml(View view)
    {
        startActivity(new Intent(getApplicationContext(), SAXXmlActivity.class));
    }


    public void  onClickJSONObject(View view)
    {
        startActivity(new Intent(getApplicationContext(), JSONObjectActivity.class));
    }

    public void  onClickGSON(View view)
    {
        startActivity(new Intent(getApplicationContext(), GsonActivity.class));
    }



}
