package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2019/4/8.
 */
public class OrderPizza {
    AbsFactory absFactory;
    public OrderPizza(AbsFactory absFactory){
        setFactory(absFactory);
    }

    public void setFactory(AbsFactory factory){
        Pizza pizza = null;
        String orderType;
        this.absFactory = factory;
        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);
            if(pizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }
        }while (true);
    }

    private String getType(){
        try
        {
            BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("input pizza type:");
            String str = strIn.readLine();
            return  str;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
