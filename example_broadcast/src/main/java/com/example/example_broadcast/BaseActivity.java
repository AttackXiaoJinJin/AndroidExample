package com.example.example_broadcast;

/*
 * Created by chenjin on 2017/5/5.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
//所有活动的父类
public class BaseActivity extends AppCompatActivity{
    private ForceOffLineReceiver forceOffLineReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(".FORCE_OFFLINE");
        forceOffLineReceiver=new ForceOffLineReceiver();
        registerReceiver(forceOffLineReceiver, intentFilter);


    }


    @Override
    protected void onPause()
    {
        super.onPause();
        if(forceOffLineReceiver!=null)
        {
            unregisterReceiver(forceOffLineReceiver);
            forceOffLineReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOffLineReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(final Context context, Intent intent)
        {   //对话框的对象
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            //设置标题
            builder.setTitle("Warning");
            //设置message
            builder.setMessage("You are forced to be offline.Please try to login again.");
            //重要！不可取消
            builder.setCancelable(false);
            //注册确定按钮
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //销毁所有活动
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context, LoginActivity.class);
                    //重新启动LoginActivity
                    context.startActivity(intent);
                }
            } );

            builder.show();

        }
    }

}
