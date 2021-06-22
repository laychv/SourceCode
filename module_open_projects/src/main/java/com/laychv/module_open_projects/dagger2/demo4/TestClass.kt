package com.laychv.module_open_projects.dagger2.demo4

/**
 * 测试Dagger2 @Inject 注解
 * 注意：使用在kotlin中使用Dagger2时，需要添加 apply plugin:'kotlin-kapt' ;使用 kapt 'com.google.dagger:dagger-compiler:2.14.1'
 * 否则在kotlin中不会生成dagger代码
 */
fun main() {
    var classA = ClassA()
    classA.doSomething()
}