##Dagger2

Dagger2作用：通过编译时注解的方式，帮助我们自动生成创建对象工厂类

> 需要在一个对象中创建另一个对象的时候

**注入方式(4种)**

* 1.接口方式
* 2.set方式
* 3.构造方式
* 4.注解方式


上手流程
在Kotlin中使用
- 激活插件
apply plugin: 'kotlin-kapt'
- 添加依赖
api "com.google.dagger:dagger:$dagger_version"
kapt "com.google.dagger:dagger-compiler:$dagger_version"

在Java中使用
annotationProcessor 'com.google.dagger:dagger-compiler:2.14.1'
implementation 'com.google.dagger:dagger:2.16'

kapt作用：根据注解预先生成代码

**未使用**
```kotlin
class ClassB{
    fun sayHello(){
        print("hello")
    }
}

class ClassA{
    var mClassB:ClassB
    init{
        mClassB = ClassB()
    }
    fun doSomething(){
        mClassB.sayHello()
    }
}
```

**使用Dagger2**
```kotlin
class ClassB @Inject constructor(){
    fun sayHello(){
        print("hello")
    }
}

class ClassA {
    @Inject
    lateinit var mClassB : ClassB

    fun doSomething(){
        mClassB.sayHello()
    }
}

```

##Dagger2基本注解

### @Inject
1.标记在需要依赖的变量上
2.使用在构造函数上
> 依赖注入是依赖的对象实例 -> 需要注入实例的属性
> 新建工厂类实例并调用成员属性注入类完成实例的注入

```
我是@Inject标注的构造函数类                             我是@Inject标注的实例属性

                                       @Component

classB @Inject constructor(){...}                    classA {...}

```

### @Component
1. 注入器：连接目标类和依赖实例的桥梁
用来连接@Inject标注的构造函数和@Inject标注的实例属性
2. 以@Component标注的类必须是接口或者抽象类
3. Component依赖关系通过dependencies属性添加
4. App必须有一个Component用来管理全局属性

### @Module
Module就是为了解决不能通过注解构造方法来创建一个实例的问题（例如：Activity中注入TextView，我们无法TextView修改源码）

1. 第三方库无法修改，不能在其构造函数添加@Inject
2. 接口不能实例化，只能通过实现类实例化
3. Module是一个简单工厂，创建类实例的方法
4. Component通过module属性添加多个Module

### @Provides
1. 在Module中，使用@Provides标注创建实例的方法
2. 实例化流程
- Component搜索@Inject注解的属性
- Component查找Module中以@Provider注解的对应的方法，创建实例

### Inject和Module维度
- Module优先级高于Inject构造函数
- 查找到实例对象，依次查看其参数实例化
- Module中存在创建实例方法，停止查找Inject维度，如果没有，查找Inject构造函数（这是个递归的过程）

### @Scope是个接口，有个子类@Singleton

### @Singleton是@Scope实现类
- 是@Scope的默认实现
- @Singleton没有实现单例能力
- ApplicationComponent单例是代码控制实现的

### 自定义@Scope
- 以Component组织方式实现自定义Scope
```
@Scope
@Retention(RetentionPolicy.RunTime)
annotation class ActivityScope

```

### @Qualifier
- 注解迷失：同一个接口有多个实现类，编译报错，分不清使用哪一个实现类
- 使用限定符区分

### 自定义@Qualifier
```
@Qualifier
@Retention(RetentionPolicy.RunTime)
annotation class ActivityQualifier

```

### @Named
- 是@Qualifier的一种实现方式
- 以名称区分是哪种注解实现



