package com.assess15.openProjects.dagger2.demo4

import javax.inject.Inject

class ClassB @Inject constructor(){
    fun sayHello(){
        println("hello")
    }
}