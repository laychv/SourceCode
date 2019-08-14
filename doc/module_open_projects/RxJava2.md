# RxJava2

- 本质：RxJava2属于异步操作库，用于进行耗时任务
- 核心：观察者模式

RxJava2四要素
----------------------------
- Observable：被观察者 (数据的上游，即事件生产者)

- Observer：   观察者   (数据的下游，即事件消费者)

- subscribe：  订阅  (连接被观察者和观察者)

- 事件

## 操作符

### 1.创建操作符

- 创建 被观察者（ `Observable`） 对象 & 发送事件。

### 2.变换操作符

- 对事件序列中的事件 / 整个事件序列 进行**加工处理**（即变换），使得其转变成不同的事件 / 整个事件序列

### 3.组合合并操作符

- 组合 多个被观察者（`Observable`） & 合并需要发送的事件

### 4.功能性操作符

- 辅助被观察者（`Observable`） 在发送事件时实现一些功能性需求

### 5.过滤操作符

- 过滤 / 筛选 被观察者（`Observable`）发送的事件 & 观察者 （`Observer`）接收的事件

### 6.条件布尔操作符

- 通过设置函数，判断被观察者（`Observable`）发送的事件是否符合条件

### 7.map/flatMap区别

- map：操作符的作用就是将Observable所发送送的信息进行格式转换或者处理,然后转变为另外一个类型，发送给Observer 事件一一对应，映射成新的事件。一对一的转换。

- flatMap：变换操作符，使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable，然后flatMap合并这些Observable发射的数据，最后将合并后的结果当作它自己的数据序列发射。一对多的转换，也可以用来完成遍历。

- 将所有的事件转换为Observable,通过Observable进行事件分发

---

## 异步

### 1.线程调度器

*用来制定事件或者数据在什么线程中处理的*

- Schedulers.single()：单线程调度器，线程可复用
- Schedulers.newThread()：为每个任务创建新的线程
- Schedulers.io()：处理I/O密集任务，内部线程池实现，可根据需求增长
- Schedulers.computation()：处理计算任务，如事件循环和回调任务
- AndroidSchedulers.mainThread()：Android主线程调度器，属于RxAndroid

### 2.线程控制符

- **subscribeOn**(Schedulers.io()) 被观察者的线程切换

  - 指定被观察者的代码在哪种线程中执行
  - 可以调用一次，但是第一次起作用 (通过Observable方式创建)

- **observeOn**(AndroidSchedulers.mainThread()) 观察者的线程切换

  - 指定后续的操作符及观察者的代码在哪种线程中执行

  - 可以调用数次，每次调用都生效

- subscribe()

### 3.背压

所谓背压，即生产者的速度大于消费者的速度带来的问题，比如在Android中常见的点击事件，点击过快则经常会造成点击两次的效果。

- 背压的被观察者和观察者：**Flowable/Subscriber**

- 背压策略：
  - BackpressureStrategy.MISSING：不使用背压，没有缓存。
  - BackpressureStrategy.BUFFER：缓存所有数据，直到观察者处理。不设置缓存大小，如果观察者处理不及时，会OOM
  - BackpressureStrategy.DROP：丢弃超时缓存大小的数据。
  - BackpressureStrategy.LATEST：超出缓存大小时，用新的数据覆盖老数据。

### 4.注意：

- 默认情况下，被观察者和观察者在同一线程中执行

- 如果不使用create()创建Flowable，而是使用range(),interval()等操作符创建，就不能配置BackpressureStrategy了，但是提供了别的方法，onBackpressureBuffer()，onBackpressureDrop()，onBackpressureLatest()。

---

## RxJava 1.x RxJava 2.x 区别？

    RxJava 1.x
    Observable --> Observer 不支持背压
    Observable --> Subscriber 支持背压
    
    RxJava 2.x
    Observable --> Observer 不支持背压
    Flowable   --> Subscriber 支持背压
