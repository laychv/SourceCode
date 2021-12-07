package com.lay.android_design_pattern.FacadePattern.cache.network.impl;

import com.lay.android_design_pattern.FacadePattern.cache.network.NetworkCache;

import java.io.InputStream;

public class NetworkCacheImpl implements NetworkCache {

    @Override
    public InputStream loadImageByNetwork(String url) {
        System.out.println("根据URL,从网络加载");
        return null;
    }
}
