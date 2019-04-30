package com.study.designPatternsDemo.strategy.stimulateduck.quackbehavior;

/**
 * @author yp
 * @data 2019/3/7 19:15
 * 鸭子叫行为中的GaGa
 */
public class GaGaQuackBehavior implements QuackBehavior {
    public void quack() {
        System.out.println("__GaGa__");
    }
}
