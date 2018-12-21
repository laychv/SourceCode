package cn.ju.openProject.dagger2.demo1

/**
 * 接口方式实现依赖注入
 */
interface ClassBInterface {
    fun setB(b: ClassBInterface)
}