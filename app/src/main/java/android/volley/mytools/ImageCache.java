package android.volley.mytools;/*
 * @Title: ImageCache.java
 * @Copyright: MKTech Corporation. Ltd. Copyright 1998-2018, All rights reserved
 * @Description: TODO<请描述此文件是做什么的>
 * @author: xjp
 * @data: 2014年7月31日 上午8:50:16
 * @version: V1.0
 */

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * TODO<请描述这个类是干什么的>
 * 
 * @author xjp
 * @data: 2014年7月31日 上午8:50:16
 * @version: V1.0
 */
public class ImageCache implements
		com.android.volley.toolbox.ImageLoader.ImageCache {

	private String TAG = "ImageCache";
	private LruCache<String, Bitmap> mMemoryCache = null;

	@SuppressLint("NewApi")
	public ImageCache() {
		// TODO Auto-generated constructor stub
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheMemory = maxMemory / 10;
		// int cacheMemory = 10 * 1024 * 1024;

		mMemoryCache = new LruCache<String, Bitmap>(cacheMemory) {

			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getHeight() * bitmap.getWidth();
			}

		};
	}

	@SuppressLint("NewApi")
	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
//		Log.e(TAG, "get----->>>" + url);
		return mMemoryCache.get(url);
	}

	@SuppressLint("NewApi")
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		if (mMemoryCache.get(url) == null && bitmap != null) {
//			Log.e(TAG, url);
			mMemoryCache.put(url, bitmap);
		}
	}
}
