package com.study.designPatternsDemo.decorator.coffeebar.coffee;

import com.study.designPatternsDemo.decorator.coffeebar.AbstractDrink;

/**
 * @author yp
 * @data 2019/3/7 21:04
 */
public class Coffee extends AbstractDrink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
