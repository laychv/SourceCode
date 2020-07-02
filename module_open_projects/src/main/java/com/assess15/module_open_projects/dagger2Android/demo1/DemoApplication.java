package com.assess15.module_open_projects.dagger2Android.demo1;

import com.assess15.module_base.BaseApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class DemoApplication extends BaseApplication implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> mDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return mDispatchingAndroidInjector;
    }
}
