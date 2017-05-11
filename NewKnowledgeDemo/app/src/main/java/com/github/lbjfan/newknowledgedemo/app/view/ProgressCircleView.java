package com.github.lbjfan.newknowledgedemo.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.github.lbjfan.newknowledgedemo.R;

/**
 * Created by ${lbjfan} on 16-11-29.
 */
public class ProgressCircleView extends View {

    private Paint paint;
    //圆的半径
    private int mRadius;
    //色带的宽度
    private int mStripeWidth;
    //总体大小
    private int mWidth;
    private int mHeight;

    //动画位置的百分比进度
    private int mCurrentPercent;
    //当期位置的百分比进度
    private int mPercent;
    //圆心坐标
    private int circleX;
    private int circleY;

    //要画的弧度
    private int mEndAngle;
    //小圆的颜色
    private int mSmallCirclebg;
    //大圆的颜色
    private int mLargeCircleBg;
    //中心百分比文字的大小
    private int mTextSize;
    //中心百分比文字的颜色
    private int mTextColor;
    private static final int ANIM_TIME = 100;
    private Paint mTextPaint;


    public ProgressCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressCircleView, defStyleAttr, 0);
        mTextColor = typedArray.getColor(R.styleable.ProgressCircleView_mTextColor, Color.RED);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.ProgressCircleView_mTextSize, 12);
        mSmallCirclebg = typedArray.getColor(R.styleable.ProgressCircleView_mSmallBgColor, Color.GREEN);
        mLargeCircleBg = typedArray.getColor(R.styleable.ProgressCircleView_mLargeBgColor, Color.RED);
        mStripeWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressCircleView_mStripeWidth, 10);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressCircleView_mRadius, 100);
        typedArray.recycle();
        init();
    }

    public ProgressCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressCircleView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getViewSize(widthMeasureSpec, 0);
        mHeight = getViewSize(heightMeasureSpec, 1);
        mRadius = Math.min(mWidth, mHeight) / 2;
        setMeasuredDimension(mWidth, mHeight);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(mStripeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mEndAngle = (int) (3.6 * mCurrentPercent);
        paint.setColor(mSmallCirclebg);
        canvas.drawCircle(circleX, circleY, mRadius - mStripeWidth, paint);
        canvas.save();
        canvas.rotate(-90, circleX, circleY);
        if (mEndAngle != 0) {
            paint.setColor(mLargeCircleBg);
            RectF rectF = new RectF(circleX - mRadius + mStripeWidth, circleY - mRadius + mStripeWidth, circleX + mRadius - mStripeWidth, circleY + mRadius - mStripeWidth);
            canvas.drawArc(rectF, 0, mEndAngle, false, paint);
        }
        canvas.restore();
        String centerContent = mCurrentPercent + "%";
        Rect textRect = getTextRect(centerContent);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        int baseLine = (int) (mHeight / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
        int x = mWidth / 2 - textRect.width() / 2;
        canvas.drawText(centerContent, x, baseLine, mTextPaint);
    }

    private Rect getTextRect(String centerContent) {
        Rect rect = new Rect();
        mTextPaint.getTextBounds(centerContent, 0, centerContent.length(), rect);
        return rect;
    }

    public void setCurrentPercent(final int percent) {
        if (percent > 100 || percent < 0) {
            return;
        }
        this.mPercent = percent;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= mPercent; i++) {
                    try {
                        Thread.sleep(ANIM_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurrentPercent = i;
                    ProgressCircleView.this.postInvalidate();
                }
            }
        }).start();
    }

    private int getViewSize(int viewMeasureSpec, int type) {
        int viewSize = 0;
        int viewMode = MeasureSpec.getMode(viewMeasureSpec);
        int viewValue = MeasureSpec.getSize(viewMeasureSpec);
        if (viewMode == MeasureSpec.EXACTLY) {
            viewSize = viewValue;
            if (type == 0) {
                circleX = viewValue / 2;
            } else {
                circleY = viewValue / 2;
            }
        } else {
            //当用户设置的宽高值无法计算得到View的宽高时，使用用户自定义的圆半径大小重新设置View宽高
            viewSize = mRadius * 2;
            if (type == 0) {
                circleX = mRadius;
            } else {
                circleY = mRadius;
            }
        }
        return viewSize;
    }
}
