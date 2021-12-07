package com.lay.android_design_pattern.FacadePattern.cache.memory.impl;

import android.graphics.Bitmap;

import com.lay.android_design_pattern.FacadePattern.cache.memory.MemoryCache;

public class MemoryCacheImpl implements MemoryCache {
    @Override
    public Bitmap findByMemory(String url) {
        System.out.println("根据URL,从内存加载");
        return null;
    }
}
