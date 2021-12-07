package com.lay.android_design_pattern;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;
import com.lay.android_design_pattern.EasyFactoryPattern.factory.PropertiesFactory;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EasyFactoryTest {

    @Test
    public void easy_factory_test() {

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // 根据配置文件
        Api api3 = PropertiesFactory.create(appContext);
        if (api3 != null) api3.create();
    }
}