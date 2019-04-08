package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public class NYCheesePizza extends  Pizza {

    public  NYCheesePizza(){
        setName("NYCheesePizza");
    }

    @Override
    public void prepare() {
        System.out.println("NYCheesePizza  preparing...");
    }
}
