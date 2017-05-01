package com.example.example_synchronizeddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyArrayBlockingQueue<Integer> aQueue=new MyArrayBlockingQueue<>();
        aQueue.put(3);
        aQueue.put(24);
        for(int i=0;i<5;i++)
        {
            System.out.println(aQueue.take());
        }
    }

/*
    public class SynchronizedDemo
    {
        //同步方法
        public synchronized  void syncMethd()
        {
            //代码
        }

        //同步块
        public void syncThis()
        {
            synchronized (this);
            //代码
        }

        //同步class对象
        public void syncClassMethod()
        {
            synchronized (SynchronizedDemo.class)
            {
                //代码
            }
        }


        //同步静态方法
        public synchronized static void syncStaticMethod()
        {
            //代码
        }


        //ReentrantLock
        Lock lock=new ReentrantLock();
        public void doSth()
        {
            lock.lock();
            try{
                //执行某些操作
            }
            finally {
                lock.unlock();
            }
        }*/



        public class MyArrayBlockingQueue<T>
        {
            //数据数组
            private final T[] items;
            //锁
            private final Lock lock=new ReentrantLock();
            //队满的条件
            private Condition full =lock.newCondition();
            //队空的条件
            private Condition empty=lock.newCondition();
            //头部索引
            private  int head;
            //尾部索引
            private int tail;
            //数据的个数
            private int count;

            public MyArrayBlockingQueue(int maxSize)
            {
                items=(T[]) new Object[maxSize];
            }

            public MyArrayBlockingQueue()
            {
                this(10);
            }

            public void put(T t)
            {
                lock.lock();
                try
                {
                    while(count==getCapacity())
                    {
                        System.out.println("数据已满，等待");
                        full.await();
                    }
                    items[tail]=t;
                    if(++tail==getCapacity())
                    {
                        tail=0;
                    }
                    ++count;
                    //唤醒等待数据的线程
                    empty.signalAll();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
            }
        }


        public T take()
        {
            lock.lock();
            try
            {
                while(count==0)
                {
                    System.out.println("还没有数据，请等待");
                    empty.await();
                }
                T ret=items[head];
                items[head]=null;
                if(++head==getCapacity())
                {
                    head=0;
                }
                --count;
                //唤醒添加数据的线程
                full.signalAll();
                return ret;
            }
            catch (InterruptedException e)
            {e.printStackTrace();}
            finally {
                lock.unlock();
            }
            return null;
        }

        public int getCapacity()
        {
            return items.length;
        }

        public int size()
        {
            lock.lock();
            try
            {
                return count;
            }
            finally {
                lock.unlock();
            }
        }


    }
}
