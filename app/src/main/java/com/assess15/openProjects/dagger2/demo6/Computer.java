package com.assess15.openProjects.dagger2.demo6;

import javax.inject.Inject;
import javax.inject.Provider;

public class Computer {
    @Inject
    Provider<NetWork> mNetWork; // 声明Provider<NetWork>对象

    public void init() {
        // 注入，会初始化mNetWork对象，但不会初始化NetWork对象
        DaggerComputerComponent.builder().build().inject(this);
        // 连续打印获取NetWork对象，每次都会创建新的NetWork对象
        System.out.println(mNetWork.get());
        System.out.println(mNetWork.get());
    }

}
