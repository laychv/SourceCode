package com.laychv.module_open_projects.dagger2.demo10

import javax.inject.Inject
import javax.inject.Named

/**
 * 如遇bug，参见：
 * https://stackoverflow.com/questions/47187657/kotlin-error-dagger-does-not-support-injection-into-private-fields
 */
class Computer @Inject constructor() {

    @Inject
    @field:[Named("wired")]
    lateinit var mMouse: Mouse
    // 注意这里必须使用lateinit，否则无法找到私有域

    fun init() {
        DaggerComputerComponent.builder().build().inject(this)
        println(mMouse.name())
    }
}
