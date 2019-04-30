package com.study.designPatternsDemo.decorator.coffeebar.decorator;

import com.study.designPatternsDemo.decorator.coffeebar.AbstractDrink;

/**
 * @author yp
 * @data 2019/3/7 21:07
 */
public class Decorator extends AbstractDrink {

    private AbstractDrink abstractDrink;

    Decorator(AbstractDrink abstractDrink) {
        this.abstractDrink = abstractDrink;
    }

    @Override
    public float cost() {
        return super.getPrice() + abstractDrink.cost();
    }

    @Override
    public String getDescription() {
        return super.description + "-" +super.getPrice() + " && " + abstractDrink.getDescription();
    }
}
