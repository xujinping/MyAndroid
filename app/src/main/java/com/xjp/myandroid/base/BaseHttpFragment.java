package com.xjp.myandroid.base;

import android.text.TextUtils;

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
     * get方式的网络请求 网络返回值为特定的类
     *
     * @param url         请求的地址
     * @param clazz       解析json需要生成的类对象
     * @param <T>         需要生成的泛型类
     * @param requestCode 请求码
     */
    protected <T> void get(String url, Class<T> clazz, int requestCode) {
        checkUrl(url);
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
        checkUrl(url);
        VolleyHttp.getInstance().post(url, this, params, headers, clazz, requestCode);
    }

    /**
     * get 方式请求网络，网络返回值为字符串
     *
     * @param url         请求的地址
     * @param requestCode 请求码
     */
    protected void get(String url, int requestCode) {
        checkUrl(url);
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
        checkUrl(url);
        VolleyHttp.getInstance().post(url, this, params, headers, requestCode);
    }


    /**
     * 检查url是否有效
     *
     * @param url
     */
    private void checkUrl(String url) {
        if (!TextUtils.isEmpty(url) || (url != null && !url.contains("http://"))) {
            throw new RuntimeException("无效的网络请求地址");
        }
    }


}
