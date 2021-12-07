package com.lay.android_ams;

import android.app.Application;

import com.lay.android_ams.core.HookAMS;

import java.lang.reflect.InvocationTargetException;

public class AMSApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            HookAMS.hookAMSService(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            HookAMS.replaceActivityThread(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
