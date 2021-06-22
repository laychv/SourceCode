package com.laychv.module_open_projects.dagger2.demo4

import javax.inject.Inject

class ClassA {
    @Inject
    lateinit var mClassB: ClassB

    fun doSomething() {
        mClassB.sayHello()
    }

    fun ClassA() {
        DaggerClassComponent.builder().build().inject(this)
        doSomething()
    }
}

