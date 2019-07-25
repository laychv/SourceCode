#### OkHttp 核心类

##### Dispatcher分发器


##### Interceptor拦截器
 * RetryAndFollowUpInterceptor
 * BridgeInterceptor
 * CacheInterceptor
 * ConnectInterceptor
 * CallServerInterceptor

>
   * 同步方法调用一旦开始，调用者必须等到方法调用返回后，才能继续后续的行为。
   * 异步方法调用更像一个消息传递，一旦开始，方法调用就会立即返回，调用者就可以继续后续的操作。而，异步方法通常会在另外一个线程中，“真实”地执行着。整个过程，不会阻碍调用者的工作。




