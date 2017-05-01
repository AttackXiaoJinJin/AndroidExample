package com.example.example_thread_runnable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //waitAndNotifyAll();
       // joinDemo();
       // yieldDemo();








    }




    //private static Object sLockObject=new Object();

    //wait()
   /* static void waitAndNotifyAll()
    {
        System.out.print("主线程运行");
        //创建并启动子线程
        Thread thread=new WaitThread();
        thread.start();
        long startTime=System.currentTimeMillis();
        try
        {
            synchronized (sLockObject)
            {
                System.out.print("主线程等待");
                sLockObject.wait();
            }
        }
        catch (Exception e)
        {}
        long timesMs=(System.currentTimeMillis()-startTime);
        System.out.println("主线程继续-->等待耗时："+timesMs+"ms");


    }*/


    //等待线程
    //notifyAll()
   /* static class WaitThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                synchronized (sLockObject)
                {
                    Thread.sleep(3000);
                    sLockObject.notifyAll();;
                }

            }
            catch (Exception e)
            {}
        }
    }*/

    //join()
   /* static void joinDemo()
    {
        Worker worker1=new Worker("work-1");
        Worker worker2=new Worker("work-2");
        worker1.start();
        System.out.println("启动线程1");
        try
        {
            //调用worker1的join函数，主线程会阻塞直到worker1完成
            worker1.join();
            System.out.println("启动线程2");
            //再启动线程2，并调用join,主线程会阻塞直到worker2完成
            worker2.start();
            worker2.join();
        }
        catch (InterruptedException e)
        {e.printStackTrace();}
        System.out.print("主线程继续执行");
    }
*/

   /* static class Worker extends Thread
    {
        public Worker(String name)
        {
            super(name);
        }

        @Override
        public void run()
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {e.printStackTrace();}
            System.out.print("work in" + getName());
        }
    }*/

   /* static class  YieldThread extends Thread
    {   private int Max=5;
        public YieldThread(String name)
        {
            super(name);
        }

        public synchronized void run()
        {
            for(int i=0;i<Max;i++)
            {
                System.out.println(this.getName()+", 优先级为:"+this.getPriority()+"-----> "+ i);
                //当i为2时，调用当前线程的yield函数
               /* if(i == 2)
            { Thread.yield();
            }*/
        /*    }
        }
    }

    static void yieldDemo()
    {
        YieldThread t1=new YieldThread("thread-1");
        YieldThread t2=new YieldThread("thread-2");
        t1.start();
        System.out.println("启动线程1");
        t2.start();
        System.out.println("启动线程1");
    }*/


      /*  //testSimpleAsyncTask
        protected void async()

        {

            new SimpleAsyncTask<Stirng>()

            {
                private void makeToast(String msg)
                {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                protected void onPreExecute()
                {
                    makeToast("onPreExecute");
                }

                @Override
                protected String doInBackground()
                {
                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    return "Hello";
                }


                @Override
                protected void onPostExecute(String result)
                {
                    makeToast("onPostExecute : "+ result);
                }
            }.execute();

        }*/
}



