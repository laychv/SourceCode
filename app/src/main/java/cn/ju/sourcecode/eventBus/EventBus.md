### EventBus  

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

> 注解:4个枚举对应4个核心类
> * 1.MAIN    -> HandlerPoster
> * 2.ASYNC      -> AsyncPoster
> * 3.BACKGROUND -> BackgroundPoster

> register -> findSubscriberMethods -> SubscriberMethod -> findUsingInfo -> 
> findState(保存状态) -> prepareFindState -> initForSubscriber(复制操作) -> 
> getSubscriberInfo(获取订阅者信息) -> 

#### 粘性事件

* 事件消费者在事件发布之后才注册的也能接收到该事件的特殊类型
* Sticky Broadcast
* 在广播发送结束后会保存刚刚发送的广播
* AndroidEventBus会存储所有的Sticky事件

