package com.assess15.customView.focusView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.assess15.utils.ScreenUtilsKt.getScreenWidth;

/**
 * =====================================
 * 作    者: lpc
 * 版    本：
 * 创建日期： 18-12-18 上午10:24
 * 描    述：矩形对焦框
 * 更    新：
 * =====================================
 */
public class FocusView extends View {

    private int mSize;
    private int centerX;
    private int centerY;
    private int length;
    private Paint mPaint;

    public FocusView(Context context) {
        this(context, null);
    }

    public FocusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FocusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mSize = getScreenWidth(getContext()) / 3;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xEE16AE16);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    // 先走测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = (int) (mSize / 2.0);
        centerY = (int) (mSize / 2.0);
        length = (int) ((mSize / 2.0) - 2);
        /**
         * This method must be called by {@link #onMeasure(int, int)} to store the measured width and measured height.
         */
        setMeasuredDimension(mSize, mSize);
    }

    //再走绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(centerX - length, centerY - length, centerX + length, centerY + length, mPaint);
        canvas.drawLine(0, getHeight() / 2, mSize / 10, getHeight() / 2, mPaint);// 左边
        canvas.drawLine(getWidth(), getHeight() / 2, getWidth() - mSize / 10, getHeight() / 2, mPaint);// 右边
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, mSize / 10, mPaint);// 上边
        canvas.drawLine(getWidth() / 2, getHeight(), getWidth() / 2, getHeight() - mSize / 10, mPaint);// 下边
    }
}
