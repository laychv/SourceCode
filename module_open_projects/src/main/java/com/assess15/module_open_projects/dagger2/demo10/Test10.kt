package com.assess15.module_open_projects.dagger2.demo10

import com.assess15.module_open_projects.dagger2.demo10.Computer

object Test10 {
    @JvmStatic
    fun main(args: Array<String>) {
        val computer = Computer()
        computer.init()
    }
}
