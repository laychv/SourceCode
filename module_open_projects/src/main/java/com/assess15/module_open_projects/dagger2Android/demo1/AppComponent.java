package com.assess15.module_open_projects.dagger2Android.demo1;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, ActivityModule.class})
public interface AppComponent {
    void inject(DemoApplication instance);
}
