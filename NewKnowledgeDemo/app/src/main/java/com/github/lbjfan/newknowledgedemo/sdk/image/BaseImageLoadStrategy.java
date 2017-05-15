package com.github.lbjfan.newknowledgedemo.sdk.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片加载策略(Java设计模式-策略模式)
 * Created by Administrator on 2017/5/15.
 */

public interface BaseImageLoadStrategy {

    /**
     * 正常加载图片
     *
     * @param url
     * @param view
     */
    void loadImage(String url, ImageView view);

    /**
     * 占位图和加载失败时显示的图
     *
     * @param url：网路图片的Url地址
     * @param placeHolder：默认占位图和加载失败之后的图片资源ID
     * @param view：图片
     */
    void loadImage(String url, int placeHolder, ImageView view);

    /**
     * 加载圆角图片
     *
     * @param url:网络图片的Url
     * @param view：显示图片的View
     * @param corner:圆角的度数
     */
    void loadCircleCornerImage(String url, ImageView view, int corner);

    /**
     * 加载圆角图片
     *
     * @param url:网络图片的Url
     * @param view：显示图片的View
     * @param placeHolder:默认占位图和加载失败之后的图片资源ID
     * @param corner:圆角的度数
     */
    void loadCircleCornerImage(String url, int placeHolder, ImageView view, int corner);

    /**
     * 加载GIF图片
     *
     * @param url：GIF图片的Url
     * @param placeHolder：默认占位图和加载失败之后的图片资源ID
     * @param view：显示的View
     */
    void loadGifImage(String url, int placeHolder, ImageView view);

    /**
     * 清除图片磁盘缓存
     *
     * @param context：上下文对象
     */
    void clearImageDiskCache(Context context);

    /**
     * 清除图片内存缓存
     *
     * @param context
     */
    void clearImageMemoryCache(Context context);

    /**
     * 根据不同的内存状态，来进行不同的释放策略
     *
     * @param context
     * @param level
     */
    void trimMemory(Context context, int level);

    /**
     * 获取缓存大小
     *
     * @param context
     */
    void getMemorySize(Context context);

}
