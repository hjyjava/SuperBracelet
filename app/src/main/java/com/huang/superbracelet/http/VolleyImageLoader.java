package com.huang.superbracelet.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.huang.superbracelet.R;
import com.huang.superbracelet.base.MyApplication;

/**
 *
 * Created by 黄家远 on 16/5/6.
 */
public class VolleyImageLoader {

    private BitmapCache mBitmapCache;

    private static VolleyImageLoader mInstance;

    public static VolleyImageLoader getInstance() {

        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new VolleyImageLoader();
                }
            }
        }
        return mInstance;
    }

    private VolleyImageLoader() {
        mBitmapCache = new BitmapCache();
    }

    public void loadImage(String url, ImageView imageView){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueues(),mBitmapCache);
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.drawable.default_icon,R.drawable.default_icon);
        imageLoader.get(url,imageListener);
    }

    private class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheMemory = maxMemory / 8;
            mCache = new LruCache<String, Bitmap>(cacheMemory) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }
}
