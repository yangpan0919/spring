package com.study.designPatternsDemo.decorator.coffeebar.coffee;

/**
 * @author yp
 * @data 2019/3/7 21:04
 */
public class Espresso extends Coffee {
    public Espresso(){
        super.setDescription("Espresso");
        super.setPrice(4.0f);
    }
}
