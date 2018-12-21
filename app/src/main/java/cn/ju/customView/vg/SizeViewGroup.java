package cn.ju.customView.vg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SizeViewGroup extends ViewGroup {

    private RectF rectF;
    private Paint paint;
    private Path path;

    public SizeViewGroup(Context context) {
        this(context, null);
    }

    public SizeViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SizeViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        TextView textView = new TextView(context);
        textView.setText("Android");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20f);
        textView.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(200, 200);
        this.addView(textView, layoutParams);
        this.setBackgroundColor(Color.alpha(255));

        rectF = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //设置子组件(此处为 TextView)的位置和大小
        View textView = this.getChildAt(0);
        textView.layout(50, 50, textView.getMeasuredWidth() + 50, textView.getMeasuredHeight() + 50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量所有子组件的大小
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量自身的大小,此处直接写死为 500 * 500
        this.setMeasuredDimension(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF.inset(2, 2);
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);

        path.addRoundRect(rectF, 20, 20, Path.Direction.CCW);

        canvas.drawPath(path, paint);
    }
}
