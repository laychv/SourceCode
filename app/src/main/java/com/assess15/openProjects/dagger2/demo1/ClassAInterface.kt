package com.assess15.openProjects.dagger2.demo1

class ClassAInterface : ClassBInterface {

    private var cb: ClassBInterface? = null

    override fun setB(b: ClassBInterface) {
        cb = b
    }

}