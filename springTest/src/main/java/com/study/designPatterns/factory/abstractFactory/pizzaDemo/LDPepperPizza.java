package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public class LDPepperPizza extends  Pizza {

    public  LDPepperPizza(){
        setName("LDPepperPizza");
    }

    @Override
    public void prepare() {
        System.out.println("LDPepperPizza  preparing...");
    }
}
