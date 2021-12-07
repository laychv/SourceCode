package com.lay.android_handler;

import org.junit.Test;

public class ActivityThread {

    @Test
    public void main() {
        // 全局唯一
        Looper.prepare();

        // 主线程 接收
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };

        // 子线程 发送
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.obj = "发送的对象";
                handler.sendMessage(msg);
            }
        });
        thread.start();

        // 开启轮训,发送消息
        Looper.loop();
    }
}
