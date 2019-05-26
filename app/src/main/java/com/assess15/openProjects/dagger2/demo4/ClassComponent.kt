package com.assess15.openProjects.dagger2.demo4

import com.assess15.openProjects.dagger2.demo4.ClassA
import dagger.Component

@Component
interface ClassComponent {
    fun inject(classA: ClassA)
}