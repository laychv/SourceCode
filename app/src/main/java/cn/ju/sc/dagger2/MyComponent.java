package cn.ju.sc.dagger2;

import dagger.Component;

/**
 * 制定MyComponent -> Module
 */
@Component(modules = TextViewModule.class)
public interface MyComponent {
    void inject(Dagger2Activity activity);
}
