package com.assess15.openProjects.dagger2.demo5;

import javax.inject.Inject;

import dagger.Lazy;

public class Computer {
    @Inject
    CPU cpu;// 声明CPU对象，在注入时初始化

    @Inject
    Lazy<USB> usb;// 声明Lazy<USB>对象，在注入时不会初始化USB对象

    public void init() {
        // 注入，这时USB对象并没有初始化，只初始化Lazy<USB>对象 usb
        DaggerComputerComponent.builder().build().inject(this);
        // 连续打印2次USB对象，第一次创建一个usb对象，第二次返回第一次创建的usb对象
        System.out.println(usb.get());
        System.out.println(usb.get());
    }
}
