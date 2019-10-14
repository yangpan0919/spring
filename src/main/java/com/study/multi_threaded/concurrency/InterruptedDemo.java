package com.study.multi_threaded.concurrency;

public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().interrupt();

        System.out.println("线程已经被中断了，但是仍然可以执行");


        Thread t = new Thread(()->{
            Thread.currentThread().interrupt();
            System.out.println("wode ");
        });

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            System.out.println("线程中断,无法sleep");
            Thread.currentThread().interrupt();
        }

        Thread.sleep(1000L);       //InterruptedException     异常抛出后    interrupt状态   会被重置
        //所以说interrupt只是一个状态标志

    }
}
