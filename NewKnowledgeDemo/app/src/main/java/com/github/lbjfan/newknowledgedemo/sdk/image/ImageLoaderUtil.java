package com.github.lbjfan.newknowledgedemo.sdk.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/11.
 */

public class ImageLoaderUtil {

    private BaseImageLoadStrategy mBStrategy;
    private static ImageLoaderUtil mImageLoaderUtil;

    public ImageLoaderUtil() {
        mBStrategy = new GlideImageLoadStrategy();
    }

    public static ImageLoaderUtil getInstance() {
        if (mImageLoaderUtil == null) {
            synchronized (ImageLoaderUtil.class) {
                if (mImageLoaderUtil == null) {
                    mImageLoaderUtil = new ImageLoaderUtil();
                }
            }
        }
        return mImageLoaderUtil;
    }

    public void loadImage(String url, ImageView view) {
        mBStrategy.loadImage(url, view);
    }

    public void loadImage(String url, int placeHolder, ImageView view) {
        mBStrategy.loadImage(url, placeHolder, view);
    }

    public void loadCircleCornerImage(String url, ImageView view, int corner) {
        mBStrategy.loadCircleCornerImage(url, view, corner);
    }

    public void loadCircleCornerImage(String url, int placeHolder, ImageView view, int corner) {
        mBStrategy.loadCircleCornerImage(url, placeHolder, view, corner);
    }

    public void loadGifImage(String url, int placeHolder, ImageView view) {
        mBStrategy.loadGifImage(url, placeHolder, view);
    }

    public void clearImageDiskCache(Context context) {
        mBStrategy.clearImageDiskCache(context);
    }

    public void clearImageMemoryCache(Context context) {
        mBStrategy.clearImageMemoryCache(context);
    }

    public void trimMemory(Context context, int level) {
        mBStrategy.trimMemory(context, level);
    }

    public void getMemorySize(Context context) {
        mBStrategy.getMemorySize(context);
    }
}
