package com.assess15.module_aop.click

@Target(AnnotationTarget.FUNCTION)
annotation class AopOnclick(val value: Long = 1000)