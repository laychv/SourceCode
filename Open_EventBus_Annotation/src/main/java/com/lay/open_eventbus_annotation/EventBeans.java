package com.lay.open_eventbus_annotation;

/**
 * 所有事件集合
 */
public class EventBeans implements SubscriberInfo {

    // 订阅者对象Class
    private final Class subscriberClass;
    // 订阅方法数组
    private final SubscriberMethod[] methodInfos;

    public EventBeans(Class subscriberClass, SubscriberMethod[] methodInfos) {
        this.subscriberClass = subscriberClass;
        this.methodInfos = methodInfos;
    }

    @Override
    public Class<?> getSubscriberClass() {
        return subscriberClass;
    }

    @Override
    public SubscriberMethod[] getSubscriberMethods() {
        return methodInfos;
    }
}
