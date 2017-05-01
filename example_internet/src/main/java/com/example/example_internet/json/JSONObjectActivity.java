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

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONObjectActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textjson;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_jsonobject);
        Button saxxml=(Button)findViewById(R.id.buttonjson);
        textjson=(TextView)findViewById(R.id.textjson);
        saxxml.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.buttonjson)
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
                    parseJSONwithJSONObject(responseData);


                }
                catch (Exception e)
                {e.printStackTrace();}

                }

        }).start();
    }

    private void parseJSONwithJSONObject(String jsonData)
    {
       try
       {
           JSONArray jsonArray=new JSONArray(jsonData);
           for(int i=0;i<jsonArray.length();i++)
           {
               JSONObject jsonObject=jsonArray.getJSONObject(i);
               String id=jsonObject.getString("id");
               String name=jsonObject.getString("name");
               String version=jsonObject.getString("version");
               Log.d("MainActivity","id is "+id);
               Log.d("MainActivity","name is "+name);
               Log.d("MainActivity","version is "+version);

           }



       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }


}
