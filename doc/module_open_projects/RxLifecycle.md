## [RxLifecycle](https://github.com/trello/RxLifecycle)

### 为什么使用RxLifecycle？

- 解决Rx内存泄漏问题
- 通过监听Activity，Fragment生命周期，自动断开Rx绑定
- 搭配Dagger2实现通用配置

### RxLifecycle配置

> api "com.trello.rxlifecycle2:rxlifecycle-components:2.2.2"
> api "com.trello.rxlifecycle2:rxlifecycle-kotlin:2.2.2"

缺点：代码侵入性太强
- 必须要继承RxLifecycle中的RxActivity,RxFragment
