package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * 抽象工厂   demo 测试
 * Created by Administrator on 2019/4/8.
 */

public class Test {
    public static  void main(String[] args){
        OrderPizza orderPizza = new OrderPizza(new NYFactory());

    }
}
