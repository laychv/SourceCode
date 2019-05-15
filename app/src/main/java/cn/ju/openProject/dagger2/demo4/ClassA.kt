package cn.ju.openProject.dagger2.demo4

import javax.inject.Inject

class ClassA {
    @Inject
    lateinit var mClassB: ClassB

    fun doSomething() {
        mClassB.sayHello()
    }

    fun main() {
        DaggerClassComponent.builder().build().inject(this)
        doSomething()
    }
}

