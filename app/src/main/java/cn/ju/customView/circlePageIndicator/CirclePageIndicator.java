package cn.ju.customView.circlePageIndicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Objects;

/**
 * ViewPager圆形指示器
 */
public class CirclePageIndicator extends View {

    private int mDotRadius;
    private int mDotGap;
    private Paint mDotPaint;
    private int mNormalColor;
    private int mSelectedColor;
    private ViewPager mViewPager;
    private float mPositionOffset;
    private int mPosition;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 点的半径
        mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        // 点的间距
        mDotGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        // 画点
        mDotPaint = new Paint();
        mDotPaint.setAntiAlias(true);

        mNormalColor = Color.BLACK;
        mSelectedColor = Color.RED;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = Objects.requireNonNull(mViewPager.getAdapter()).getCount();
        // 宽 = 点的直径 * 个数 + 间隙 * (点的个数 -1)
        int width = 2 * mDotRadius * count + mDotGap * (count - 1);
        // 高 = 点的直径
        int height = 2 * mDotRadius;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 点与点之间圆心的距离
        int dotDistance = 2 * mDotRadius + mDotGap;
        // 遍历不动点
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            float cx = mDotRadius + i * dotDistance;// 点x坐标
            float cy = mDotRadius;// 点y坐标
            mDotPaint.setColor(mNormalColor);
            canvas.drawCircle(cx, cy, mDotRadius, mDotPaint);
        }
        //动点
        mDotPaint.setColor(mSelectedColor);
        float moveX = mDotRadius + dotDistance * mPositionOffset + mPosition * dotDistance;
        float moveY = mDotRadius;
        canvas.drawCircle(moveX, moveY, mDotRadius, mDotPaint);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(listener);
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mPosition = position;
            mPositionOffset = positionOffset;
            invalidate();
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
