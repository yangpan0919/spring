package com.study.designPatternsDemo.factory.pizzastore;

import com.study.designPatternsDemo.factory.pizzastore.absfactory.NYFactory;

/**
 * @author yp
 * @data 2019/3/11 21:57
 */
public class PizzaStore {
    public static void main(String[] args) {
        OrderPizza orderPizza;
        orderPizza = new OrderPizza(new NYFactory());
    }
}
