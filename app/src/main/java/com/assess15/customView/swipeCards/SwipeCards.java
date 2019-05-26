package com.assess15.customView.swipeCards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 卡片滑动切换
 */
public class SwipeCards extends ViewGroup {

    private int centerX;
    private int centerY;
    private int mCardGap;
    private ViewDragHelper mViewDragHelper;
    private static final int MAX_DEGREE = 60;
    private static final float MAX_ALPHA_RANGE = 0.5f;

    public SwipeCards(Context context) {
        this(context, null);
    }

    public SwipeCards(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeCards(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCardGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            // 允许拖动所有孩子控件
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            int diffX = left + changedView.getWidth() / 2 - centerX;
            float ratio = diffX * 1.0f / getWidth();
            float degree = MAX_DEGREE * ratio;
            changedView.setRotation(degree);
            float alpha = 1 - Math.abs(ratio) * MAX_ALPHA_RANGE;
            changedView.setAlpha(alpha);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            int left = releasedChild.getLeft();
            int right = releasedChild.getRight();

            if (left > centerX) {
                animateToRight(releasedChild);
            } else if (right < centerX) {
                animateToLeft(releasedChild);
            } else {
                animateToCenter(releasedChild);
            }
        }
    };

    private void animateToCenter(View releasedChild) {
        int finalLeft = centerX - releasedChild.getWidth() / 2;
        int indexOfChild = indexOfChild(releasedChild);
        int finalTop = centerY - releasedChild.getHeight() / 2 + mCardGap * (getChildCount() - indexOfChild);
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }

    private void animateToLeft(View releasedChild) {
        int finalLeft = -getWidth();
        int finalTop = 0;
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }

    private void animateToRight(View releasedChild) {
        int finalLeft = getWidth() + releasedChild.getHeight();
        int finalTop = releasedChild.getTop();
        mViewDragHelper.smoothSlideViewTo(releasedChild, finalLeft, finalTop);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(false)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // ViewDragHelper 对象处理触摸事件
        mViewDragHelper.processTouchEvent(event);

        performClick();
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /**
         *  获取控件中心的位置
         */
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 测量所有的CardView
         */
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**
         * 布局所有的CardView
         */
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int left = centerX - child.getMeasuredWidth() / 2;
            int top = centerY - child.getMeasuredHeight() / 2 + mCardGap * (getChildCount() - i);
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
        }
    }

}
