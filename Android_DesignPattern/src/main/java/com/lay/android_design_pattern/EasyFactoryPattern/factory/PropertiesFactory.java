package com.lay.android_design_pattern.EasyFactoryPattern.factory;

import android.content.Context;

import com.lay.android_design_pattern.EasyFactoryPattern.Api;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFactory {

    public static Api create(Context context) {
        try {
            Properties properties = new Properties();
            // app/src/main/assets
            InputStream inputStream = context.getAssets().open("config.properties");
            // app/src/main/res/raw
//            InputStream inputStream1 = context.getResources().openRawResource("config.properties");
            //Java写法
            InputStream inputStream2 = PropertiesFactory.class.getResourceAsStream("config.properties");

            properties.load(inputStream);

            Class clazz = Class.forName(properties.getProperty("create_a"));
            return (Api) clazz.newInstance();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}