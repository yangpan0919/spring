package com.study.multi_threaded;

import java.util.*;
import java.util.concurrent.*;

public class Single {
    private volatile  int count = 0;
    private static volatile  int temp = 0;
    private static  int temp1 = 1;
    private static Set<Integer> set = new HashSet<>();
    private static List<Integer> list = new ArrayList<>(1000);   //多线程添加都会丢失数据，什么鬼
    private static ArrayBlockingQueue<Integer> list1 = new ArrayBlockingQueue<>(1000);

    private static Single single = new Single();

    public static Single getInstance(){
        return  single;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(500);
        for(int j=0;j<100;j++) {
            CountDownLatch latch = new CountDownLatch(1000);
            for (int i = 0; i < 1000; i++) {
//                Future<Integer> submit = executorService.submit(() -> {
//                    list.add(1);
//                    return 1;
//                });
                new Thread(() -> {
                    list.add(1);
                    list1.add(++temp);
                    latch.countDown();
//                    list.add(++temp);
//                synchronized (Single.class){
//
//                    if(temp1 ==set.size()){
//
//                    }else{
//                        System.out.println("数据重复了"+set.size());
//                        temp1--;
//                    }
//                    temp1++;
//                }
//                try {
//                    Single.getInstance().countManage();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                }).start();
            }
//            Thread.sleep(4000L);
//        list.sort((x,y)->{
//            return x-y;
//        });
            latch.await();
            System.out.println(list1.size());
            System.out.println("--------------------------"+list.size()+"--------------------------");

            Integer[] objects = new Integer[1000];
            list1.toArray(objects);
            List<Integer> integers = Arrays.asList(objects);
            Collections.sort( integers);
            boolean flag = true;
            for (int i = 1; i <= 1000; i++) {
                if (integers.get(i - 1) == i) {

                } else {
                    if(flag){
                        System.out.println(i + "jieshule");
                    }
                    flag = false;


                }
            }
            System.out.println(integers);
            temp = 0;
            list = new ArrayList<>(1000);
            list1 =new ArrayBlockingQueue<>(1000);
        }

    }

    public synchronized void countManage() throws InterruptedException {

        System.out.println(count);
        count++;
//        Thread.sleep(1L);

    }
}
