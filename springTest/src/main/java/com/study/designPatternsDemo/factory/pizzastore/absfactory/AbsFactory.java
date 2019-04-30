package com.study.designPatternsDemo.factory.pizzastore.absfactory;

import com.study.designPatternsDemo.factory.pizzastore.pizza.AbstractPizza;

/**
 * @author yp
 * @data 2019/3/11 22:33
 */
public interface AbsFactory {
    /**
     * 创建披萨
     * @param orderType 披萨类型
     * @return 披萨
     */
    public AbstractPizza createPizza(String orderType);
}
