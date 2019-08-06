MVP模式（Model View Presenter）
可以说是MVC模式（Model View Controller）在Android开发上的一种变种、进化模式

Model：数据层，负责处理数据的加载或者存储
View：视图层，负责界面数据的展示，与用户进行交互
Presenter：中间者，绑定Model层和View层，是Model与View之间的桥梁

MVP的模型关系图：


MVP设计执行的基本流程：

首先视图接受用户输入请求，
然后将请求传递给Presenter，
Presenter再调用某个模型来处理用户的请求，
模型中修改数据后传递更新数据到Presenter中，
Presenter再将处理后的结果交给视图进行格式化输出给用户

MVP优点：
- 模型与视图完全分离，我们可以修改视图而不影响模型
- 可以更高效地使用模型，因为所有的交互都发生在一个地方——Presenter内部
- 我们可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑。这个特性非常的有用，因为视图的变化总是比模型的变化频繁
- 如果我们把逻辑放在Presenter中，那么我们就可以脱离用户接口来测试这些逻辑（单元测试）

MVP缺点：
- 由于对视图的渲染放在了Presenter中，
- 所以视图和Presenter的交互会过于频繁。
- 还有一点需要明白，如果Presenter过多地渲染了视图，
- 往往会使得它与特定的视图的联系过于紧密。一旦视图需要变更，那么Presenter也需要变更了

MVP内存泄漏问题
Presenter在Activity的onDestroy方法回调时执行资源释放操作，
或者在Presenter引用View对象时使用更加容易回收的软引用，弱应用。

Presenter持有了Activity或者Fragment的强引用，如果在请求结束之前Activity或者Fragment被销毁了，
那么由于网络请求还没有返回，导致Presenter一直持有Activity或者Fragment的对象，使得Activity或者Fragment对象无法回收，此时就发生了内存泄露

MVP解决内存泄漏解决
- 通过RxLifecycle，绑定Activity/Fragment生命周期
- Presenter对这个View持有弱引用。通常情况下这个View类型应该是实现了某个特定接口的Activity或者Fragment等类型。
