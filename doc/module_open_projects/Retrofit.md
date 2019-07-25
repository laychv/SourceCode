#### Retrofit2
* 概述：App应用程序通过Retrofit请求网络，实际上是使用Retrofit接口层封装请求参数，之后由OkHttp完成后续的请求操作
* 在服务端返回数据之后，OkHttp将原始的结果交给Retrofit，Retrofit根据用户的需求对结果进行解析

> 7步使用：

  * 1.导入依赖:implementation 'com.squareup.retrofit2:retrofit:2.4.0'
  * 2.创建 接受服务器返回数据 实体类
  * 3.创建 用于描述网络请求接口 @GET()
  * 4.通过Builder创建网络请求实例
  * 5.创建 网络请求接口实例 create
  * 6.发送网络请求(异步/同步)
  * 7.处理服务器返回的数据

> 网络通讯8步

* 1.创建Retrofit实例
* 2.定义一个网络接口并为接口中方法添加注解3.通过动态代理生成网络请求对象
* 4.通过网络请求适配器将网络请求对象进行平台适配
* 5.通过网络请求执行器发送网络请求
* 6.通过数据转换器解析数据
* 7.通过回调执行器切换数据
* 8.用户在主线程处理返回结果



## Retrofit中 设计模式

#### 代理设计模式 [代理模式](https://blog.csdn.net/briblue/article/details/73928350)

为其他对象提供一种代理，用以控制对这个对象的访问

> 静态代理 ： 一个实现类对应一个代理类，一一对应关系
举例：打官司

> 动态代理 ： 代理类在程序运行时创建的代理方式
优点：无侵入，增强方法
写法：jdk动态代理(反射机制)，cglib
注意：动态代理，代理类是接口，静态代理，代理类是类 

InvocationHandler
* 每个代理类的对象都会关联一个表示内部处理逻辑的InvocationHandler接口的实现
* invoke 方法的参数中可以获取参数
* invoke 方法返回值被返回给使用者

##### 总结：
* 运行期实现代理
* InvocationHandler接口和invoke类
* 动态代理和静态代理的最大的不同
* 动态代理类不需要手动生成，是在运行期间动态生成的


#### 观察者模式



#### 工厂设计模式 factory


#### Build方法创建Retrofit

* platform
* callFactory
* baseUrl
* converterFactories
* callAdapterFactories
* callbackExecutor
* validateEagerly

#### 外观模式


#### 策略模式


#### 适配器模式


