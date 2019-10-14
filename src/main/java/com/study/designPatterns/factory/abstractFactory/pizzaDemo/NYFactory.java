package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public class NYFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new NYCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new NYPepperPizza();
        }else {
            System.out.println("没有该类型的pizza品种");
        }
        return pizza;
    }
}
