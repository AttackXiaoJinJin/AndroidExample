package com.example.exampleaidl_cilent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SsoAuth mSsoAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //执行操作
        findViewById(R.id.sso_btn).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if( mSsoAuth==null)
                {
                    //绑定远程服务，并进行登录
                    bindSsoAuthService();
                }
                else
                {
                    doSsoAuth();
                }
                // onDestory();
            }
        });

    }

    private void bindSsoAuthService()
    {
        Intent intent = new Intent(".example_aidl_server.SinaSsoAuthService" );
        intent.setPackage("com.example.example_aidl_server");
        bindService(intent , mConnection , Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {//建立连接后将Binder转换为mSsoAuth
            mSsoAuth= SsoAuth.Stub.asInterface(iBinder);
            doSsoAuth();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mSsoAuth=null;
        }
    };

    private void doSsoAuth()
    {
        try
        {
            //执行登录，实际上调用的是Server端的ssoAuth函数
            mSsoAuth.ssoAuth("yuer","123");
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    protected void onDestory()
    {
        super.onDestroy();
        unbindService(mConnection);
    }

}

