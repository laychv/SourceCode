package com.lay.android_handler;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ThreadLocalTest {

    @Test
    public void thread_local_test() {
        final ThreadLocal<String> local = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "默认值: 00";
            }
        };

        System.out.println("主线程ThreadLocal: " + local.get());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取的还是默认值
                String s = local.get();
                System.out.println(s);
                // 重新写入
                local.set("修改后的值: 11");
                System.out.println("1号主线程ThreadLocal: " + local.get());
                // 释放
                local.remove();
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取的还是默认值
                String s = local.get();
                System.out.println(s);
                // 重新写入
                local.set("修改后的值: 22");
                System.out.println("2号主线程ThreadLocal: " + local.get());
                // 释放
                local.remove();
            }
        });
        thread1.start();
    }

}