### EventBus 单例模式创建

> 功能:通过解耦发布者和订阅者简化Android事件传递
  EventBus可以代替Android传统的Intent,Handler,Broadcast或接口函数,在Fragment,Activity,Service线程之间传递数据，执行方法。

> 特点:代码简洁，是一种发布订阅设计模式(观察者设计模式)。

> 优点:与订阅解耦

> 原理图:![EventBus](https://baike.baidu.com/pic/EventBus/20461274/0/aa64034f78f0f736e9d6d1800355b319ebc41302?fr=lemma&ct=single#aid=0&pic=aa64034f78f0f736e9d6d1800355b319ebc41302)

#### EventBus 使用步骤

 * 1.注册EventBus
 * 2.注销EventBus
 * 3.发送事件
 * 4.接收事件
 
#### Handler线程创建

* 1.主线程发送事件,子线程处理
* 2.子线程发送事件,主线程处理

> 1.new Thread(new Runnable() {  
                public void run() {  
                    Looper.prepare();  // 此处获取到当前线程的Looper，并且prepare()  
                    Handler handler = new Handler(){  
                        @Override  
                        public void handleMessage(Message msg) {  
                            Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();  
                        }  
                    };  
                    handler.sendEmptyMessage(1);  
                };  
            }).start();
            
> 2.new Thread(new Runnable() {  
                public void run() {  
                    Handler handler = new Handler(Looper.getMainLooper()){ // 区别在这！！！！  
                        @Override  
                        public void handleMessage(Message msg) {  
                            Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();  
                        }  
                    };  
                    handler.sendEmptyMessage(1);  
                };  
            }).start();

#### 源码分析

> EventBus 核心类: HandlerPoster,BackgroundPoster,AsyncPoster 负责线程调度
> 注意:3.1.1中用的MainThreadSupport接口，最终使用HandlerPoster实现类

> 创建:getDefault()(就是单利模式,注意:使用的是public)

> 注解:4个枚举对应4个核心类 线程调度
> * 1.MAIN       -> HandlerPoster       Handler
> * 2.ASYNC      -> AsyncPoster         Runnable
> * 3.BACKGROUND -> BackgroundPoster    Runnable

> register -> findSubscriberMethods -> SubscriberMethod -> (findUsingInfo -> checkAdd())-> 
> findState(保存状态) -> prepareFindState -> initForSubscriber(复制操作) -> 
> getSubscriberInfo(获取订阅者信息) -> findUsingReflectionInSingleClass ->

subscribe

* 1.首先判断是否有注册过该事件
* 2.然后再按照优先级加入到subscriptionByEventType的value的List中
* 3.然后再添加到typesBySubscriber的value的List中
* 4.分发事件：checkPostStickyEventToSubscription

检查分发事件                            发布到订阅发
checkPostStickyEventToSubscription -> postToSubscription(..,..,isMainThread())判断是否在主

postToSubscription方法判断 POSTING,MAIN,MAIN_ORDERED,BACKGROUND,ASYNC 


post

判断是否在主线程

发送事件线程状态封装类
PostingThreadState

发布单个事件
postSingleEvent

查找所以事件父类
lookupAllEventTypes


#### [粘性事件](https://www.cnblogs.com/fuyaozhishang/p/7968059.html)

使用场景
我们要把一个Event发送到一个还没有初始化的Activity/Fragment，即尚未订阅事件。那么如果只是简单的post一个事件，那么是无法收到的，这时候，你需要用到粘性事件,它可以帮你解决这类问题.

* 事件消费者在事件发布之后才注册的也能接收到该事件的特殊类型
* Sticky Broadcast
* 在广播发送结束后会保存刚刚发送的广播
* AndroidEventBus会存储所有的Sticky事件

