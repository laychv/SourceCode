## Dagger2

Dagger2作用：通过编译时注解的方式，帮助我们自动生成创建对象工厂类
编译时产生的类文件位置：build -> generated -> source -> kapt -> debug -> ***
编译时产生的工厂类，帮助我们完成对象初始化过程

> 需要在一个对象中创建另一个对象的时候

**注入方式(4种)**

* 1. 接口方式
* 2. set方式
* 3. 构造方式
* 4. 注解方式

**注解：**
注解，Java1.5后才有的，注解即标注，Java中的类、方法、变量、参数、包都可以被注解，告诉编译器，这个标注的作用
注意：注解仅仅是元数据，和业务逻辑无关

上手流程
在Kotlin中使用

- 激活插件
apply plugin: 'kotlin-kapt'
- 添加依赖
- api "com.google.dagger​ : dagger:$dagger_version"
- kapt "com.google.dagger:dagger-compiler:$dagger_version"

kapt作用：根据注解预先生成代码

在Java中使用
annotationProcessor 'com.google.dagger:dagger-compiler:2.14.1'
implementation 'com.google.dagger​ : dagger:2.16'

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
class ClassA {
    @Inject
    lateinit var mClassB : ClassB // 成员变量添加注解

    fun doSomething(){
        mClassB.sayHello()
    }
  
  fun ClassA() {
        DaggerClassComponent.builder().build().inject(this)// 注入成功(以Dagger开头)
    }
}

class ClassB @Inject constructor(){ // 构造方法添加注解
    fun sayHello(){
        print("hello")
    }
}

@Component
interface ClassComponent { // 关联成员变量与构造方法
    fun inject(classA: ClassA)
}
```

---

## Dagger2基本注解

### @Inject
1.标记在需要依赖的**成员变量**上
2.使用在**构造函数**上
> 依赖注入是依赖的对象实例 -> 需要注入实例的属性
> 新建工厂类实例并调用成员属性注入类完成实例的注入

注意：
- @Inject并不是Dagger2提供的，而是使用的JDK中javax.inject包下的

```
我是@Inject标注的构造函数类                             我是@Inject标注的实例属性

                                       @Component

classB @Inject constructor(){...}                    classA {...}

```

### @Component
1. 注入器：连接目标类和依赖实例的桥梁
用来连接@Inject标注的构造函数和@Inject标注的实例属性
2. 以@Component标注的类必须是**接口**或者**抽象类**,完成依赖注入过程
3. Component依赖关系通过dependencies属性添加
4. App必须有一个Component用来管理全局属性

### @Module
@Module的产生就是为了解决@Inject的局限：仅限可以修改源码的类提供依赖
@Module就是为了**解决不能通过注解构造方法**来创建一个实例的问题，通常给不能修改源码的类提供依赖（例如：Activity中注入TextView，我们无法TextView修改源码）

1. 第三方库无法修改，不能在其构造函数添加@Inject
2. 接口不能实例化，只能通过实现类实例化
3. Module是一个简单工厂，创建类实例的方法
4. Component通过module属性添加多个Module

这样的阐述可能不严谨，我自己创建的类，就是不想改代码，依然可以使用@Module

### @Provides
1. 在@Module中，使用@Provides标注创建实例的方法（）
2. 实例化流程
- Component搜索@Inject注解的属性
- Component查找Module中以@Provider注解的对应的方法，创建实例

**注意：@Provider只能使用在@Module中**

**Inject和Module维度**

- Module优先级高于Inject构造函数
- 查找到实例对象，依次查看其参数实例化
- Module中存在创建实例方法，停止查找Inject维度，如果没有，查找Inject构造函数（这是个递归的过程）

### @Singleton

- demo11
- 作用域注解
- 作用域仅限在Component中实现单例模式

分析：

使用@Singleton分别注解MainBoard和ComputerComponent类，表明MainBoard和ComputerComponent绑定，一个ComputerComponent对象只会存在一个MainBoard实例。虽然在Computer中声明了2个MainBoard对象，注入后，这2对象实际上指向的是同一个MainBoard对象。

注意：

一个ComputerComponent对象中保持一个MainBoard实例，如果新建ComputerComponent，又会新建MainBoard实例，所以这个并不是应用层级全局单例模式，而只是在Component对象中的单例模式。

**@Scope是个接口，有个子类@Singleton**

```
@Target(ANNOTATION_TYPE)
@Retention(RUNTIME)
@Documented
public @interface Scope {}
```

**@Singleton是@Scope实现类**

- 是@Scope的默认实现
- @Singleton没有实现单例能力，只是标注表示单例
- ApplicationComponent单例是代码控制实现的

### @Scope

- 元注解
- 范围，作用域

**自定义@Scope**

- 以Component组织方式实现自定义Scope
```
@Scope
@Retention(RetentionPolicy.RunTime)
annotation class ActivityScope
```

### @Qualifier
- 是个元注解
- 注解迷失：同一个接口有多个实现类，编译报错，分不清使用哪一个实现类
- 使用限定符区分

注意：
- @Module中可以有一个或者多个@Provides，如果有多个@Provides，编译时报错，不知道需要调用那个@Provides；通过使用@Qualifier，指定调用那个

**自定义@Qualifier**

```
@Qualifier
@Retention(RetentionPolicy.RunTime)
annotation class ActivityQualifier
```

### @Named
- 是@Qualifier的一种实现方式
- 以名称区分是哪种注解实现
- demo10

**Lazy**

- demo5
- 在Dagger中提供了懒加载的实现，可以不用在注入时候初始化所有对象
- 使用懒加载时，后续获取的都是同一个实例

**Provider**
- demo5
- 需要懒加载并且每次获取的都是一个新实例

**Qualifier**

- 注解迷失 demo7

```
e: /Users/ProjectsAndroidStudio/GitHub/SourceCode/app/src/main/java/com/assess15/openProjects/dagger2/demo7/ComputerComponent.java:7: 错误: com.assess15.openProjects.dagger2.demo7.Mouse is bound multiple times:
    void inject(Computer computer);
         ^
      @Provides com.assess15.openProjects.dagger2.demo7.Mouse com.assess15.openProjects.dagger2.demo7.MouseModule.ProvidesWireMouse()
      @Provides com.assess15.openProjects.dagger2.demo7.Mouse com.assess15.openProjects.dagger2.demo7.MouseModule.ProdidesWirelessMouse()

FAILURE: Build failed with an exception.
```

- 分析

在MouseModule中提供了2个方法返回不同的Mouse对象（WireMouse/WirelessMouse），
而Computer中注入一个Mouse对象，这让Dagger困惑，不知道使用哪个方法来创建Mouse实例

- 解决方式：

- demo8 通过自定义Qualifier方式解决

使用Qualifier
在Computer类中添加Mouse依赖时指定使用哪个方法返回的Mouse对象

- demo9 通过@Named方式解决

- demo10 在Kotlin中使用 注意：kotlin中使用遇到的坑

```
e: /Users/ProjectsAndroidStudio/GitHub/SourceCode/app/build/tmp/kapt3/stubs/debug/com/assess15/openProjects/dagger2/demo10/Computer.java:14: 错误: Dagger does not support injection into private fields
    private com.assess15.openProjects.dagger2.demo10.Mouse mMouse;
                                                           ^
e: /Users/ProjectsAndroidStudio/GitHub/SourceCode/app/build/tmp/kapt3/stubs/debug/com/assess15/openProjects/dagger2/demo10/ComputerComponent.java:9: 错误: com.assess15.openProjects.dagger2.demo10.Computer cannot be provided without an @Inject constructor or from an @Provides-annotated method.
    public abstract void inject(@org.jetbrains.annotations.NotNull()
                         ^
      com.assess15.openProjects.dagger2.demo10.Computer is injected at
          com.assess15.openProjects.dagger2.demo10.ComputerComponent.inject(computer)

FAILURE: Build failed with an exception.
```

- 解决：
    lateinit var mMouse: Mouse



