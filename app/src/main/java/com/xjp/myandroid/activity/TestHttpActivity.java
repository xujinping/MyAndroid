package com.xjp.myandroid.activity;

import android.widget.TextView;

import com.android.volley.VolleyError;
import com.xjp.myandroid.R;
import com.xjp.myandroid.base.BaseHttpActivity;
import com.xjp.myandroid.widget.ViewInject;

/**
 * Description:网络请求测试
 * User: xjp
 * Date: 2015/3/24
 * Time: 14:53
 */

public class TestHttpActivity extends BaseHttpActivity {

    @ViewInject(id = R.id.tv_title)
    TextView tv;
    @ViewInject(id = R.id.tv_content)
    TextView tvContent;

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {
        get("http://www.baidu.com", 1);
        get("http://www.weather.com.cn/adat/cityinfo/101010100.html", 2);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object response, int requestCode) {
        switch (requestCode) {
            case 1:
                tvContent.setText(response.toString());
                break;
            case 2:
                tv.setText(response.toString());
                break;
        }
    }

    @Override
    public void onFailed(VolleyError error) {

    }
}
