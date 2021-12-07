package com.lay.android_design_pattern.FacadePattern.cache.disk;

import android.graphics.Bitmap;

public interface DiskCache {
    // 内存中没有找到,从磁盘中找
    Bitmap findByDisk(String url);
}
