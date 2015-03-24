/*
 * Copyright (C) 2011 The Android Open Source Project Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.android.volley.toolbox;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 * 
 * @param <T>
 */
public class FastJsonRequest<T> extends Request<T> {
	private final Listener<T> mListener;
	private final Map<String, String> mParams;
	private Map<String, String> mHeaders;
	private Class<T> mClass;

	/**
	 * Creates a new request with the given method.
	 * 
	 * @param method
	 *            the request {@link Method} to use
	 * @param url
	 *            URL to fetch the string at
	 * @param params
	 * 			  Params for the POST request.
	 * @param headers
	 * 			  Headers for the POST request.
	 * @param listener
	 *            Listener to receive the String response
	 * @param errorListener
	 *            Error listener, or null to ignore errors
	 */
	public FastJsonRequest(int method, String url, Map<String, String> params,
			Map<String, String> headers, Class<T> mClass, Listener<T> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
		mParams = params;
		mHeaders = headers;
		this.mClass = mClass;
	}

	/**
	 * Creates a new GET or POST request, if request params is null the request
	 * is GET otherwise POST request.
	 *  @param url
	 *            URL to fetch the string at
	 * @param params
	 * 			  Params for the POST request.
     * @param headers
 * 			  Headers for the POST request.
     * @param mClass
     * @param listener
     *            Listener to receive the String response
     * @param errorListener
     */
	public FastJsonRequest(String url, Map<String, String> params,
			Map<String, String> headers, Class<T> mClass, Listener<T> listener,
			ErrorListener errorListener) {
		this(null == params ? Method.GET : Method.POST, url, params, headers,
				mClass, listener, errorListener);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mParams;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		if (null == mHeaders) {
			mHeaders = Collections.emptyMap();
		}
		return mHeaders;
	}

	@Override
	protected void deliverResponse(T response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					com.android.volley.toolbox.HttpHeaderParser.parseCharset(response.headers));
			return Response.success(JSON.parseObject(jsonString, mClass),
					com.android.volley.toolbox.HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
	}
}
