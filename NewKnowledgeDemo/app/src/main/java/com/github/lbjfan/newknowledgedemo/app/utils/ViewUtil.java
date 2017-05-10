package com.github.lbjfan.newknowledgedemo.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by xe on 15-4-14.
 */
public final class ViewUtil {

    public static final String TAG = "ViewUtil";

    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(View parent, @IdRes int viewId) {
        return (T) parent.findViewById(viewId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(Activity activity, @IdRes int viewId) {
        return (T) activity.findViewById(viewId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(FragmentActivity activity, @IdRes int viewId) {
        return (T) activity.findViewById(viewId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(Fragment fragment, @IdRes int viewId) {
        return (T) fragment.getView().findViewById(viewId);
    }

    public static void bindClick1(View.OnClickListener inClickListener, Activity rootView, int... viewIds) {
        if (viewIds == null) {
            return;
        }
        for (int viewId : viewIds) {
            View view = rootView.findViewById(viewId);
            if (view == null) {
                continue;
            }
            view.setOnClickListener(inClickListener);
        }
    }

    public static void bindClick2(View.OnClickListener inClickListener, View rootView, int... viewIds) {
        if (viewIds == null) {
            return;
        }
        for (int viewId : viewIds) {
            View view = rootView.findViewById(viewId);
            if (view == null) {
                continue;
            }
            view.setOnClickListener(inClickListener);
        }
    }

    public static void bindClick3(View.OnClickListener inClickListener, View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setOnClickListener(inClickListener);
        }
    }

    public static void visible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void invisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public static void gone(View view) {
        view.setVisibility(View.GONE);
    }

    public static int measureTextWidth(TextView view, String text) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }

        TextPaint paint = view.getPaint();
        return (int) paint.measureText(text);
    }

    public static boolean eventInView(MotionEvent event, View view) {
        if (event == null || view == null) {
            return false;
        }

        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int width = view.getWidth();
        int height = view.getHeight();
        int left = location[0];
        int top = location[1];
        int right = left + width;
        int bottom = top + height;

        Rect rect = new Rect(left, top, right, bottom);
        boolean contains = rect.contains(eventX, eventY);
        return contains;
    }

    public static Point getViewCenter(View view) {
        if (view == null) {
            return new Point();
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0] + view.getWidth() / 2;
        int y = location[1] + view.getHeight() / 2;
        return new Point(x, y);
    }

    @SuppressLint("NewApi")
    public static void setAlpha(View view, float alpha) {
        if (Build.VERSION.SDK_INT < 11) {
            final AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
            animation.setDuration(0);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        } else {
            view.setAlpha(alpha);
        }
    }

    public static void removeFromParent(View view) {
        if (view == null) {
            return;
        }

        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    public static Bitmap capture(View view) {
        if (view == null) {
            return null;
        }
        boolean oldEnabled = view.isDrawingCacheEnabled();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (!oldEnabled) {
            view.setDrawingCacheEnabled(false);
        }
        return bitmap;
    }

    public static void setTextDp(TextView textView, float textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    public static void setTextSp(TextView textView, float textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }
}
