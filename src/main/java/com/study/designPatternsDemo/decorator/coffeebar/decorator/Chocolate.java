package com.study.designPatternsDemo.decorator.coffeebar.decorator;

import com.study.designPatternsDemo.decorator.coffeebar.AbstractDrink;

/**
 * @author yp
 * @data 2019/3/7 21:11
 */
public class Chocolate extends Decorator {

    public Chocolate(AbstractDrink abstractDrink) {
        super(abstractDrink);
        super.setDescription("Chocolate");
        super.setPrice(3.0f);
    }
}
