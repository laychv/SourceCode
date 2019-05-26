package com.assess15.viewConflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static com.assess15.utils.ScreenUtilsKt.getScreenHeight;
import static com.assess15.utils.ScreenUtilsKt.getScreenWidth;

/**
 * =====================================
 * 作    者: ju
 * 版    本：
 * 创建日期： 18-12-25 下午3:04
 * 描    述： 可用拖动的ImageView
 * 更    新：
 * 注： 滑动冲突，由父控件决定是否拦截
 * =====================================
 */
public class MoveImage extends android.support.v7.widget.AppCompatImageView {
    private int lastX = 0;
    private int lastY = 0;

    private int dx;
    private int dy;
    private float movex = 0;
    private float movey = 0;

    private int screenWidth;
    private int screenHeight;

    public MoveImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenWidth = getScreenWidth(context);
        screenHeight = getScreenHeight(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                movex = lastX;
                movey = lastY;
                break;
            case MotionEvent.ACTION_MOVE:
                dx = (int) event.getRawX() - lastX;
                dy = (int) event.getRawY() - lastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                if (left < 0) {
                    left = 0;
                    right = left + getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + getHeight();
                }
                if (bottom > screenHeight) {
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                //避免滑出触发点击事件
                if ((int) (event.getRawX() - movex) != 0 || (int) (event.getRawY() - movey) != 0) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}