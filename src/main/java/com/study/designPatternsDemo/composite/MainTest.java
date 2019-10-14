package com.study.designPatternsDemo.composite;

import com.study.designPatternsDemo.composite.composemode.CakeHouseMenu;
import com.study.designPatternsDemo.composite.composemode.DinerMenu;
import com.study.designPatternsDemo.composite.composemode.Waitress;

/**
 * @author yp
 * @data 2019/3/19 20:29
 */
public class MainTest {
    public static void main(String[] args) {
        Waitress waitress = new Waitress();
        CakeHouseMenu cakeHouseMenu = new CakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        waitress.addComponent(cakeHouseMenu);
        waitress.addComponent(dinerMenu);
        waitress.printVegetableMenu();
    }
}
