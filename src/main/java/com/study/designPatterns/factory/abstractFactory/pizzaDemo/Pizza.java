package com.study.designPatterns.factory.abstractFactory.pizzaDemo;

/**
 * Created by Administrator on 2019/4/8.
 */
public abstract class Pizza {
    protected  String name;
    public abstract void prepare();

    public void bake(){
        System.out.println(name + " baking;");
    }
    public void cut(){
        System.out.println(name + " cutting;");
    }
    public void box(){
        System.out.println(name + " boxing;");
    }
    public void setName( String name){
        this.name = name;
    }
}
