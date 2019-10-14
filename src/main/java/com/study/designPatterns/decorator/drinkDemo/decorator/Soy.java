package com.study.designPatterns.decorator.drinkDemo.decorator;

import com.study.designPatterns.decorator.drinkDemo.Drink;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        super.setDescription("Soy...");
        super.setPrice(4.0f);
    }
}
