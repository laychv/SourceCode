package com.lay.android_ams.core;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import static com.lay.android_ams.core.Flag.LAUNCH_ACTIVITY;

/**
 * 核心: 通过动态代理方式, 只需要注册一个Activity的xml, 其他Activity不用再次注册xml
 * <p>
 * 跳转到TargetActivity -> Binder应用进程到系统进程 -> AMS管理,调度等核心工作 -> Binder系统进程到应用进程 ->
 * ActivityThread加载目标视图 -> TargetActivity加载完成
 * <p>
 * 调用顺序不能颠倒
 */
public final class HookAMS {

    // 第一步: 欺AMS, 把没有注册的Activity替换成ProxyActivity(已经注册)
    public static void hookAMSService(final Context mContext) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {

        Object mIActivityManager = null;
        Object mIActivityManagerSingleton = null;

        if (SDKVersion.isSDK21to25()) {
            Class mActivityManagerClass = Class.forName("android.app.ActivityManagerNative");

            // 获取method
            Method getDefaultMethod = mActivityManagerClass.getDeclaredMethod("getDefault");
            getDefaultMethod.setAccessible(true);
            mIActivityManager = getDefaultMethod.invoke(null);

            // 获取field
            Field gDefaultField = mActivityManagerClass.getDeclaredField("gDefault");
            gDefaultField.setAccessible(true);
            mIActivityManagerSingleton = gDefaultField.get(null);
        } else if (SDKVersion.isSDK26to28()) {
            Class mActivityManagerClass = Class.forName("android.app.ActivityManager");
            mIActivityManager = mActivityManagerClass.getMethod("getService").invoke(null);// public static

            Field iActivityManagerSingletonField = mActivityManagerClass.getDeclaredField("IActivityManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            mIActivityManagerSingleton = iActivityManagerSingletonField.get(null);
        } else if (SDKVersion.isSDK29to30()) {
            // todo 适配 Android 29,30
        }

        Class mIActivityManagerClass = Class.forName("android.app.IActivityManager");
        final Object finalMIActivityManager = mIActivityManager;

        Object mIActivityManagerProxy = Proxy.newProxyInstance(mContext.getClassLoader(),
                new Class[]{mIActivityManagerClass}, new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("startActivity".equals(method.getName())) {
                            // 替换的开始, 把不能替换的TargetActivity替换为ProxyActivity
                            Intent proxyIntent = new Intent(mContext, ProxyActivity.class);
                            Intent target = (Intent) args[2];
                            proxyIntent.putExtra(Flag.TARGET_INTENT, target);
                            args[2] = proxyIntent;
                        }
                        return method.invoke(finalMIActivityManager, args);
                    }
                });

        if (mIActivityManagerSingleton == null || mIActivityManagerProxy == null) {
            throw new IllegalArgumentException("Hook源码失败!!!!!");
        }

        Class<?> mSingletonClass = Class.forName("android.util.Singleton");
        Field mInstanceField = mSingletonClass.getDeclaredField("mInstance");
        mInstanceField.setAccessible(true);
        // 默认使用系统IActivityManager ---> Binder Client ---> Binder Server AMS, 报错
        // 此番操作优先使用自己写的动态代理的Activity,替换系统的
        mInstanceField.set(mIActivityManagerSingleton, mIActivityManagerProxy);
    }

    // 第二步: 把ProxyActivity替换为目标Activity
    public static void replaceActivityThread(final Context mContext) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        if (SDKVersion.isSDK21to25()) {
            // 拿到核心类 ActivityThread
            Class mActivityThreadClass = Class.forName("android.app.ActivityThread");
            // 拿到真实字段sCurrentActivityThread
            Field sCurrentActivityThread = mActivityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            // 拿到ActivityThread
            Object mActivityThread = sCurrentActivityThread.get(null);// static get null
            // 拿到mH即: ActivityThread内部类Handler
            Field mHField = mActivityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            // 无需通过反射再次获取Handler
            Handler mH = (Handler) mHField.get(mActivityThread);
            // 拿到Handler内部的Callback
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            // 执行自己的Handler Callback
            mCallbackField.set(mH, new HandlerCallback21to25());
        } else if (SDKVersion.isSDK26to28()) {
            Class<?> mActivityThreadClass = Class.forName("android.app.ActivityThread");
            try {
                // 获取public方法,尽量避免使用private变量,因为可能会修改,导致找不到
                Method sCurrentActivityThreadMethod = mActivityThreadClass.getMethod("currentActivityThread");
                Object sCurrentActivityThread = sCurrentActivityThreadMethod.invoke(null);
                // 获取到mH
                Field mHField = mActivityThreadClass.getDeclaredField("mH");
                mHField.setAccessible(true);
                Object mH = mHField.get(sCurrentActivityThread);
                Field mCallbackField = Handler.class.getDeclaredField("mCallback");
                mCallbackField.setAccessible(true);
                mCallbackField.set(mH, new HandlerCallback26to28());
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (SDKVersion.isSDK29to30()) {
            // todo 适配 Android 29,30
        }
    }

    //     public void dispatchMessage(@NonNull Message msg) {
    //        if (msg.callback != null) {
    //            handleCallback(msg);
    //        } else {
    //            if (mCallback != null) {
    //                if (mCallback.handleMessage(msg)) {
    //                    return;
    //                }
    //            }
    //            handleMessage(msg);
    //        }
    //    }
    // 这里是我们需要做的地方
    //  if (mCallback != null) {
    //     if (mCallback.handleMessage(msg)) {
    //         return;
    //     }
    //  }

    // 第三步: 核心: 替换系统Handler Callback, 加载自己的Callback逻辑
    // android [21-25]
    public static final class HandlerCallback21to25 implements Handler.Callback {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (LAUNCH_ACTIVITY == msg.what) {
                // 记录跳转所有Activity
                final Object mActivityClientRecord = msg.obj;
                try {
                    // 获取ActivityThread的内部类ActivityClientRecord中的Intent
                    Field intentField = mActivityClientRecord.getClass().getDeclaredField("intent");
                    intentField.setAccessible(true);
                    // 获取对象ActivityClientRecord, 拿到Intent
                    Intent proxyIntent = (Intent) intentField.get(mActivityClientRecord);
                    if (proxyIntent != null) {
                        // 获取AMS中携带过来的目标Activity
                        Intent targetIntent = proxyIntent.getParcelableExtra(Flag.TARGET_INTENT);
                        // 还原目标Activity
                        if (targetIntent != null) {
                            intentField.set(mActivityClientRecord, targetIntent);
                        }
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    //android [26-28]
    public static final class HandlerCallback26to28 implements Handler.Callback {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == Flag.EXECUTE_TRANSACTION) {
                // final ClientTransaction transaction = (ClientTransaction) msg.obj;
                Object mTransaction = msg.obj;
                try {
                    Class<?> mClientTransactionClass = Class.forName("android.app.servertransaction.ClientTransaction");
                    Field mActivityCallbacksField = mClientTransactionClass.getDeclaredField("mActivityCallbacks");
                    mActivityCallbacksField.setAccessible(true);
                    List mActivityCallbacks = (List) mActivityCallbacksField.get(mTransaction);
                    if (mActivityCallbacks.size() == 0) {
                        return false;
                    }
                    Object mLaunchActivityItem = mActivityCallbacks.get(0);
                    Class<?> mLaunchActivityItemClass = Class.forName("android.app.servertransaction.LaunchActivityItem");
                    if (!mLaunchActivityItemClass.isInstance(mLaunchActivityItem)) {
                        return false;
                    }
                    // 获取Intent
                    Field mIntentField = mClientTransactionClass.getDeclaredField("mIntent");
                    mIntentField.setAccessible(true);

                    Intent proxyIntent = (Intent) mIntentField.get(mLaunchActivityItem);
                    Intent targetIntent = proxyIntent.getParcelableExtra(Flag.TARGET_INTENT);
                    if (targetIntent != null) {
                        mIntentField.set(mLaunchActivityItem, targetIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    // android [29-30]
    public static final class HandlerCallback29to30 implements Handler.Callback {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    }
}
