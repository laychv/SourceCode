package cn.ju.openProject.dagger2.di.component;

import cn.ju.openProject.dagger2.Dagger2Activity;
import cn.ju.openProject.dagger2.di.module.TextViewModule;
import dagger.Component;

/**
 * 制定MyComponent -> Module
 */
@Component(modules = TextViewModule.class)
public interface MyComponent {
    void inject(Dagger2Activity activity);
}
