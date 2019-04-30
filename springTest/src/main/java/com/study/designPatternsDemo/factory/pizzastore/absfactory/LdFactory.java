package com.study.designPatternsDemo.factory.pizzastore.absfactory;

import com.study.designPatternsDemo.factory.pizzastore.pizza.AbstractPizza;
import com.study.designPatternsDemo.factory.pizzastore.pizza.LdCheesePizza;
import com.study.designPatternsDemo.factory.pizzastore.pizza.LdPeeperPizza;

/**
 * @author yp
 * @data 2019/3/11 22:18
 */
public class LdFactory implements AbsFactory {
    public AbstractPizza createPizza(String orderType) {
        AbstractPizza abstractPizza = null;
        if ("cheese".equals(orderType)){
            abstractPizza = new LdCheesePizza();
        }else if ("pepper".equals(orderType)){
            abstractPizza = new LdPeeperPizza();
        }
        return abstractPizza;
    }
}
