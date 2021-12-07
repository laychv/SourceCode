package com.lay.android_design_pattern;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.factory.ApiFactory;
import com.lay.android_design_pattern.EasyFactoryPattern.factory.ParamFactory;
import com.lay.android_design_pattern.EasyFactoryPattern.factory.PropertiesFactory;
import com.lay.android_design_pattern.EasyFactoryPattern.impl.ApiImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * 工厂模式
 * 创建对象, 不需要关系具体实现
 */
@RunWith(RobolectricTestRunner.class)
public class EasyFactoryTest {

    Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    @Config(manifest = Config.NONE)
    public void easy_factory_test() {

        // 常规编码
        ApiImpl api = new ApiImpl();
        api.create();

        // 简单工厂: 降低模块间耦合
        Api api1 = ApiFactory.createApi();
        api1.create();

        // 根据不同参数,使用不同实现
        Api api2 = ParamFactory.create(2);
        api2.create();

        // 根据配置文件
        Api api3 = PropertiesFactory.create(context);
        if (api3 != null) api3.create();
    }

}