package com.study.designPatternsDemo.decorator.coffeebar.decorator;

import com.study.designPatternsDemo.decorator.coffeebar.AbstractDrink;

/**
 * @author yp
 * @data 2019/3/7 21:11
 */
public class Milk extends Decorator {

    public Milk(AbstractDrink abstractDrink) {
        super(abstractDrink);
        super.setDescription("Milk");
        super.setPrice(2.0f);
    }
}
