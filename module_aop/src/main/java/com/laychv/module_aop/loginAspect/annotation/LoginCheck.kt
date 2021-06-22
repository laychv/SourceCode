package com.laychv.module_aop.loginAspect.annotation

// 用户登录检测
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER) // 目标作用在方法之上
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginCheck
