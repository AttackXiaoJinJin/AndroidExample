package com.example.example_internet;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestPullXml extends AppCompatActivity implements View.OnClickListener{
    TextView textpullxml;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_pull_xml);
        Button sendRequest=(Button)findViewById(R.id.pullxml);
        textpullxml=(TextView)findViewById(R.id.textpullxml);
        sendRequest.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.pullxml)
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
                            .url("http://10.0.2.2/get_data.xml")
                            .build();
                    Response response=okHttpClient.newCall(request).execute();
                    String responseData=response.body().string();
                   parseXMLWithPull(responseData);


                }
                catch (Exception e)
                {e.printStackTrace();}

                }

        }).start();
    }

   /* private void showResponse(final String response)
    {
        runOnUiThread(new Runnable(){
            @Override
            public void run()
            {
                //进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }*/

   private void parseXMLWithPull(String xmlData)
   {
       try{
           XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
           XmlPullParser xmlPullParser=xmlPullParserFactory.newPullParser();
           xmlPullParser.setInput(new StringReader(xmlData));
           int eventType=xmlPullParser.getEventType();
           String id="";
           String name="";
           String version="";
           while (eventType!=XmlPullParser.END_DOCUMENT)
           {
               String nodeName=xmlPullParser.getName();
               switch(eventType)
               {
                   //开始解析某个节点
                   case XmlPullParser.START_TAG:{
                       if("id".equals(nodeName)){
                           id=xmlPullParser.nextText();
                       }
                       else if("name".equals(nodeName))
                       {
                           name=xmlPullParser.nextText();
                       }
                       else if("version".equals(nodeName))
                       {
                           version=xmlPullParser.nextText();
                       }
                       break;
                   }

                   case XmlPullParser.END_TAG:{
                       if("app".equals(nodeName)){
                           Log.d("MainActivity", "id is "+id);
                           Log.d("MainActivity", "name is "+name);
                           Log.d("MainActivity", "version is "+version);
                       }
                       break;
                   }

                   default:break;
               }

               eventType=xmlPullParser.next();



           }


       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
   }





}
