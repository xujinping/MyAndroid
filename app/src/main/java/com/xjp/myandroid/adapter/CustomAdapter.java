package com.xjp.myandroid.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 客户化基础类通用适配器
 * User: xjp
 * Date: 2015/3/23
 * Time: 17:29
 */

public abstract class CustomAdapter<T> extends BaseAdapter {

    private List<T> mList = new ArrayList<>();


    /**
     * 初始化适配器数据
     *
     * @param list
     */
    public void initAllData(List<T> list) {
        mList.addAll(list);
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return setHolderView(position, convertView, parent);
    }

    /**
     * 客户化View 每个适配器实现这个方法去加载布局
     *
     * @return
     */
    protected abstract View setHolderView(int position, View convertView, ViewGroup parent);
}
