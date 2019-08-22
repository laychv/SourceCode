# Kotlin中使用AspjectJ无效问题
https://blog.csdn.net/EthanCo/article/details/87938487
https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx

注意：
模块化/组件化中
如果跳转/跨模块无法调取，检查模块依赖关系

# AspectJ无效

配置
```groovy
apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
apply plugin: 'kotlin-kapt'

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.aspectj:aspectjtools:1.8.9'
        classpath 'org.aspectj:aspectjweaver:1.8.9'
    }
}

android {
    compileSdkVersion 28

    defaultConfig {
        minSdkVersion 21
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.1.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0'
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'org.aspectj:aspectjrt:1.8.13'
    implementation "org.jetbrains.anko:anko-appcompat-v7-commons:0.10.5"
}
repositories {
    mavenCentral()
}


// 版本界限：As-3.0.1 + gradle4.4-all （需要配置r17的NDK环境）
// 或者：As-3.2.1 + gradle4.6-all （正常使用，无警告）
// AS-3.4.0 + gradle5.1.1-all 提示警告
//API 'variant.getJavaCompile()' is obsolete and has been replaced with 'variant.getJavaCompileProvider()'.
//It will be removed at the end of 2019.

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main

// * What went wrong:
//A problem occurred evaluating project ':Arch'.
//> Could not get unknown property 'applicationVariants' for object of type com.android.build.gradle.LibraryExtension.
// applicationVariants 只包含在 apply plugin: 'com.android.application' 插件中

final def log = project.logger
//final def variants = project.android.applicationVariants
final def variants = project.android.libraryVariants

variants.all { variant ->
    if (!variant.buildType.isDebuggable()) {
        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
        return
    }

    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        log.debug "ajc args: " + Arrays.toString(args)

        MessageHandler handler = new MessageHandler(true)
        new Main().run(args, handler)
        for (IMessage message : handler.getMessages(null, true)) {
            switch (message.getKind()) {
                case IMessage.ABORT:
                case IMessage.ERROR:
                case IMessage.FAIL:
                    log.error message.message, message.thrown
                    break
                case IMessage.WARNING:
                    log.warn message.message, message.thrown
                    break
                case IMessage.INFO:
                    log.info message.message, message.thrown
                    break
                case IMessage.DEBUG:
                    log.debug message.message, message.thrown
                    break
            }
        }
    }
}


```

注意自定义注解Java和Kotlin区别
```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickLimit {
    int value() default 500;
}
```

```kotlin
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SingleClick

```

project下添加 必须添加
```groovy
classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4'
```

app/module下添加 留一个
```
apply plugin: 'com.hujiang.android-aspectjx'

```

或者添加 留一个
```
dependencies {
    api 'org.aspectj:aspectjrt:1.8.13'
}

```

