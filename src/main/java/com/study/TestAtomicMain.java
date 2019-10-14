package com.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent.atomic包下提供了一些原子操作类，即对基本数据类型进行了封装，保证这些操作是原子性操作
 * atomic是利用CAS来实现原子性操作的（Compare And Swap），CAS实际上是利用处理器提供的CMPXCHG指令实现的，
 * 而处理器执行CMPXCHG指令是一个原子性操作
 *
 * @author Jack
 * @create 2018-05-04 12:12
 **/
public class TestAtomicMain {

    private static final int THREAD_COUNT = 10;

    private static final int FOR_COUNT = 1000;

    private static final int THREAD_ACTIVE_COUNT = 2;

    public AtomicInteger inc = new AtomicInteger();
//   public volatile int inc =0;
    public void increase() {
        inc.getAndIncrement();   //保证原子性的操作
//        inc++;   //原子性无法保证
    }



    public static void main(String[] args) {

        final TestAtomicMain test = new TestAtomicMain();
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        ThreadFactory namedThreadFactory = threadFactoryBuilder.setNameFormat("ThreadFactory-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT * 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < THREAD_COUNT; i++) {
            singleThreadPool.execute(() -> {
                for (int j = 0; j < FOR_COUNT; j++) {
                    test.increase();
                }
            });
        }
        singleThreadPool.shutdown();
        while (Thread.activeCount() > THREAD_ACTIVE_COUNT) {
//            System.out.println("其他活跃的线程数" + Thread.activeCount());
            Thread.yield();

        }
        System.out.println(test.inc);

    }
}