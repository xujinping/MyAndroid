package com.xjp.myandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjp.myandroid.R;
import com.xjp.myandroid.widget.ViewHolder;

/**
 * Description: 适配器测试类
 * User: xjp
 * Date: 2015/3/23
 * Time: 20:10
 */

public class TestAdapter extends CustomAdapter<String> {

    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected View setHolderView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main, null);
        }

        //此处也无需在使用findViewById 繁琐查找，直接使用ViewHolder类即可。
        TextView tv = ViewHolder.get(convertView, R.id.tv_title);

        return convertView;
    }
}
