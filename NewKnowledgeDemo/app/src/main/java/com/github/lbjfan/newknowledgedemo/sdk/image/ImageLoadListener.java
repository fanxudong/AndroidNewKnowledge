package com.github.lbjfan.newknowledgedemo.sdk.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/15.
 */

public interface ImageLoadListener {

    void loadImage(Context context, ImageView view, String url);

    void loadCircleCornerImage(Context context, ImageView view, String url);

}
