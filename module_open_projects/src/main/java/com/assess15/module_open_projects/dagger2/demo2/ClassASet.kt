package com.assess15.module_open_projects.dagger2.demo2

/**
 * set方式
 */
class ClassASet {

    var cb: ClassBSet? = null

    fun setClassB(b: ClassBSet) {
        cb = b
    }
}