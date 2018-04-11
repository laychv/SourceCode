
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
  
#### 代理设计模式 [代理模式](https://blog.csdn.net/briblue/article/details/73928350)

> 静态代理


> 动态代理

#### 工厂设计模式 factory


#### Build方法创建Retrofit

* platform
* callFactory
* baseUrl
* converterFactories
* callAdapterFactories
* callbackExecutor
* validateEagerly