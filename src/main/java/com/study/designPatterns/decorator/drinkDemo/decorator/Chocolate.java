package com.study.designPatterns.decorator.drinkDemo.decorator;

import com.study.designPatterns.decorator.drinkDemo.Drink;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Chocolate extends  Decorator{

    public Chocolate(Drink obj) {
        super(obj);
        super.setDescription("Chocolate...");
        super.setPrice(3.0f);
    }

}
