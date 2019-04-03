package com.study.designPatterns;

/**
 * Created by Administrator on 2019/4/3.
 */
public interface Fly {
    default void  canFly(){
        System.out.println("我可以飞");
    }
}
