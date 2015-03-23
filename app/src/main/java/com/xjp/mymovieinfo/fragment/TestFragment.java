package com.xjp.mymovieinfo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjp.mymovieinfo.R;
import com.xjp.mymovieinfo.base.BaseActivity;
import com.xjp.mymovieinfo.base.BaseFragment;
import com.xjp.mymovieinfo.widget.ViewInject;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/23
 * Time: 19:45
 */

public class TestFragment extends BaseFragment {

    @ViewInject(id = R.id.tv_title)
    TextView tv;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.activity_main, container, false);
        BaseActivity.initInjectedView(this, viewRoot);
        return viewRoot;
    }

    @Override
    protected void getBundle() {

    }
}
