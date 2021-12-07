package com.lay.android_ams;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * 注意:
 * 子类也要addView
 */
public class MyView extends FrameLayout {

    private Context context;
    private LinearLayout itemView;
    private TextView tv_common_view;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        itemView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.ocr_common_item2, null);
        tv_common_view = (TextView) itemView.findViewById(R.id.tv_common_view);
        LayoutParams layoutParams = new LayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutParams.height = 90;
        addView(itemView, layoutParams);
    }

    public void setText(String text) {
        tv_common_view.setText(text);
    }

}