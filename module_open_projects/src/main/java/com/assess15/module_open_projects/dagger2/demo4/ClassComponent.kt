package com.assess15.module_open_projects.dagger2.demo4

import dagger.Component

@Component
interface ClassComponent {
    fun inject(classA: ClassA)
}