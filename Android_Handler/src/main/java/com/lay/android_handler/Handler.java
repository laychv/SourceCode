package com.lay.android_handler;

public class Handler {

    private final Looper mLooper;
    private final MessageQueue mQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    public void handleMessage(Message msg) {

    }

    public void sendMessage(Message msg) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            throw new RuntimeException("");
        }
        enqueueMessage(queue, msg);
    }

    private void enqueueMessage(MessageQueue queue, Message msg) {
        msg.target = this;
        queue.enqueueMessage(msg);
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
