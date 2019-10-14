package com.study.designPatterns.decorator.drinkDemo.coffee;

import com.study.designPatterns.decorator.drinkDemo.Drink;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
