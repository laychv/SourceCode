package cn.ju.cv.codeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

import cn.ju.sc.R;

/**
 * 验证码
 */
public class CodeView extends View {

    private int count;
    private int lineCount;
    private int fontSize;
    private int codeColor;

    private String code;//验证码
    private Random rnd;
    private Paint paint;
    private static final int DEFAULT_COUNT = 4;
    private static final int DEFAULT_LINE_COUNT = 50;
    private static final int DEFAULT_FONT_SIZE = 12;//sp
    private static final int DEFAULT_COLOR = Color.BLACK;
    private int width;
    private int height;
    private Rect rect;
    private Rect rect1;

    public CodeView(Context context) {
        this(context, null);
    }

    public CodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CodeView);
        count = typedArray.getInt(R.styleable.CodeView_count, DEFAULT_COUNT);
        lineCount = typedArray.getInt(R.styleable.CodeView_line_count, DEFAULT_LINE_COUNT);
        codeColor = typedArray.getInt(R.styleable.CodeView_code_color, DEFAULT_COLOR);
        fontSize = typedArray.getDimensionPixelSize(R.styleable.CodeView_font_size, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_FONT_SIZE, getResources().getDisplayMetrics()));
        typedArray.recycle();

        rnd = new Random();
        initPaint();
        code = getCode();
        rect = new Rect(0, 0, width, height);
        rect1 = new Rect(rect);
    }

    private void initPaint() {
        paint = new Paint();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(codeColor);
        paint.setTextSize(fontSize);
    }

    public String getCode() {
        //  转换前
//        String code = "";
//        for (int i = 0; i < count; i++) {
//            code += rnd.nextInt(10);
//        }
//        return code;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < count; i++) {
            code.append(rnd.nextInt(10));
        }
        return code.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect rect = getTextRect();
        int width = measureWith(widthMeasureSpec, rect);
        int height = measureHeight(heightMeasureSpec, rect);
        setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpec, Rect rect) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + rect.height() + getPaddingBottom();
        }
        return height;
    }

    private int measureWith(int widthMeasureSpec, Rect rect) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + rect.width() + getPaddingRight();
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        // 这里写法不规范，不要创建对象，因为onDraw会多次调用,影响性能
//        rect = new Rect(0, 0, width, height);
        //绘制外围矩形框
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
//        rect1 = new Rect(rect);
        rect1.inset(2, 2);
        canvas.drawRect(rect1, paint);
        paint.setStyle(Paint.Style.FILL);
        //绘制随机干扰线
        paint.setColor(Color.GRAY);
        for (int i = 0; i < lineCount; i++) {
            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            int x1 = rnd.nextInt(width);
            int y1 = rnd.nextInt(height);
            canvas.drawLine(x, y, x1, y1, paint);
        }
        paint.setColor(codeColor);
        //绘制文字
        Rect textRect = getTextRect();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int x = (width - textRect.width()) / 2;
        int y = (int) (height / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent);
        canvas.drawText(code, x, y, paint);
    }

    public Rect getTextRect() {
        Rect textRect = new Rect();
        paint.getTextBounds(code, 0, code.length(), textRect);
        return textRect;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        code = getCode();
        requestLayout();//重新调整布局大小
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
        invalidate();//重绘
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, fontSize,
                getResources().getDisplayMetrics());
        initPaint();
        requestLayout();//重新调整布局大小
    }

    public int getColor() {
        return codeColor;
    }

    public void setColor(int color) {
        codeColor = color;
        initPaint();
        invalidate();//重绘
    }

    /**
     * 刷新验证码
     */
    public void refresh() {
        code = getCode();
        invalidate();
    }

    public String theCode() {
        return code;
    }

}
