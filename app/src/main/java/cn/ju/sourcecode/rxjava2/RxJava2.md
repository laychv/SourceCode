RxJava2
==========

> 本质:RxJava2属于异步操作库，用于进行耗时任务
> 核心:观察者模式 

观察者模式
-------------------------
> * 一对多(多个观察者，对应一个被观察者)

RxJava2四要素
----------------------------
> * Observable 被观察者 (数据的上游，即事件生产者)
> * Observer   观察者   (数据的下游，即事件消费者)
> * subscribe  订阅
> * 事件

入手
----------------------------
- Observable.create

操作符
----------------------------
- 区别:
> - map：操作符的作用就是将Observable所发送送的信息进行格式转换或者处理,然后转变为另外一个类型，发送给Observer 
> - 事件一一对应，映射成新的事件
> - flatMap：变换操作符，使用一个指定的函数对原始Observable发射的每一项数据执行变换操作，这个函数返回一个本身也发射数据的Observable，然后flatMap合并这些Observable发射的数据，最后将合并后的结果当作它自己的数据序列发射。
> - 将所有的事件转换为Observable,通过Observable进行事件分发

线程控制
----------------------------

- 解决：Handler/Async

线程控制符
----------------------------

- subscribeOn(Schedulers.io())
> 可以调用一次 (通过Observable方式创建)

- observeOn(AndroidSchedulers.mainThread())
> 可以调用数次

- subscribe()
>

