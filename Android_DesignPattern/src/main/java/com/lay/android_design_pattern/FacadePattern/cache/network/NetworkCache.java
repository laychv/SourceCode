package com.lay.android_design_pattern.FacadePattern.cache.network;

import java.io.InputStream;

public interface NetworkCache {
    InputStream loadImageByNetwork(String url);
}
