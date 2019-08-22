package com.assess15.module_aop.click

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect
class AopClickAspect {

    /**
     * 定义切点，标记切点为所有被@AopOnclick注解的方法
     * 注意：这里com.freak.kotlinhttpmanager.aop.AopOnclick需要替换成
     * 你自己项目中AopOnclick这个类的全路径
     */
    @Pointcut("execution(@com.assess15.module_base.aop.click.AopOnclick * *(..))")
    fun methodAnnotated() {
    }

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("methodAnnotated()")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint) {
        // 取出方法的注解
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        if (!method.isAnnotationPresent(AopOnclick::class.java)) {
            return
        }
        val aopOnclick = method.getAnnotation(AopOnclick::class.java)
        // 判断是否快速点击
        if (!AopClickUtils.isFastDoubleClick(aopOnclick.value)) {
            // 不是快速点击，执行原方法
            joinPoint.proceed()
        }
    }
}
