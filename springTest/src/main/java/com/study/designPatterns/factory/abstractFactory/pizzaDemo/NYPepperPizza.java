package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public class NYPepperPizza extends Pizza {

    public  NYPepperPizza(){
        setName("NYPepperPizza");
    }

    @Override
    public void prepare() {
        System.out.println("NYPepperPizza  preparing...");
    }
}
