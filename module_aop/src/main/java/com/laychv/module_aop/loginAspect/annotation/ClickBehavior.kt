package com.laychv.module_aop.loginAspect.annotation

// 用户点击痕迹（行为统计）  IoC容器
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER) // 目标作用在方法之上
@Retention(AnnotationRetention.RUNTIME)
annotation class ClickBehavior(val value: String)
