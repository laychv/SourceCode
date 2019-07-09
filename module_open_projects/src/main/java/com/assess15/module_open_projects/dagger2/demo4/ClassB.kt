package com.assess15.module_open_projects.dagger2.demo4

import javax.inject.Inject

class ClassB @Inject constructor(){
    fun sayHello(){
        println("hello")
    }
}