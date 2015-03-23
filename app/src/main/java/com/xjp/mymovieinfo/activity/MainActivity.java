package com.xjp.mymovieinfo.activity;

import android.widget.TextView;

import com.xjp.mymovieinfo.R;
import com.xjp.mymovieinfo.base.BaseActivity;
import com.xjp.mymovieinfo.widget.ViewInject;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/23
 * Time: 17:14
 */

public class MainActivity extends BaseActivity {

    @ViewInject(id = R.id.tv_title)
    TextView tv;


    @Override
    protected void initData() {
        tv.setText("测试框架集合");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
