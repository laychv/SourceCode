package com.lay.open_eventbus_annotation;

public enum ThreadMode {
    /**
     * 订阅发布在同一线程, 避免了线程切换, 默认模式
     */
    POSTING,
    /**
     * 主线程中被调用, 切勿耗时操作
     */
    MAIN,
    /**
     * 用于网络访问等耗时操作, 事件总线已完成的异步订阅通知线程
     */
    ASYNC
}
