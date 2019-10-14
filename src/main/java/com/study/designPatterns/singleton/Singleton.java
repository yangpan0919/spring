package com.study.designPatterns.singleton;

/**
 * Created by Administrator on 2019/4/8.
 * 单例模式
 */
public class Singleton {

    private Singleton(){

    }

    private volatile static Singleton singleton = null;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
