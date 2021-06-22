package com.laychv.module_open_projects.dagger2.demo1

class ClassAInterface : ClassBInterface {

    private var cb: ClassBInterface? = null

    override fun setB(b: ClassBInterface) {
        cb = b
    }

}