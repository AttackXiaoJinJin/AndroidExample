package com.example.example_aidl_server;

/*
 * Created by chenjin on 2017/4/8.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.exampleaidl_cilent.SsoAuth;


public class SinaSsoAuthService extends Service
{
    SinaSsoImpl mBinder=new SinaSsoImpl();

    public void onCreate()
    {
        super.onCreate();
        Log.e("","### sso auth created");
    }
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    //继承自Stub类，在这里实现ssoauth函数
    class SinaSsoImpl extends SsoAuth.Stub
    {
        public  void ssoAuth(String userName, String pwd) throws RemoteException
        {
            Log.e("", "这里是新浪客户端， 执行SSO登录啦，用户名："+ userName + ", 密码：" + pwd);
        }
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,double aDouble, String aString) throws RemoteException
        {}


    }

}
