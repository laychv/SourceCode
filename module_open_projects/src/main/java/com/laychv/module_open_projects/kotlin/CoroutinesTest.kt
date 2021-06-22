package com.laychv.module_open_projects.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 本质上，协程是轻量级的线程。
 */
fun main() {
    // 在后台启动一个新的协程并继续
    GlobalScope.launch {
        delay(1000L)// 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("coroutines") // 在延迟后打印输出
    }
    println("hello")// 协程已在等待时主线程还在继续
    Thread.sleep(2000L)// 阻塞主线程 2 秒钟来保证 JVM 存活
}