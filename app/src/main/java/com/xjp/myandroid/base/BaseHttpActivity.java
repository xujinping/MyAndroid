package com.xjp.myandroid.base;

import com.xjp.myandroid.network.HttpResult;
import com.xjp.myandroid.network.VolleyHttp;

import java.util.Map;

/**
 * Description:Activity 网络请求基础类，支持get，post 两种请求方式
 * User: xjp
 * Date: 2015/3/24
 * Time: 10:46
 */

public abstract class BaseHttpActivity extends BaseActivity implements HttpResult {

    /**
     * get方式的网络请求 网络返回值为特定的类
     *
     * @param url         请求的地址
     * @param clazz       解析json需要生成的类对象
     * @param <T>         需要生成的泛型类
     * @param requestCode 请求码
     */
    protected <T> void get(String url, Class<T> clazz, int requestCode) {
        VolleyHttp.getInstance().get(url, this, clazz, requestCode);
    }

    /**
     * post 方式的网络请求 网络返回值为特定的类
     *
     * @param url         请求的地址
     * @param params      请求的参数
     * @param headers     请求的头部信息
     * @param clazz       解析json需要生成的类对象
     * @param <T>         需要生成的泛型类
     * @param requestCode 请求码
     */
    protected <T> void post(String url, Map<String, String> params, Map<String, String> headers,
                            Class<T> clazz, int requestCode) {
        VolleyHttp.getInstance().post(url, this, params, headers, clazz, requestCode);
    }

    /**
     * get 方式请求网络，网络返回值为字符串
     *
     * @param url         请求的地址
     * @param requestCode 请求码
     */
    protected void get(String url, int requestCode) {
        VolleyHttp.getInstance().get(url, this, requestCode);
    }

    /**
     * post 方式请求网络，网络返回值为字符串
     *
     * @param url         请求的地址
     * @param params      请求的参数
     * @param headers     请求的头部信息
     * @param requestCode 请求码
     */
    protected void post(String url, Map<String, String> params, Map<String, String> headers, int requestCode) {
        VolleyHttp.getInstance().post(url, this, params, headers, requestCode);
    }


}
