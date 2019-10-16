package com.assess15.module_open_projects.okHttp;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp中线程池的使用
 *
 * @see okhttp3.Dispatcher
 */
public class OkHttpThreadPool {

    public static void main(String[] args) {

        /**
         * 阿里巴巴 安卓开发手册 建议使用本方式
         *
         * int corePoolSize,核心线程数
         * int maximumPoolSize,线程池非核心线程数,线程池规定大小
         * long keepAliveTime,设定时间
         * TimeUnit unit,在时间范围内线程复用,超出时间范围,销毁Runnable任务
         * BlockingQueue<Runnable> workQueue,把超出的任务加入到队列中缓存起来
         */
        Executor executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("当前线程,执行耗时任务" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        /**
         * 已经封装好的线程池
         */
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(1);
        Executors.newScheduledThreadPool(1);


    }
}
