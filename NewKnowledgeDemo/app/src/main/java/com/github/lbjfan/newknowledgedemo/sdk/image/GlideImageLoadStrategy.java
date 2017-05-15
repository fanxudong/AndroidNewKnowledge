package com.github.lbjfan.newknowledgedemo.sdk.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by Administrator on 2017/5/15.
 */

public class GlideImageLoadStrategy implements BaseImageLoadStrategy {

    @Override
    public void loadImage(String url, ImageView view) {
        Glide.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    @Override
    public void loadImage(String url, int placeHolder, ImageView view) {
        Glide.with(view.getContext())
                .load(url)
                .error(view.getContext().getDrawable(placeHolder))
                .placeholder(view.getContext().getDrawable(placeHolder))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    @Override
    public void loadCircleCornerImage(String url, ImageView view, int corner) {
        Glide.with(view.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(view.getContext(), 10))
                .into(view);
    }

    @Override
    public void loadCircleCornerImage(String url, int placeHolder, ImageView view, int corner) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(placeHolder)
                .error(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    @Override
    public void loadGifImage(String url, int placeHolder, ImageView view) {
        Glide.with(view.getContext())
                .load(url)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    @Override
    public void clearImageDiskCache(Context context) {

    }

    @Override
    public void clearImageMemoryCache(Context context) {

    }

    @Override
    public void trimMemory(Context context, int level) {

    }

    @Override
    public void getMemorySize(Context context) {

    }

    public class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int radius) {
            super(context);
            this.radius = radius;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return null;
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap toTransform) {
            if (pool == null) {
                return null;
            }
            Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0, 0, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}
