package com.lay.android_design_pattern.FacadePattern.cache.memory;

import android.graphics.Bitmap;

public interface MemoryCache {
    // 从内存中找图片
    Bitmap findByMemory(String url);
}
