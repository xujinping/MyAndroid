package com.xjp.myandroid.activity;

import android.widget.TextView;

import com.xjp.myandroid.R;
import com.xjp.myandroid.base.BaseActivity;
import com.xjp.myandroid.widget.ViewInject;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/23
 * Time: 17:14
 */

public class MainActivity extends BaseActivity {
    //此处就是使用注解查找id。
    @ViewInject(id = R.id.tv_title)
    TextView tv;


    @Override
    protected void initData() {
        //TODO 初始化一些数据
        tv.setText("测试框架集合");
    }

    @Override
    protected void loadData() {
        //TODO 加载一些数据，例如网络请求等。
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
