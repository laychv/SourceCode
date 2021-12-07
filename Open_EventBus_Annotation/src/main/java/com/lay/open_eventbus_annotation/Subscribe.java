package com.lay.open_eventbus_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 该注解作用在方法之上
@Target(ElementType.METHOD)
// 要在编译时进行一些预处理操作,注解会在class文件中存在
@Retention(RetentionPolicy.CLASS)
public @interface Subscribe {
    // 线程模式,默认POSTING
    ThreadMode threadMode() default ThreadMode.POSTING;

    // 是否使用粘性事件
    boolean sticky() default false;

    // 事件订阅优先级, 在同一个线程中, 数值越大优先级越高
    int priority() default 0;
}