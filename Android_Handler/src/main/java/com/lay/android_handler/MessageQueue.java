package com.lay.android_handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

    Message mMessages;

    BlockingQueue<Message> mBlockingDeque = new ArrayBlockingQueue<Message>(10);

    public void enqueueMessage(Message msg) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }

        try {
            mBlockingDeque.put(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Message p = mMessages;
//
//        if (p == null) {
//            mMessages = msg;
//        } else {
//            Message prev;
//            for (; ; ) {
//                prev = p;
//                p = p.next;
//                if (p == null) {
//                    break;
//                }
//            }
//            msg.next = p;
//            prev.next = msg;
//        }
    }

    public Message next() {
//        for (; ; ) {
//            Message msg = mMessages;
//
//            synchronized (this){
//
//            }
//
//            return msg;
//        }

        try {
            return mBlockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
