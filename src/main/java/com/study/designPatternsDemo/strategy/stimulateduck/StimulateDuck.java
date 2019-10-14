package com.study.designPatternsDemo.strategy.stimulateduck;

import com.study.designPatternsDemo.strategy.stimulateduck.duck.AbstractDuck;
import com.study.designPatternsDemo.strategy.stimulateduck.duck.GreenHeadAbstractDuck;
import com.study.designPatternsDemo.strategy.stimulateduck.duck.RedHeadAbstractDuck;
import com.study.designPatternsDemo.strategy.stimulateduck.flybehavior.NoFlyBehavior;
import com.study.designPatternsDemo.strategy.stimulateduck.quackbehavior.NoQuackBehavior;

/**
 * @author yp
 * @data 2019/3/7 19:28
 */
public class StimulateDuck {
    public static void main(String[] args) {
        AbstractDuck greenHeadDuck = new GreenHeadAbstractDuck();
        AbstractDuck redHeadDuck = new RedHeadAbstractDuck();

        //**GreenHead**
        //--GoodFly--
        //__GaGa__
        //~~im swim~~
        greenHeadDuck.display();
        greenHeadDuck.fly();
        greenHeadDuck.quack();
        greenHeadDuck.swim();

        //**RedHead**
        //--BadFly--
        //__GeGe__
        //~~im swim~~
        redHeadDuck.display();
        redHeadDuck.fly();
        redHeadDuck.quack();
        redHeadDuck.swim();
        //**RedHead**
        //--NoFly--
        //__NoQuack__
        redHeadDuck.display();
        redHeadDuck.setFlyBehavior(new NoFlyBehavior());
        redHeadDuck.fly();
        redHeadDuck.setQuackBehavior(new NoQuackBehavior());
        redHeadDuck.quack();
    }
}
