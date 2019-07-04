package com.assess15.customView.lineChart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.assess15.openProject.R;

public class LineChartView extends View {

    // 数据列表最大值
    private float mMax;
    private int[] mDataList;
    private String[] mHorizontalAxis;
    private List<Dot> mDots = new ArrayList<>();
    private int mStep;
    private Paint mAxisPaint;
    private Paint mDotPaint;
    private Paint mLinePaint;
    private Paint mGradientPaint;
    private int mGap;
    private int mClickRadius;
    private int mRadius;
    private Rect mTextRect;
    private Path mGradientPath;
    private Path mPath;
    private int mLineColor;
    private int mNormalDotColor;
    private int mSelectedDotColor;
    private float heightRatio;
    private int mSelectedDotIndex = -1;
    private static int[] DEFAULT_GRADIENT_COLORS = {Color.BLUE, Color.GREEN};

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineChartView);
        mLineColor = typedArray.getColor(R.styleable.LineChartView_line_color, Color.BLACK);
        mNormalDotColor = typedArray.getColor(R.styleable.LineChartView_dot_normal_color, Color.BLACK);
        mSelectedDotColor = typedArray.getColor(R.styleable.LineChartView_dot_selected_color, Color.RED);
        typedArray.recycle();

        initPaints();

        mPath = new Path();
        mGradientPath = new Path();

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        mClickRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        mTextRect = new Rect();
        mGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    }

    private void initPaints() {
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setTextSize(35f);
        mAxisPaint.setTextAlign(Paint.Align.CENTER);

        mDotPaint = new Paint();
        mDotPaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(3f);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(mLineColor);

        mGradientPaint = new Paint();
        mGradientPaint.setAntiAlias(true);
    }

    private class Dot {
        // 绘制点的坐标(x,y)
        int x;
        int y;
        //点的数据值
        int value;
        //计算点的原始数据值value对应的高度像素大小
        int transformedValue;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 清除点的集合
        mDots.clear();
        // 去除padding,计算绘制区的宽高
        int width = w - getPaddingLeft() - getPaddingRight();
        int height = h - getPaddingTop() - getPaddingBottom();
        // 根据点的个数平分宽度
        mStep = width / (mDataList.length - 1);
        //通过坐标文本画笔计算绘制x轴第一个坐标文本占据的矩形边界，这里主要获取其高度
        //为计算maxBarHeight提供数据,maxBarHeight为折线图最大高度
        mAxisPaint.getTextBounds(mHorizontalAxis[0], 0, mHorizontalAxis[0].length(), mTextRect);
        // 计算折线图高度的最大像素大小，mTextRect.height为底部x轴坐标文本的高度
        //mGpa为坐标文本域折线之间间隔大小的高度
        int maxBarHeight = height - mTextRect.height() - mGap;
        // 计算折线图最大高度与最大数据值的比值
        heightRatio = maxBarHeight / mMax;
        // 遍历所有点
        for (int i = 0; i < mDataList.length; i++) {
            Dot dot = new Dot();
            dot.value = mDataList[i];
            dot.transformedValue = (int) (dot.value * heightRatio);
            dot.x = mStep * i + getPaddingLeft();
            dot.y = getPaddingTop() + maxBarHeight - dot.transformedValue;
            // 当是第一个点时，将路径移动到该点
            if (i == 0) {
                mPath.moveTo(dot.x, dot.y);
                mGradientPath.moveTo(dot.x, dot.y);
            } else {
                // 路径连线到点
                mPath.lineTo(dot.x, dot.y);
                mGradientPath.lineTo(dot.x, dot.y);
            }

            //如果到了最后一个点
            if (i == mDataList.length - 1) {
                //将渐变路径连接到最后一个点在竖直方法的最低点
                int bottom = getPaddingTop() + maxBarHeight;
                mGradientPath.lineTo(dot.x, bottom);
                Dot firstDot = mDots.get(0);
                //连接到第一个点在竖直方法的最低点
                mGradientPath.lineTo(firstDot.x, bottom);
                //连接到第一个点，形成闭合区域
                mGradientPath.lineTo(firstDot.x, bottom);
            }

            mDots.add(dot);
        }

        Shader shader = new LinearGradient(0, 0, 0, getHeight(), DEFAULT_GRADIENT_COLORS, null, Shader.TileMode.CLAMP);
        mGradientPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制折线路径
        canvas.drawPath(mPath, mLinePaint);
        canvas.drawPath(mGradientPath, mGradientPaint);
        for (int i = 0; i < mDots.size(); i++) {
            // 绘制坐标文本
            String axis = mHorizontalAxis[i];
            int x = getPaddingLeft() + i * mStep;
            int y = getHeight() - getPaddingBottom();
            canvas.drawText(axis, x, y, mAxisPaint);
            Dot dot = mDots.get(i);
            // 如果是用户点击的位置
            if (i == mSelectedDotIndex) {
                // 设置点的画笔颜色为用户点击点的颜色
                mDotPaint.setColor(mSelectedDotColor);
                // 绘制数据文本
                canvas.drawText(String.valueOf(mDataList[i]), dot.x, dot.y - mRadius - mGap, mAxisPaint);
            } else {
                //设置其他点的颜色
                mDotPaint.setColor(mNormalDotColor);
            }
            // 绘制点
            canvas.drawCircle(dot.x, dot.y, mRadius, mDotPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mSelectedDotIndex = getClickDotIndex(event.getX(), event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mSelectedDotIndex = -1;
                invalidate();
                break;
        }
        return true;
    }

    private int getClickDotIndex(float x, float y) {
        int index = -1;
        for (int i = 0; i < mDots.size(); i++) {
            Dot dot = mDots.get(i);
            // 初始化接收点击事件的矩形区域
            int left = dot.x - mClickRadius;
            int top = dot.y - mClickRadius;
            int right = dot.x + mClickRadius;
            int bottom = dot.y + mClickRadius;
            // 判断点(x,y)是否在矩形区域内
            if (x > left && x < right && y > top && y < bottom) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void setDataList(int[] dataList, int max) {
        mDataList = dataList;
        mMax = max;
    }

    public void setHorizontalAxis(String[] horizontalAxis) {
        mHorizontalAxis = horizontalAxis;
    }

}
