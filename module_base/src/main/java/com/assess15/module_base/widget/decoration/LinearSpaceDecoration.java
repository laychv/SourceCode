package com.assess15.module_base.widget.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assess15.module_base.utils.ScreenUtilsKt;

/**
 * Linear Space
 */
public class LinearSpaceDecoration extends RecyclerView.ItemDecoration {

    private int bottom;
    private Context mContext;

    public LinearSpaceDecoration(Context context, int bottom) {
        mContext = context;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, ScreenUtilsKt.dip2px(mContext, bottom)
        );
    }
}
