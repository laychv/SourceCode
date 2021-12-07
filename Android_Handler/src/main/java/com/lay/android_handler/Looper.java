package com.lay.android_handler;

public class Looper {

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    public MessageQueue mQueue;

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        // 主线程中唯一一个对象
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("");
        }

        sThreadLocal.set(new Looper());
    }

    public static void loop() {
        Looper me = myLooper();

        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }

        final MessageQueue queue = me.mQueue;

        for (; ; ) {
            final Message msg = queue.next();
            if (msg == null) {
                return;
            }

            msg.target.dispatchMessage(msg);
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }
}
