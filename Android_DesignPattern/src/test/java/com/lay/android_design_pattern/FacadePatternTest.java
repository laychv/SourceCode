package com.lay.android_design_pattern;

import com.lay.android_design_pattern.FacadePattern.cache.Facade;
import com.lay.android_design_pattern.FacadePattern.cache.disk.impl.DiskCacheImpl;
import com.lay.android_design_pattern.FacadePattern.cache.memory.MemoryCache;
import com.lay.android_design_pattern.FacadePattern.cache.memory.impl.MemoryCacheImpl;
import com.lay.android_design_pattern.FacadePattern.cache.network.impl.NetworkCacheImpl;

import org.junit.Test;

/**
 * 外观模式
 * 隐藏子系统的复杂性,为子系统中的一组接口提供了一个统一的访问接口
 * 高内聚,低耦合
 */
public class FacadePatternTest {

    private final static String URL = "http://www.baidu.com";

    @Test
    public void facade_pattern_test() {

        // 常规写法
        MemoryCache memoryCache = new MemoryCacheImpl();
        memoryCache.findByMemory(URL);

        DiskCacheImpl diskCache = new DiskCacheImpl();
        diskCache.findByDisk(URL);

        NetworkCacheImpl networkCache = new NetworkCacheImpl();
        networkCache.loadImageByNetwork(URL);

        System.out.println("-------------------------");

        Facade facade = new Facade(URL);
        facade.loader();

    }
}
