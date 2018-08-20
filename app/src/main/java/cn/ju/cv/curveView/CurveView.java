package cn.ju.cv.curveView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CurveView extends View {

    private Paint mPaint;
    private Paint mAxisPaint;
    private Paint mDotPaint;
    private int mGap;
    private int mRadius;
    private Rect mTextRect;
    private Path mCurvePath;
    private List<Dot> mDots = new ArrayList<>();
    private int[] mDataList;
    private int mMax;
    private String[] mHorizontalAxis;
    private int mStep;
    private float[][] mControlDots = new float[2][2];
    private int mNormalDotColor = Color.BLACK;
    private static final float SMOOTHNESS_RATIO = 0.16f;

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 线 - 画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        // 文字 - 画笔
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setTextSize(35f);
        mAxisPaint.setTextAlign(Paint.Align.CENTER);
        // 点 - 画笔
        mDotPaint = new Paint();
        mDotPaint.setAntiAlias(true);
        //
        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        // 间隙
        mGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        mTextRect = new Rect();
        mCurvePath = new Path();
    }

    private class Dot {
        int x;
        int y;
        int value;
        int transformedValue;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mDots.clear();
        int with = w - getPaddingLeft() - getPaddingRight();
        int height = h - getPaddingTop() - getPaddingBottom();
        mStep = with / (mDataList.length - 1);
        mAxisPaint.getTextBounds(mHorizontalAxis[0], 0, mHorizontalAxis[0].length(), mTextRect);
        int maxBarHeight = height - mTextRect.height() - mGap;
        int heightRatio = maxBarHeight / mMax;
        for (int i = 0; i < mDataList.length; i++) {
            Dot dot = new Dot();
            dot.value = mDataList[i];
            dot.transformedValue = dot.value * heightRatio;
            dot.x = mStep * i + getPaddingLeft();
            dot.y = getPaddingTop() + maxBarHeight - dot.transformedValue;
            mDots.add(dot);
        }
        //规划曲线路径
        for (int i = 0; i < mDataList.length - 1; i++) {
            if (i == 0) {
                mCurvePath.moveTo(mDots.get(0).x, mDots.get(0).y);
            }
            //计算三阶贝塞尔曲线的控制点
            calculateControlPoints(i);
            //使用三阶贝塞尔曲线连接下一个点
            mCurvePath.cubicTo(mControlDots[0][0], mControlDots[0][1], mControlDots[1][0], mControlDots[1][1], mDots.get(i + 1).x, mDots.get(i + 1).y);
        }
    }

    /**
     * 计算三阶贝塞尔曲线的控制点
     */
    private void calculateControlPoints(int i) {
        float x1, y1; //第1个控制点
        float x2, y2; //第2个控制点
        Dot currentDot = mDots.get(i); // 当前点
        Dot nextDot;//下一个点
        Dot previousDot;// 上一个点
        Dot nextNextDot;//下下一个点

        if (i > 0) {
            //当i>0，即不是第一个点时，获取上一个点
            previousDot = mDots.get(i - 1);
        } else {
            //当i=0，即为第一个点时，没有上一个点，就用第一个点代替
            previousDot = currentDot;
        }

        if (i < mDots.size() - 1) {
            //当i没有遍历到最后一个点时，获取下一个点
            nextDot = mDots.get(i + 1);
        } else {
            //当i=mDots.size()-1，即为最后一个点时，没有下一个点，就用最后一个点代替
            nextDot = currentDot;
        }

        if (i < mDots.size() - 2) {
            //当i没有遍历到倒数第二个点时，获取下下个点
            nextNextDot = mDots.get(i + 2);
        } else {
            //当i遍历到倒数第二个点，下下个点不存在，就用下个点代替
            nextNextDot = nextDot;
        }

        //利用公式计算两个控制点，参数取a=b=SMOOTHNESS_RATIO=0.16
        x1 = currentDot.x + SMOOTHNESS_RATIO * (nextDot.x - previousDot.x);
        y1 = currentDot.y + SMOOTHNESS_RATIO * (nextDot.y - previousDot.y);
        x2 = nextDot.x - SMOOTHNESS_RATIO * (nextNextDot.x - currentDot.x);
        y2 = nextDot.y - SMOOTHNESS_RATIO * (nextNextDot.y - currentDot.y);

        //保存计算结果到数组中，规划mCurvePath中使用
        mControlDots[0][0] = x1;
        mControlDots[0][1] = y1;
        mControlDots[1][0] = x2;
        mControlDots[1][1] = y2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mCurvePath, mPaint);
        //绘制点和底部坐标文本
        for (int i = 0; i < mDots.size(); i++) {
            String axi = mHorizontalAxis[i];
            int x = getPaddingLeft() + i * mStep;
            int y = getHeight() - getPaddingBottom();
            canvas.drawText(axi, x, y, mAxisPaint);
            Dot dot = mDots.get(i);
            mDotPaint.setColor(mNormalDotColor);
            canvas.drawCircle(dot.x, dot.y, mRadius, mDotPaint);
        }
    }

    public void setDataList(int[] dataList, int max) {
        mDataList = dataList;
        mMax = max;
    }

    public void setHorizontalAxis(String[] horizontalAxis) {
        mHorizontalAxis = horizontalAxis;
    }

}
