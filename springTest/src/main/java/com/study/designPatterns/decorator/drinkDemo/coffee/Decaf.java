package com.study.designPatterns.decorator.drinkDemo.coffee;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Decaf extends  Coffee{

    public Decaf(){
        super.setDescription("Decaf...");
        super.setPrice(3.0f);
    }
}
