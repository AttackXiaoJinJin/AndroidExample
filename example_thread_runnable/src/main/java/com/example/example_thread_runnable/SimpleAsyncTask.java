package com.example.example_thread_runnable;

/*
 * Created by chenjin on 2017/4/26.
 */

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public abstract class SimpleAsyncTask<Result> {
    //HandlerThread内部封装了自己的Handler和Thread,有单独的looper和消息队列
    private static final HandlerThread HT=new HandlerThread(SimpleAsyncTask.class.getName(), android.os.Process.THREAD_PRIORITY_BACKGROUND);

    static
    {
        HT.start();
    }


    //获取调用execute的线程的Looper,构建Handler
    final Handler mUIHandler= new Handler(Looper.getMainLooper());

    //与异步线程队列关联的Handler
    final Handler mAsyncHandler=new Handler(HT.getLooper());

    //onPreExecute任务执行之前的初始化操作等
    protected void onPreExecute(){}

    //doInBackground后台执行任务，返回执行结果
    protected abstract Result doInBackground();

    //doInBackground返回结果传递给执行在UI线程的onPostExecute,执行结果
    protected void onPostExecute(Result result){}

    //execute方法，执行任务，调用doInBackground,并将结果投递给UI线程，使用户可以在onPostExecute处理结果
    public final SimpleAsyncTask<Result> execute()
    {
        onPreExecute();
        //将任务投递到HandlerThread线程中执行
        mAsyncHandler.post(new Runnable() {
            @Override
            public void run() {
                //后台执行任务，完成后向UI线程POST数据，用以更新UI等操作
                postResult(doInBackground());
            }
        });

        return this;
    }

    private void postResult(final Result result)
    {
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(result);
            }
        });
    }



}
