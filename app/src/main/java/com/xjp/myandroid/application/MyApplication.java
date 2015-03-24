package com.xjp.myandroid.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:整个应用的入口
 * User: xjp
 * Date: 2015/3/23
 * Time: 17:02
 */

public class MyApplication extends Application {

    private List<Activity> activityList;
    public static MyApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        activityList = new ArrayList<>();
        super.onCreate();
    }

    /**
     * 保存已新建的Activity实例
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        synchronized (this) {
            if (null != activityList) {
                activityList.add(activity);
            }
        }
    }

    /**
     * 移除队列中的activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        synchronized (this) {
            if (null != activityList && activityList.size() > 0) {
                activityList.remove(activity);
            }
        }
    }

    /**
     * 完全退出整个应用
     */
    public void closeAllActivity() {
        if (null != activityList) {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
    }
}
