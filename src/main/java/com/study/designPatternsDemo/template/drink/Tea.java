package com.study.designPatternsDemo.template.drink;

/**
 * @author yp
 * @data 2019/3/18 17:14
 */
public class Tea extends HotDrink{
    @Override
    public void brew(){
        System.out.println("Brewing tea");
    }
    @Override
    public void addCondiments(){
        System.out.println("Adding lemon");
    }
}
