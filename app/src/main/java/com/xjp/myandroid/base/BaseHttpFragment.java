package com.xjp.myandroid.base;

import com.xjp.myandroid.network.HttpResult;
import com.xjp.myandroid.network.VolleyHttp;

import java.util.Map;

/**
 * Description: Fragment 网络请求基础类
 * User: xjp
 * Date: 2015/3/24
 * Time: 11:16
 */

public abstract class BaseHttpFragment extends BaseFragment implements HttpResult {

    /**
     * get方式的网络请求
     *
     * @param url   请求的地址
     * @param clazz 解析json需要生成的类对象
     * @param <T>   需要生成的泛型类
     */
    protected <T> void get(String url, Class<T> clazz) {
        VolleyHttp.getInstance().get(url, this, clazz);
    }

    /**
     * post 方式的网络请求
     *
     * @param url     请求的地址
     * @param params  请求的参数
     * @param headers 请求的头部信息
     * @param clazz   解析json需要生成的类对象
     * @param <T>     需要生成的泛型类
     */
    protected <T> void post(String url, Map<String, String> params, Map<String, String> headers, Class<T> clazz) {
        VolleyHttp.getInstance().post(url, this, params, headers, clazz);
    }


}
