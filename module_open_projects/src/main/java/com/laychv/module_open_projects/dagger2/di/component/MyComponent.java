package com.laychv.module_open_projects.dagger2.di.component;

import com.laychv.module_open_projects.dagger2.Dagger2Activity;
import com.laychv.module_open_projects.dagger2.di.module.TextViewModule;

import dagger.Component;

/**
 * 制定MyComponent -> Module
 */
@Component(modules = TextViewModule.class)
public interface MyComponent {
    void inject(Dagger2Activity activity);
}
