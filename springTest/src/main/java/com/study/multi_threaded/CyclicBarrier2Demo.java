package com.study.multi_threaded;

import java.util.concurrent.*;

public class CyclicBarrier2Demo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cyclicBarrier.await()); //线程阻塞住
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cyclicBarrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                method3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cyclicBarrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                method4();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cyclicBarrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                method5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(cyclicBarrier.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        System.out.println("CyclicBarrier 正在等待数量： "+cyclicBarrier.getNumberWaiting());

//        try {
//            System.out.println(cyclicBarrier.await());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
        System.out.println("CyclicBarrier 正在等待数量： "+cyclicBarrier.getNumberWaiting());

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
