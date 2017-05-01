package com.example.example_internet.saxxml;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.example_internet.R;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SAXXmlActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textsaxxml;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_sax_xml);
        Button saxxml=(Button)findViewById(R.id.saxxml);
        textsaxxml=(TextView)findViewById(R.id.textsaxxml);
        saxxml.setOnClickListener(this);



    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.saxxml)
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
                    parseXMLWithSAX(responseData);


                }
                catch (Exception e)
                {e.printStackTrace();}

                }

        }).start();
    }

    private void parseXMLWithSAX(String xmlData)
    {
       try
       {
           SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
           XMLReader xmlReader=saxParserFactory.newSAXParser().getXMLReader();
           ContentHandler contentHandler=new ContentHandler();
           //将ContentHandler的实例设置到XMLReader中
           xmlReader.setContentHandler(contentHandler);
           //开始执行解析
           xmlReader.parse(new InputSource(new StringReader(xmlData)));



       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }


}
