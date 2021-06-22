package com.laychv.module_open_projects.dagger2.demo10

import dagger.Component

@Component(modules = [MouseModule::class])
interface ComputerComponent {
    fun inject(computer: Computer)
}
