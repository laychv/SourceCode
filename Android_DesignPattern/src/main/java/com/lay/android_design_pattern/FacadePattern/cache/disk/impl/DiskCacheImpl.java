package com.lay.android_design_pattern.FacadePattern.cache.disk.impl;

import android.graphics.Bitmap;

import com.lay.android_design_pattern.FacadePattern.cache.disk.DiskCache;

public class DiskCacheImpl implements DiskCache {
    @Override
    public Bitmap findByDisk(String url) {
        System.out.println("根据URL,从磁盘中加载");
        return null;
    }
}
