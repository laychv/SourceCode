### EventBus

功能:通过解耦发布者和订阅者简化Android事件传递EventBus可以代替Android传统的Intent,Handler,Broadcast或接口函数,在Fragment,Activity,Service线程之间传递数据，执行方法。

特点:

代码简洁，是一种发布订阅设计模式(观察者设计模式)。

优点:

与订阅解耦

---

### 使用步骤

#### 0.添加依赖

```groovy
api 'org.greenrobot:eventbus:3.1.1'
```

#### 1.注册/注销

##### *Activity*

- onCreate()/onDestory()
- onStart()/onPause()

##### *Fragment*

- onCreate()/onDestory()
- onCreateView()/onDestoryView()

##### *View*

- onAttachedToWindow()/onDetachedFromWindow()



```java
public MyActivity extends Activity{
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EventBus.getDefault().register(this);
  }

  @Override
  protected void onDestroy() {
      super.onDestroy();
      EventBus.getDefault().unregister(this);
  }
}
```



#### 2.创建事件

创建一个Java类（即为一个事件），可以是任意类型

```java
public class MyEvent {
    String event;
    public MyEvent(String event) {
        this.event = event;
    }
}
```



#### 3.发布事件 post

创建一个事件，通过post方法发送事件，所有订阅该事件的方法都会收到事件

```java
EventBus.getDefault().post(MyEvnet("test"));
```



#### 4.订阅事件 threadMode

在(**Activity**,**Fragment**,**View**)中接收事件

注意：

- 如果没有指定threadMode，**默认都是POSTING类型**（在@Subscribe源码中有所体现）
- 订阅事件的方法必须是**public**方法

**threadMode(线程模型):**

##### ThreadMode.MAIN

```java
// 不管在哪个线程发布事件，都在主线程执行onMainEvent()方法
@Subscribe(threadMode = ThreadMode.MAIN)
public void onMainEvent(MyEvent event){
  Log.d("tag","onMainEvent:" + Thread.currentThread().getName());
}
```

##### ThreadMode.POSTING

```java
// 在哪个线程发布事件，就在哪个线程执行onPostingEvent()方法
@Subscribe(threadMode = ThreadMode.POSTING)
public void onPostingEvent(MyEvent event){
  Log.d("tag","onPostingEvent:" + Thread.currentThread().getName());
}
```

##### ThreadMode.BACKGROUND

```java
// 事件如果在子线程发布，onBackgroundEvent()方法就在该子线程执行；
// 事件如果在主线程发布，onBackgroundEvent()方法就在EventBus内部的线程池中执行
@Subscribe(threadMode = ThreadMode.BACKGROUND)
public void onBackgroundEvent(MyEvent event){
  Log.d("tag","onBackgroundEvent:" + Thread.currentThread().getName());
}
```

##### ThreadMode.ASYNC

```java
// 不管事件在哪个线程发布，onAsyncEvent()方法都在EventBus内部线程池中执行
@Subscribe(threadMode = ThreadMode.ASYNC)
public void onAsyncEvent(MyEvent event){
  Log.d("tag","onAsyncEvent:" + Thread.currentThread().getName());
}
```



#### 5.事件优先级 priority

当一个事件**同时有几个方法接收**，并在**同一ThreadMode下**，可以通过配置优先级来指定它们的调用先后顺序；

优先级priority**默认是0**（@在Subscribe源码中有所体现），**数值越大，执行顺序越优先**

```java
@Subscribe(priority = 1)
public void onEvent1(MyEvent ev){
  Log.d("tag","onEvent1");
}
@Subscribe(priority = 2)
public void onEvent2(MyEvent ev){
  Log.d("tag","onEvent2");
}
```



#### [6.粘性事件](https://www.cnblogs.com/fuyaozhishang/p/7968059.html) sticky

**默认是false**（在@Subscribe源码中有所体现）

使用场景
我们要把一个Event发送到一个还没有初始化的Activity/Fragment，即尚未订阅事件。那么如果只是简单的post一个事件，那么是无法收到的，这时候，你需要用到粘性事件,它可以帮你解决这类问题；相当于EventBus帮你做了缓存。

- 事件消费者在事件发布之后才注册的也能接收到该事件的特殊类型
- Sticky Broadcast
- 在广播发送结束后会保存刚刚发送的广播
- AndroidEventBus会存储所有的Sticky事件

```java
EventBus.getDefault().postSticky(new MyEvent("test2"));

@Subscribe(threadMode = TheadMode.MAIN,sticky = true)
public void onEvent(MyEvent ev){
	Log.d("tag","onEvent" + ev);
}
```



---

### 源码分析

EventBus主要逻辑：注册、发布事件

#### 1.注解 - @Subscribe

```java
@Documented//javadoc生成文档时包括Subscribe注解，因为默认生成文档是不包括注解的
@Retention(RetentionPolicy.RUNTIME)// 注解会保留到虚拟机中
@Target({ElementType.METHOD})// 表示@Subscribe可以注解METHOD（方法）
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.POSTING;// 初始化 ThreadMode，默认POSTING

    boolean sticky() default false;// 初始化 sticky，默认false

    int priority() default 0; // 初始化 priority，默认0
}
```



#### 2.初始化 - getDefault()

```java
static volatile EventBus defaultInstance;
...
public static EventBus getDefault() { // 单例模式，采用双重校验模式DCL创建的单例
    if (defaultInstance == null) {
        synchronized (EventBus.class) {
            if (defaultInstance == null) {
                defaultInstance = new EventBus();
            }
        }
    }
    return defaultInstance;
}
...
```



#### 3.注册 - register

```java
 public class EventBus {
    ...
 /**
   * 通过subscriberMethodFinder类的findSubscriberMethods方法找到注册到EventBus的subscriber类中所有           	  * 被@Subscriber注解的订阅事件的方法，并且遍历所有找到的订阅方法，调用subscribe方法保存 
   * subscribeMethod。findSubscribeMethods方法把找到的监听方法封装成SubscribeMethod对象。
   */
    public void register(Object subscriber) {
            Class<?> subscriberClass = subscriber.getClass();
            List<SubscriberMethod> subscriberMethods = subscriberMethodFinder.findSubscriberMethods(subscriberClass);
            synchronized (this) {
                for (SubscriberMethod subscriberMethod : subscriberMethods) {
                    subscribe(subscriber, subscriberMethod);
                }
            }
        }
  
    ...
      
     private void subscribe(Object subscriber, SubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventType;
        Subscription newSubscription = new Subscription(subscriber, subscriberMethod);
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions == null) {
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType, subscriptions);
        } else {
            if (subscriptions.contains(newSubscription)) {
                throw new EventBusException("Subscriber " + subscriber.getClass() + " already registered to event "
                        + eventType);
            }
        }

        int size = subscriptions.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || subscriberMethod.priority > subscriptions.get(i).subscriberMethod.priority) {
                subscriptions.add(i, newSubscription);
                break;
            }
        }

        List<Class<?>> subscribedEvents = typesBySubscriber.get(subscriber);
        if (subscribedEvents == null) {
            subscribedEvents = new ArrayList<>();
            typesBySubscriber.put(subscriber, subscribedEvents);
        }
        subscribedEvents.add(eventType);

        if (subscriberMethod.sticky) {
            if (eventInheritance) {
               
                Set<Map.Entry<Class<?>, Object>> entries = stickyEvents.entrySet();
                for (Map.Entry<Class<?>, Object> entry : entries) {
                    Class<?> candidateEventType = entry.getKey();
                    if (eventType.isAssignableFrom(candidateEventType)) {
                        Object stickyEvent = entry.getValue();
                        checkPostStickyEventToSubscription(newSubscription, stickyEvent);
                    }
                }
            } else {
                Object stickyEvent = stickyEvents.get(eventType);
                checkPostStickyEventToSubscription(newSubscription, stickyEvent);
            }
        }
    }
 }   
```

```java
class SubscriberMethodFinder {
  ...
  /**
  * findSubscriberMethods方法是如何找到一个类中的所有被@Subscribe注解的方法？
  */
     List<SubscriberMethod> findSubscriberMethods(Class<?> subscriberClass) {
        // 从缓存中查询注册的类对应的订阅方法的列表
        List<SubscriberMethod> subscriberMethods = METHOD_CACHE.get(subscriberClass);
    		// 如果有缓存，则直接返回
        if (subscriberMethods != null) {
            return subscriberMethods;
        }
				// 默认为false
        if (ignoreGeneratedIndex) {
          	// 通过反射获取
            subscriberMethods = findUsingReflection(subscriberClass);
        } else {
          	// 通过编译时注解生成的代码获取订阅方法 
            subscriberMethods = findUsingInfo(subscriberClass);
        }
        if (subscriberMethods.isEmpty()) {
            throw new EventBusException("Subscriber " + subscriberClass
                    + " and its super classes have no public methods with the @Subscribe annotation");
        } else {
          	// 放入缓存
            METHOD_CACHE.put(subscriberClass, subscriberMethods);
            return subscriberMethods;
        }
    }
  /**
  * EventBus会将一个类中订阅方法的列表缓存起来，如果有缓存，则直接返回；
  * 如果没有缓存，则通过反射或者通过编译时生成的代码来找到订阅方法列表，并将结果缓存起来。
  */
...
}
```



#### 4.注销-unregister



#### 5.发布-post





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

---

### Handler线程创建

- 1.主线程发送事件,子线程处理
- 2.子线程发送事件,主线程处理

```Java
new Thread(new Runnable() {
            public void run() {
                Looper.prepare();  // 此处获取到当前线程的Looper，并且prepare()  
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
                    }
                };
                handler.sendEmptyMessage(1);
            }
        }).start();
```



```Java
new Thread(new Runnable() {  
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
```

