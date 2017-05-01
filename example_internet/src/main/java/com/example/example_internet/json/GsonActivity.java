package com.example.example_internet.json;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.example_internet.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GsonActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textgson;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_gson);
        Button buttongson=(Button)findViewById(R.id.buttongson);
        textgson=(TextView)findViewById(R.id.textgson);
        buttongson.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.buttongson)
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
                            .url("http://10.0.2.2/get_json_data.json")
                            .build();
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                    parseGSONwithGSON(responseData);


                }
                catch (Exception e)
                {e.printStackTrace();}


                }

        }).start();
    }

    private void parseGSONwithGSON(String jsonData)
    {

        Gson gson=new Gson();
        List<GsonApp> appList=gson.fromJson(jsonData, new TypeToken<List<GsonApp>>(){}.getType());
        for(GsonApp gsonApp : appList) {
            Log.d("MainActivity", "id is " + gsonApp.getId());
            Log.d("MainActivity", "name is " + gsonApp.getName());
            Log.d("MainActivity", "version is " + gsonApp.getVersion());

        }
    }


}
