package com.laychv.module_aop.click

@Target(AnnotationTarget.FUNCTION)
annotation class AopOnclick(val value: Long = 1000)