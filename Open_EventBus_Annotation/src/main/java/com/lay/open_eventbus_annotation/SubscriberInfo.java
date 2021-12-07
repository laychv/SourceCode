package com.lay.open_eventbus_annotation;

public interface SubscriberInfo {
    // 订阅所属类
    Class<?> getSubscriberClass();

    // 获取订阅所属类中所有订阅事件的方法
    SubscriberMethod[] getSubscriberMethods();
}
