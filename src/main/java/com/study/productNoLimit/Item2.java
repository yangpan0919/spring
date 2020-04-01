package com.study.productNoLimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item2 extends Item {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static ExecutorService executorService1 = Executors.newFixedThreadPool(1);

    public static volatile int count = 0;

    public Item2(int index) {
        super(index);
    }


    @Override
    public void poll(LinkedBlockingDeque queue) {
        System.out.println("获取到的内容下标" + index);
        System.out.println(System.currentTimeMillis() + "开始扩容队列:" + ++count + "队列数量为：" + queue.size());
        executorService1.execute(() -> {
            for (int i = 1; i < Constant.count; i++) {
                queue.add(new Item(i));
            }
            queue.add(new Item2(Constant.count));
        });
    }
}
