package com.huangjin.testthread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by huang on 2018-11-14.
 */
public class CountDownLatchTest {

    private static final int THREAD_POOL_CORE_SIZE = 5;// 线程池最少线程数
    private static final int THREAD_POOL_MAX_SIZE = 20;// 最大线程数
    private static final int THREAD_MAX_THREAD_WAIT = 10000;// 最大线程等待数
    private static final int THREAD_POOL_WAIT_SECONDS = 5 * 60;// 最长等待时间

    private ExecutorService exec = new ThreadPoolExecutor(THREAD_POOL_CORE_SIZE, THREAD_POOL_MAX_SIZE, THREAD_POOL_WAIT_SECONDS, TimeUnit.SECONDS, new ArrayBlockingQueue<>(THREAD_MAX_THREAD_WAIT), new ThreadPoolExecutor.AbortPolicy());


    @Test
    public void test() {
        try {
            CountDownLatch latch = new CountDownLatch(10);
            for (int threadId = 1; threadId <= 10; threadId++) {
                this.mession(latch, threadId);
            }
            latch.await();
            System.out.println("结束");
        } catch (InterruptedException e) {

        }
    }


    public void mession(CountDownLatch latch, Integer threadId) {
        exec.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(threadId*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadId);
                latch.countDown();
            }
        });
    }
}
