package com.study.multi_threaded;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService.submit(()->{
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService.submit(()->{
            try {
                method3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService.submit(()->{
            try {
                method4();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });
        executorService.submit(()->{
            try {
                method5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        System.out.println("CountDownLatch 剩余数量： "+latch.getCount());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CountDownLatch 剩余数量： "+latch.getCount());

        executorService.shutdown();
    }


    private static void method1() throws InterruptedException {
        System.out.println("方法一执行开始");
        Thread.sleep(5000L);
        System.out.println("方法一执行结束");
    }
    private static void method2() throws InterruptedException {
        System.out.println("方法二执行开始");
        Thread.sleep(6000L);
        System.out.println("方法二执行结束");
    }
    private static void method3() throws InterruptedException {
        System.out.println("方法三执行开始");
        Thread.sleep(7000L);
        System.out.println("方法三执行结束");
    }
    private static void method4() throws InterruptedException {
        System.out.println("方法四执行开始");
        Thread.sleep(8000L);
        System.out.println("方法四执行结束");
    }
    private static void method5() throws InterruptedException {
        System.out.println("方法五执行开始");
        Thread.sleep(9000L);
        System.out.println("方法五执行结束");
    }

}
