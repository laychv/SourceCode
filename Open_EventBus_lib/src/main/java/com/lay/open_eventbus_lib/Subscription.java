package com.lay.open_eventbus_lib;

import com.lay.open_eventbus_annotation.SubscriberMethod;

/**
 * 临时JavaBean对象，也可以直接写在EventBus做为变量
 */

public class Subscription {
    final Object subscriber;
    final SubscriberMethod subscriberMethod;

    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }

    @Override
    public boolean equals(Object o) {
        // 必须重写方法，检测激活粘性事件重复调用（同一对象注册多个）
        if (o instanceof Subscription) {
            // 删除官方：subscriber == otherSubscription.subscriber判断条件
            // 原因：粘性事件Bug，多次调用和移除时重现，参考Subscription.java 37行
            return subscriberMethod.equals(((Subscription) o).subscriberMethod);
        } else {
            return false;
        }
    }

}
