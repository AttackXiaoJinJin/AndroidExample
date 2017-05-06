package com.example.example_chapter13;

/*
 * Created by chenjin on 2017/5/4.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
    private String name;
    private int age;
    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public void setAge(int age)
    {
        this.age=age;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        //写出name
        dest.writeString(name);
        //写出age
        dest.writeInt(age);
    }
                                                          //接口的一个实现
    public static final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>()
    {
        @Override
        public Person createFromParcel(Parcel source)
        {
            /*//写出name
        dest.writeString(name);
        //写出age
        dest.writeInt(age);*/
            //一定跟上面所写顺序相同
            Person person=new Person();
            //读取name
            person.name=source.readString();
            //读取age
            person.age=source.readInt();
            return person;
        }

        @Override
        public Person[] newArray(int size)
        {
            return new Person[size];
        }

    };



}
