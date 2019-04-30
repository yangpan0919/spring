package com.study.designPatternsDemo.template;

import com.study.designPatternsDemo.template.drink.Coffee;
import com.study.designPatternsDemo.template.drink.HotDrink;
import com.study.designPatternsDemo.template.drink.TeaWithHook;


/**
 * @author yp
 * @data 2019/3/18 17:16
 */
public class MainTest {
    public static void main(String[] args) {
        HotDrink coffee = new Coffee();
        TeaWithHook tea = new TeaWithHook();
        coffee.prepareRecipe();
        tea.prepareRecipe();
    }
}
