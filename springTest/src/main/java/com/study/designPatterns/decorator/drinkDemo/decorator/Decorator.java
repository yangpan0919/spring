package com.study.designPatterns.decorator.drinkDemo.decorator;

import com.study.designPatterns.decorator.drinkDemo.Drink;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj){
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription()+"&&"+obj.getDescription();
    }
}
