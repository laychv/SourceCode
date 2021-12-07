package com.lay.android_design_pattern.FacadePattern.cache;

import com.lay.android_design_pattern.FacadePattern.cache.disk.DiskCache;
import com.lay.android_design_pattern.FacadePattern.cache.disk.impl.DiskCacheImpl;
import com.lay.android_design_pattern.FacadePattern.cache.memory.MemoryCache;
import com.lay.android_design_pattern.FacadePattern.cache.memory.impl.MemoryCacheImpl;
import com.lay.android_design_pattern.FacadePattern.cache.network.NetworkCache;
import com.lay.android_design_pattern.FacadePattern.cache.network.impl.NetworkCacheImpl;

public class Facade {

    private final String url;
    private final MemoryCache mMemoryCache;
    private final DiskCache mDiskCache;
    private final NetworkCache mNetworkCache;

    public Facade(String url) {
        this.url = url;
        mMemoryCache = new MemoryCacheImpl();
        mDiskCache = new DiskCacheImpl();
        mNetworkCache = new NetworkCacheImpl();
    }

    public void loader() {
        mMemoryCache.findByMemory(url);
        mDiskCache.findByDisk(url);
        mNetworkCache.loadImageByNetwork(url);
    }

}
