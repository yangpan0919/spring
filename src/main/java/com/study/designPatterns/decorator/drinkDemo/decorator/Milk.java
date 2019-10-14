package com.study.designPatterns.decorator.drinkDemo.decorator;

import com.study.designPatterns.decorator.drinkDemo.Drink;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        super.setDescription("Milk...");
        super.setPrice(2.0f);
    }
}
