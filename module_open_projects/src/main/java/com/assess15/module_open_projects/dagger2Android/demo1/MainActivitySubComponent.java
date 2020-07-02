package com.assess15.module_open_projects.dagger2Android.demo1;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface MainActivitySubComponent extends AndroidInjector<DaggerAndroidActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DaggerAndroidActivity> {
    }
}