package com.xjp.myandroid.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xjp.myandroid.activity.MainActivity;
import com.xjp.myandroid.application.MyApplication;
import com.xjp.myandroid.utils.MyLog;
import com.xjp.myandroid.widget.ViewInject;

import java.lang.reflect.Field;

/**
 * Description:Activity 基础类，便于控制activity生命周期和代码复用
 * User: xjp
 * Date: 2015/3/23
 * Time: 16:48
 */

public abstract class BaseActivity extends Activity {
    private static String TAG;
    //是否隐藏ActionBar
    protected boolean isHideActionBar = false;

    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        showLog("===onCreate===");
        MyApplication.instance.addActivity(this);
        actionBar = getActionBar();
        //是否隐藏 ActionBar
        if (null != actionBar && isHideActionBar) {
            actionBar.hide();
        }
        //显示返回图标
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(getContentView());

        initData();
        loadData();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("===onStart===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLog("===onRestart===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("===onResume===");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("===onPause===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("===onStop===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("===onDestroy===");
        MyApplication.instance.removeActivity(this);
        if (this instanceof MainActivity) {
            MyApplication.instance.closeAllActivity();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!(this instanceof MainActivity)) {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 打印日志
     *
     * @param msg
     */
    protected void showLog(String msg) {
        MyLog.d(TAG, msg);
    }

    /**
     * 显示提示
     *
     * @param msg
     */
    protected void showTost(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示提示
     *
     * @param msgId
     */
    protected void showToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
    }

    /**
     * activity 跳转
     *
     * @param clzz
     */
    protected void startActivity(Class<?> clzz) {
        Intent intent = new Intent(this, clzz);
        this.startActivity(intent);
    }


    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initInjectedView(this);
    }


    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initInjectedView(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        initInjectedView(this);
    }

    public static void initInjectedView(Activity activity) {
        initInjectedView(activity, activity.getWindow().getDecorView());
    }


    /**
     * 反射实现依赖注解UI 代替繁琐的 findViewById 方法。
     * @param injectedSource
     * @param sourceView
     */
    public static void initInjectedView(Object injectedSource, View sourceView) {
        Field[] fields = injectedSource.getClass().getDeclaredFields();
        int length = fields.length;
        if (fields != null && length > 0) {
            for (Field field : fields) {
                try {
                    field.setAccessible(true);

                    if (field.get(injectedSource) != null)
                        continue;

                    ViewInject viewInject = field.getAnnotation(ViewInject.class);
                    if (viewInject != null) {
                        int viewId = viewInject.id();
                        field.set(injectedSource, sourceView.findViewById(viewId));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 抽象方法加载布局，每个子类都需要实现这个方法来加载布局
     * 在 onCreate()方法中使用setContentView (getContentView());
     */
    protected abstract int getContentView();
}
