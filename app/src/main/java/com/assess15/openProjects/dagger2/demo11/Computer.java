package com.assess15.openProjects.dagger2.demo11;

import javax.inject.Inject;

public class Computer {

    @Inject
    MainBoard mMainBoard1; // 声明主板1

    @Inject
    MainBoard mMainBoard2; // 声明主板2

    public void init() {
        // 注入，主板1/主板2都会初始化
        DaggerComputerComponent.builder().build().inject(this);
        // 打印2主板，发现都是同一个对象，实现了主板的单例
        System.out.println(mMainBoard1);
        System.out.println(mMainBoard2);
    }

}
