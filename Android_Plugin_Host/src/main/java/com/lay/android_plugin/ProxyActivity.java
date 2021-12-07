package com.lay.android_plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lay.android_plugin_standard.ActivityInterface;

import java.lang.reflect.Constructor;

/**
 * 代理Activity
 */
public class ProxyActivity extends Activity {

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getClassLoader();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getStringExtra("className");
        try {
            Class<?> pluginActivityClass = getClassLoader().loadClass(className);
            Constructor<?> constructor = pluginActivityClass.getConstructor(new Class[]{});
            Object pluginActivity = constructor.newInstance(new Object[]{});
            ActivityInterface activityInterface = (ActivityInterface) pluginActivity;
            activityInterface.insertContext(this);
            Bundle bundle = new Bundle();
            bundle.putString("appName","这是宿主传递的信息");
            activityInterface.onHostCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra("className", className);
        super.startActivity(proxyIntent);
    }
}
