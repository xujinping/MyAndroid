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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

/**
 * A canned request for retrieving the response body at a given URL as a String.
 */
public class StringRequest extends Request<String> {
	private final Listener<String> mListener;
	private final Map<String, String> mParams;
	private Map<String, String> mHeaders;

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
	public StringRequest(int method, String url, Map<String, String> params,
			Map<String, String> headers, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
		mParams = params;
		mHeaders = headers;
	}

	/**
	 * Creates a new GET or POST request, if request params is null the request
	 * is GET otherwise POST request.
	 * 
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
	public StringRequest(String url, Map<String, String> params,
			Map<String, String> headers, Listener<String> listener,
			ErrorListener errorListener) {
		this(null == params ? Method.GET : Method.POST, url, params, headers,
				listener, errorListener);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mParams;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		if (null == mHeaders){
			mHeaders = Collections.emptyMap();
		}
		return mHeaders;
	}

	@Override
	protected void deliverResponse(String response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		String parsed;
		try {
			parsed = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			parsed = new String(response.data);
		}
		return Response.success(parsed,
				HttpHeaderParser.parseCacheHeaders(response));
	}
}
