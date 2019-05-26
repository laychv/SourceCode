package com.assess15.customView.gradientProgressView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class GradientProgressView extends View {

    private int mCx;
    private int mCy;
    private int mProgress = 0;


    private static final int DEFAULT_ANIMATION_DURATION = 3000;
    private int mDuration = DEFAULT_ANIMATION_DURATION;

    private Paint mTextPaint = new Paint();
    private Rect mTextBound = new Rect();
    private RectF mRectF = new RectF();
    private Paint mGradientCirclePaint = new Paint();
    private Paint mBackgroundCirclePaint = new Paint();

    private static final int[] DEFAULT_COLOR = {Color.BLUE, Color.GREEN};
    private static int[] mGradientColors = DEFAULT_COLOR;

    public GradientProgressView(Context context) {
        this(context, null);
    }

    public GradientProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTextPaint();
    }

    /**
     * 初始化文字画笔
     */
    private void initTextPaint() {
        mTextPaint.setAntiAlias(true);//抗锯齿
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 22, getResources().getDisplayMetrics());
        mTextPaint.setTextSize(textSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        initRect(w, h);
        mCx = w / 2;
        mCy = h / 2;
        initCirclePaint();
    }

    /**
     * 初始化圆弧边界
     *
     * @param w
     * @param h
     */
    private void initRect(int w, int h) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = w - getPaddingRight();
        int bottom = h - getPaddingTop();
        mRectF.set(left, top, right, bottom);
    }

    /**
     * 初始化圆弧背景环画笔,渐变色画笔
     */
    private void initCirclePaint() {
        Shader shader = new SweepGradient(mCx, mCy, mGradientColors, null);// 渐变着色器

        mGradientCirclePaint.setShader(shader);
        mGradientCirclePaint.setAntiAlias(true);
        mGradientCirclePaint.setStrokeWidth(30);
        mGradientCirclePaint.setStyle(Paint.Style.STROKE);
        mGradientCirclePaint.setStrokeCap(Paint.Cap.ROUND);// 设置圆弧两端为圆角

        mBackgroundCirclePaint.setStyle(Paint.Style.STROKE);
        mBackgroundCirclePaint.setAntiAlias(true);
        mBackgroundCirclePaint.setStrokeWidth(30);
        mBackgroundCirclePaint.setColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mRectF, 0, 360, false, mBackgroundCirclePaint);// 绘制圆环背景
        float startAngel = -90;// 起始角度
        //ValueAnimator实现
//        float sweepAngle = mDrawProgress * 1.0f / 100 * 360;
//        String progressString = String.valueOf(mDrawProgress);
        float sweepAngel = mProgress * 1.0f / 100 * 360;// 计算扫过的角度
        canvas.drawArc(mRectF, startAngel, sweepAngel, false, mGradientCirclePaint);//绘制圆环进度
        String progressString = String.valueOf(mProgress);
        mTextPaint.getTextBounds(progressString, 0, progressString.length(), mTextBound);// 绘制中心的进度文本
        float x = mCx - mTextBound.width() / 2;
        float y = mCy + mTextBound.height() / 2;
        canvas.drawText(progressString +"%", x, y, mTextPaint);
    }

    /*    public void setProgress(int progress) {
        mProgress = progress;
    }
    public void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mProgress);
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDrawProgress = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }*/

    /**
     * 供属性动画使用
     */
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public void startAnimation(int degree) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "progress", 0, degree);
        objectAnimator.setDuration(mDuration);
        objectAnimator.start();
    }

    public static int[] getGradientColors() {
        return mGradientColors;
    }

    public static void setGradientColors(int[] mGradientColors) {
        GradientProgressView.mGradientColors = mGradientColors;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

}
