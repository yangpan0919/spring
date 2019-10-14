package com.study.designPatterns.decorator.drinkDemo.coffee;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Espresso extends Coffee {

    public Espresso(){
        super.setDescription("Espresso...");
        super.setPrice(4.0f);
    }
}

