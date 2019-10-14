package com.study.designPatterns.decorator.drinkDemo;

import com.study.designPatterns.decorator.drinkDemo.coffee.LongBlack;
import com.study.designPatterns.decorator.drinkDemo.decorator.Chocolate;
import com.study.designPatterns.decorator.drinkDemo.decorator.Milk;

/**
 * Created by Administrator on 2019/4/8.
 */
public class Test {

    public static void main(String[] args) {

        Drink drink = new LongBlack();
        drink = new Milk(drink);
        drink = new Milk(drink);
        drink = new Chocolate(drink);


        System.out.println(drink.getDescription());
        System.out.println(drink.cost());


    }
}
