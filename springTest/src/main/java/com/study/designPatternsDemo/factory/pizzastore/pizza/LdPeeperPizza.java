package com.study.designPatternsDemo.factory.pizzastore.pizza;

/**
 * @author yp
 * @data 2019/3/11 21:49
 */
public class LdPeeperPizza extends AbstractPizza {
    @Override
    public void prepare() {
        super.setName("LdPeeperPizza");
        System.out.println(name + " preparing;");
    }
}
