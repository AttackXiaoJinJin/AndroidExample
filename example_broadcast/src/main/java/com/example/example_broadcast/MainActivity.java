package com.example.example_broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//extends AppCompatActivity
public class MainActivity extends BaseActivity {
   // private IntentFilter intentFilter;
  //  private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  intentFilter=new IntentFilter();
        //想要监听什么广播，就添加什么
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
     */
        Button forceoffline=(Button)findViewById(R.id.force_offline);
        forceoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(".FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }


    class NetworkChangeReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //Toast.makeText(context, "networkChanges", Toast.LENGTH_SHORT).show();
            //系统服务类，专门用来管理网络连接
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isAvailable())
            {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

}
