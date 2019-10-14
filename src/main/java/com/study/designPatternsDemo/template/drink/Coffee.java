package com.study.designPatternsDemo.template.drink;

/**
 * @author yp
 * @data 2019/3/18 17:11
 */
public class Coffee extends HotDrink{
    @Override
    public void brew(){
        System.out.println("Brewing Coffee");
    }
    @Override
    public void addCondiments(){
        System.out.println("Adding sugar and milk");
    }
}
