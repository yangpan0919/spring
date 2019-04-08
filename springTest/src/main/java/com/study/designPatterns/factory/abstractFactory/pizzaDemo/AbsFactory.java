package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public interface AbsFactory {
    public Pizza createPizza(String orderType);
}
