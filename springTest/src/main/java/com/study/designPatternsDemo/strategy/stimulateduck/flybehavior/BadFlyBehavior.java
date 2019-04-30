package com.study.designPatternsDemo.strategy.stimulateduck.flybehavior;

/**
 * @author yp
 * @data 2019/3/7 19:11
 * 飞行行为中的BadFly行为
 */
public class BadFlyBehavior implements FlyBehavior{

    public void fly() {
        System.out.println("--BadFly--");
    }
}
