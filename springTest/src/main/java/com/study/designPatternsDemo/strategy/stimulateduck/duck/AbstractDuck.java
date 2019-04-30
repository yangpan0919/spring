package com.study.designPatternsDemo.strategy.stimulateduck.duck;

import com.study.designPatternsDemo.strategy.stimulateduck.flybehavior.FlyBehavior;
import com.study.designPatternsDemo.strategy.stimulateduck.quackbehavior.QuackBehavior;

/**
 * @author yp
 * @data 2019/3/7 19:16
 * 鸭子的抽象类，定义鸭子的基本功能
 */
public abstract class AbstractDuck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    AbstractDuck() {
    }

    public void fly(){
        flyBehavior.fly();
    }

    public void quack(){
        quackBehavior.quack();
    }

    /**
     * 展示是什么鸭子
     */
    public abstract void display();

    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior = flyBehavior;
    }

    public void swim(){
        System.out.println("~~im swim~~");
    }
}
