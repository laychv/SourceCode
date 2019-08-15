# Dagger2.Android

一个类不应该知道自己是如何被注入的

```groovy
annotationProcessor 'com.google.dagger:dagger-compiler:2.17' // 注意如果使用2.21报错
implementation 'com.google.dagger:dagger-android:2.17'
implementation 'com.google.dagger:dagger-android-support:2.17'
annotationProcessor 'com.google.dagger:dagger-android-processor:2.17'
```