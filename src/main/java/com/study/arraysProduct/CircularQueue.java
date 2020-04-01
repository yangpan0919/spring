package com.study.arraysProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 环状链表
 *
 * @param <E>
 */
public class CircularQueue<E> {


    /**
     * Main lock guarding all access
     */
    final ReentrantLock lock = new ReentrantLock();


    /**
     * Condition for waiting puts
     */
    private final Condition notFull = lock.newCondition();

    /**
     * Condition for waiting takes
     */
    private final Condition notEmpty = lock.newCondition();


    transient Node kong;

    transient Node first;

    public E takeFirst() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E x;
            while ((x = unlinkFirst()) == null) {
                System.out.println("take lock...");
                notEmpty.await();
            }

            return x;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes and returns first element, or null if empty.
     */
    private E unlinkFirst() {

        Node<E> f = first;

        E item = f.item;
        if (item == null) {
            return null;
        }
        first = f.next;
        f.item = null;
        notFull.signal();
        return item;
    }


    public void putLast(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            while (!linkLast(e)) {
                System.out.println("put lock...");
                notFull.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Links node as last element, or returns false if full.
     */
    private boolean linkLast(E node) {
        Node last = this.kong;

        if (last.item == null) {

            last.item = node;

            this.kong = last.next;

            notEmpty.signal();
            return true;
        }
//        throw new IllegalStateException("Data full");
        return false;
    }

    static int temp = 20;
    //注意线程池的数量，测试的时候，数量不够，容易导致，线程池全部等待，死锁
    public static ExecutorService executorService = Executors.newFixedThreadPool(temp * 2);


    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");
        CircularQueue<Object> data = new CircularQueue();
//        new Thread(() -> {
//            ReentrantLock lock = data.lock;
//
//            lock.lock();
//            try {
//                try {
//                    System.out.println("A锁住了");
//                    data.notEmpty.await();
//                    System.out.println("A又获得锁了");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } finally {
//                lock.unlock();
//            }
//        }).start();
//
//        new Thread(() -> {
//            ReentrantLock lock = data.lock;
//
//            lock.lock();
//            try {
//
//                System.out.println("B锁住了");
//                data.notEmpty.signal();
//                System.out.println("B通知其他锁释放");
//
//            } finally {
//                lock.unlock();
//            }
//        }).start();


        new Thread(() -> {
            for (int j = 0; j < temp; j++) {
                int finalJ = j;
                executorService.execute(() -> {
                    for (int i = 0; i < 1000; i++) {
                        try {
                            data.putLast(i);
//                            System.out.println(Thread.currentThread().getName() + ":" + finalJ + "--<" + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }).start();
        for (int j = 0; j < temp; j++) {
            int finalJ = j;
            executorService.execute(() -> {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Object o = data.takeFirst();
                        System.out.println(Thread.currentThread().getName() + ":" + o + ":" + finalJ + "-->" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }


    }

    {
        //创建1000的环
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new Node<>(null));
        }
        list.get(0).prev = list.get(list.size() - 1);
        list.get(0).next = list.get(1);
        list.get(list.size() - 1).prev = list.get(list.size() - 2);
        list.get(list.size() - 1).next = list.get(0);
        for (int i = 1; i < list.size() - 1; i++) {
            Node node = list.get(i);
            node.next = list.get(i + 1);
            node.prev = list.get(i - 1);
        }
        kong = list.get(0);
        first = list.get(0);
    }


    /**
     * 争取形成环状结构，的闭环，循环使用
     *
     * @param <E>
     */
    static final class Node<E> {
        E item;

        Node<E> prev;

        Node<E> next;

        Node(E x) {
            item = x;
        }
    }


}
