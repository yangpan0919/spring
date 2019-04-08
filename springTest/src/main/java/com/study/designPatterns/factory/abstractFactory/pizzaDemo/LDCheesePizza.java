package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public class LDCheesePizza extends  Pizza {

    public  LDCheesePizza(){
        setName("LDCheesePizza");
    }

    @Override
    public void prepare() {
        System.out.println("LDCheesePizza  preparing...");
    }
}
