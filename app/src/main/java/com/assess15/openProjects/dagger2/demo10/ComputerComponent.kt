package com.assess15.openProjects.dagger2.demo10

import dagger.Component

@Component(modules = [MouseModule::class])
interface ComputerComponent {
    fun inject(computer: Computer)
}
