package com.lay.android_handler;

public class Message {

    public Message next;
    public int what;
    public Object obj;
    public Handler target;

    public Message() {
    }

    public Message(Object obj) {
        this.obj = obj;
    }

    public static Message obtain() {
        return new Message();
    }

    @Override
    public String toString() {
        return "Message{" +
                "obj=" + obj +
                '}';
    }
}
