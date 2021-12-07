package com.lay.android_plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private static final String TAG = PluginManager.class.getSimpleName();

    private Context mContext;
    private static PluginManager pluginManager;

    public PluginManager(Context context) {
        this.mContext = context;
    }

    public static PluginManager getInstance(Context context) {
        if (pluginManager == null) {
            synchronized (PluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new PluginManager(context);
                }
            }
        }
        return null;
    }

    private Resources mResources;
    private DexClassLoader mDexClassLoader;

    /**
     * Activity dexclass
     * layout
     */
    public void loadPlugin() {
        try {
            /**
             * 加载DexClass
             */
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "host.apk");
            if (!file.exists()) {
                Log.d(TAG, "插件包, 不存在!!!");
                return;
            }
            String pluginPath = file.getAbsolutePath();
            // dexClassLoader需要一个缓存目录   /data/data/当前应用的包名/pDir
            File fDir = mContext.getDir("pDir", Context.MODE_PRIVATE);
            String oDir = fDir.getAbsolutePath();
            mDexClassLoader = new DexClassLoader(pluginPath, oDir, null, mDexClassLoader);

            /**
             * 加载layout
             */
            AssetManager assetManager = AssetManager.class.newInstance();
            // 我们要执行此方法，为了把插件包的路径 添加进去
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, pluginPath);
            Resources resources = mContext.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            Configuration config = resources.getConfiguration();
            // 特殊的 Resources，加载插件里面的资源的 Resources
            mResources = new Resources(assetManager, metrics, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClassLoader getClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }

}