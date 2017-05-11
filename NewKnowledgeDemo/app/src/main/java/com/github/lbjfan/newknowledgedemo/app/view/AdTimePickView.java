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
 * Created by ${lbjfan} on 16-12-1.
 */
public class AdTimePickView extends View {

    private Paint paint;
    private Paint mTextPaint;
    //大圆半径
    private int mRadius = 200;

    //内层小圆背景
    private int mSmallCircleBg = Color.parseColor("#66f1679b");

    //小圆外层线条宽度
    private int mProgressViewWidth = 10;

    private float currentAngle;

    private static final int ANIM_TIME = 10000;

    //圆心坐标
    private int cirCleX;
    private int circleY;

    //测量之后View的宽高,绘制中心文字时需要用到
    private int mWidth;
    private int mHeight;

    //中心文字的大小与样式
    private int mTextSize;
    private int mTextColor;
    private String mTextContent = 100 + "%";


    public AdTimePickView(Context context) {
        this(context, null);
    }

    public AdTimePickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdTimePickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdTimePickView, defStyleAttr, 0);
        mProgressViewWidth = typedArray.getDimensionPixelSize(R.styleable.AdTimePickView_mProgressWidth, 10);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.AdTimePickView_mRadius1, 100);
        mSmallCircleBg = typedArray.getColor(R.styleable.AdTimePickView_mSmallCircleBg, Color.parseColor("#66f1679b"));
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.AdTimePickView_mTextSize1, 20);
        mTextColor = typedArray.getColor(R.styleable.AdTimePickView_mTextColor1, Color.parseColor("#333333"));
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getViewSize(widthMeasureSpec, 0);
        mHeight = getViewSize(heightMeasureSpec, 1);
        //大半径
        mRadius = Math.min(mWidth, mHeight) / 2;
        setMeasuredDimension(mWidth, mHeight);
    }

    private int getViewSize(int viewMeasureSpec, int type) {
        int viewValue = 0;
        int viewSize = MeasureSpec.getSize(viewMeasureSpec);
        int viewMode = MeasureSpec.getMode(viewMeasureSpec);
        if (MeasureSpec.EXACTLY == viewMode) {
            viewValue = viewSize;
            if (type == 0) {
                cirCleX = viewSize / 2;
            } else {
                circleY = viewSize / 2;
            }
        } else {
            if (type == 0) {
                cirCleX = mRadius;
            } else {
                circleY = mRadius;
            }
            viewValue = 2 * (mRadius + mProgressViewWidth);
        }
        return viewValue;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(mSmallCircleBg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cirCleX, circleY, (float) (mRadius - 1.5 * mProgressViewWidth), paint);

        //设置画笔状态
        paint.setColor(mTextColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mProgressViewWidth);
        //保存Canvans的状态
        canvas.save();
        //将坐标系围绕View的中心逆时针旋转90度数
        canvas.rotate(-90, cirCleX, circleY);
        RectF rectF = new RectF(cirCleX - mRadius + mProgressViewWidth, cirCleX - mRadius + mProgressViewWidth, cirCleX + mRadius - mProgressViewWidth, cirCleX + mRadius - mProgressViewWidth);
        //第四个参数表示逆时针还是顺时针绘制
        canvas.drawArc(rectF, 0, -currentAngle, false, paint);
        canvas.restore();

        Rect textRect = getTextRect(mTextContent);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        int baseLine = (int) (mHeight / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
        int x = mWidth / 2 - textRect.width() / 2;
        canvas.drawText(mTextContent, x, baseLine, mTextPaint);
    }

    private Rect getTextRect(String centerContent) {
        Rect rect = new Rect();
        mTextPaint.getTextBounds(centerContent, 0, centerContent.length(), rect);
        return rect;
    }

    public void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 360; i >= 0; i--) {
                    try {
                        Thread.sleep(ANIM_TIME / 360);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if ((int) (i % 3.6) == 0) {
                        mTextContent = (int) (i / 3.6) + "%";
                    }
                    currentAngle = i;
                    AdTimePickView.this.postInvalidate();
                }
            }
        }).start();
    }
}
