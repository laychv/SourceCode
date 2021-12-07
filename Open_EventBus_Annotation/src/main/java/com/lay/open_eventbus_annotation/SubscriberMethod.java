package com.lay.open_eventbus_annotation;

import java.lang.reflect.Method;

/**
 * 所有订阅方法封装类
 */
public class SubscriberMethod {
    // 订阅方法名
    private String methodName;
    // 订阅方法, 用于最后的自动执行订阅方法
    private Method method;
    // 线程模式
    private ThreadMode threadMode;
    // 事件对象Class
    private Class<?> eventType;
    // 事件订阅优先级
    private int priority;
    // 是否粘性事件
    private boolean sticky;

    public SubscriberMethod(Class subscriberClass,
                            String methodName,
                            Class<?> eventType,
                            ThreadMode threadMode,
                            int priority,
                            boolean sticky) {
        this.methodName = methodName;
        this.eventType = eventType;
        this.threadMode = threadMode;
        this.priority = priority;
        this.sticky = sticky;

        try {
            // 订阅所属类
            method = subscriberClass.getDeclaredMethod(methodName, eventType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getMethod() {
        return method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isSticky() {
        return sticky;
    }
}
