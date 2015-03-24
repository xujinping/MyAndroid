package com.xjp.myandroid.network;

import com.android.volley.VolleyError;

/**
 * Description:网络请求返回结果的接口
 * User: xjp
 * Date: 2015/3/11
 * Time: 12:55
 */
public interface HttpResult<T> {
    public void onSuccess(T response, int requestCode);

    public void onFailed(VolleyError error);
}
