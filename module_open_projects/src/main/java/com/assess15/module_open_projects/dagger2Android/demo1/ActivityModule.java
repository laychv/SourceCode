package com.assess15.module_open_projects.dagger2Android.demo1;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubComponent.class)
public abstract class ActivityModule {
    @Binds
    @IntoMap
    @ClassKey(DaggerAndroidActivity.class)
    abstract AndroidInjector.Factory<?>
    bindMainActivityInjectorFactory(MainActivitySubComponent.Factory builder);
}
