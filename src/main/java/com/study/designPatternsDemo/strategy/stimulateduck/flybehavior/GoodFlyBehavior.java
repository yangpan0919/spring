package com.study.designPatternsDemo.strategy.stimulateduck.flybehavior;

/**
 * @author yp
 * @data 2019/3/7 19:12
 * 飞行行为中的GoodFly
 */
public class GoodFlyBehavior implements FlyBehavior {
    public void fly() {
        System.out.println("--GoodFly--");
    }
}
