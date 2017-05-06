package com.example.example_broadcast;

/*
 * Created by chenjin on 2017/5/5.
 */

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
//用来管理所有的活动
public class ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity)
    {
        activities.add(activity);
    }
    public static void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }
    public static void finishAll()
    {
        for(Activity activity: activities)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }

}
