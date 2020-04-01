package com.study.productNoLimit;

public class Item {


    public Object object;

    public int index;

    public Item(int index) {
        this.index = index;
    }

    public void poll(LinkedBlockingDeque queue) {
//        System.out.println("获取到的内容下标" + index);
    }

    public void run() {
        new Item(123);
        new Item(123);
        new Item(123);
        new Item(123);
        new Item(123);

    }


}
