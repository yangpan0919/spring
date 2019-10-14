package com.study.multi_threaded.concurrency;

/**
 * Created by Administrator on 2019/4/10.
 */
public class SynchronizedDemo {
    public static void main(String[] args) {

    }
    private static class Data {

        private volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
