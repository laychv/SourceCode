package cn.ju.openProject.dagger2.demo4

import dagger.Component

@Component
interface ClassComponent {
    fun inject(classA: ClassA)
}