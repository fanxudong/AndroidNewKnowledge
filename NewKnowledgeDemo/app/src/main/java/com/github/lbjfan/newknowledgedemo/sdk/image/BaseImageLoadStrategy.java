package com.github.lbjfan.newknowledgedemo.sdk.image;

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
     * @param url：网路图片的Url地址
     * @param placeHolder：加载失败时需要显示的图片ID
     * @param view：图片
     */
    void loadImage(String url, int placeHolder, ImageView view);
}
