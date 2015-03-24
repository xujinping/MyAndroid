package com.xjp.myandroid.network;


import android.volley.mytools.ImageCache;
import android.volley.toolbox.StringRequest;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.FastJsonRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.xjp.myandroid.application.MyApplication;
import com.xjp.myandroid.utils.MyLog;

import java.util.Map;

import static com.android.volley.Response.ErrorListener;

/**
 * Description:使用Google 的Volley框架请求网络图片和Json数据
 * User: xjp
 * Date: 2015/3/24
 * Time: 9:11
 */
public class VolleyHttp {
    public RequestQueue mQueue;
    public ImageLoader imageLoader;
    public static VolleyHttp instance = null;


    public static synchronized VolleyHttp getInstance() {
        if (null == instance) {
            instance = new VolleyHttp();
        }
        return instance;
    }

    private VolleyHttp() {
        mQueue = Volley.newRequestQueue(MyApplication.instance);
        imageLoader = new ImageLoader(mQueue, new ImageCache());
    }

    /**
     * 加载网络图片
     *
     * @param imageView
     * @param strImgUrl
     * @param defaultImageResId
     * @param failedImageResId
     */
    public void displayImage(NetworkImageView imageView, String strImgUrl,
                             int defaultImageResId, int failedImageResId) {
        imageView.setDefaultImageResId(defaultImageResId);
        imageView.setErrorImageResId(failedImageResId);
        imageView.setImageUrl(strImgUrl, imageLoader);
    }


    /**
     * 网络json数据请求和解析 get方法
     *
     * @param url
     * @param httpResult
     * @param requestCode 请求码
     */
    public void get(String url, HttpResult httpResult, int requestCode) {
        executeHttp(url, httpResult, null, null, requestCode);
    }

    /**
     * 网络json数据请求和解析 post方法
     *
     * @param url
     * @param httpResult
     * @param params
     * @param headers
     * @param requestCode 请求码
     */
    public void post(String url, HttpResult httpResult, Map<String, String> params,
                     Map<String, String> headers, int requestCode) {
        executeHttp(url, httpResult, params, headers, requestCode);
    }


    /**
     * Volley 网络框架请求，网络返回的值是字符串
     *
     * @param url
     * @param httpResult
     * @param params
     * @param headers
     * @param requestCode 请求码
     */
    private void executeHttp(String url, final HttpResult httpResult, Map<String, String> params,
                             Map<String, String> headers, final int requestCode) {
        MyLog.d(this.getClass().getCanonicalName(), url);
        StringRequest stringRequest = new StringRequest(url, params, headers, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                httpResult.onSuccess(response, requestCode);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpResult.onFailed(error);
            }
        });
        stringRequest.setTag("tag");
        mQueue.add(stringRequest);
    }


    /**
     * 网络json数据请求和解析 get方法
     *
     * @param url
     * @param httpResult
     * @param clazz
     * @param <T>
     * @param requestCode 请求码
     */
    public <T> void get(String url, HttpResult httpResult, Class<T> clazz, int requestCode) {
        executeHttp(url, httpResult, null, null, clazz, requestCode);
    }


    /**
     * 网络json数据请求和解析 post方法
     *
     * @param url
     * @param httpResult
     * @param params
     * @param headers
     * @param clazz
     * @param <T>
     * @param requestCode 请求码
     */
    public <T> void post(String url, HttpResult httpResult, Map<String, String> params,
                         Map<String, String> headers, Class<T> clazz, int requestCode) {
        executeHttp(url, httpResult, params, headers, clazz, requestCode);
    }

    /**
     * Volley 框架执行网络请求 和解析Json数据
     *
     * @param url
     * @param httpResult
     * @param params
     * @param headers
     * @param clazz
     * @param <T>
     * @param requestCode 请求码
     */
    private <T> void executeHttp(String url, final HttpResult httpResult, Map<String, String> params,
                                 Map<String, String> headers, Class<T> clazz, final int requestCode) {
        MyLog.d(this.getClass().getCanonicalName(), url);
        FastJsonRequest<T> fastJsonRequest = new FastJsonRequest<T>(url, params, headers, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                httpResult.onSuccess(response, requestCode);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpResult.onFailed(error);
            }
        });
        fastJsonRequest.setTag("tag");
        mQueue.add(fastJsonRequest);
    }

    /**
     * TODO<取消所有含有Object tag标记的请求,退出线程循环>
     *
     * @param
     * @return void
     * @throw
     */
    public void cancelAll() {
        mQueue.cancelAll("tag");
        mQueue.stop();
    }

}
