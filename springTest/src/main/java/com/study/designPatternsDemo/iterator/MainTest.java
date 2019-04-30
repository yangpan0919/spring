package com.study.designPatternsDemo.iterator;

import com.study.designPatternsDemo.iterator.iterator.CafeMenu;
import com.study.designPatternsDemo.iterator.iterator.CakeHouseMenu;
import com.study.designPatternsDemo.iterator.iterator.DinerMenu;
import com.study.designPatternsDemo.iterator.iterator.Waitress;

/**
 * @author yp
 * @data 2019/3/19 20:29
 */
public class MainTest {
    public static void main(String[] args) {
        Waitress waitress = new Waitress();
        CakeHouseMenu cakeHouseMenu = new CakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        CafeMenu cafeMenu = new CafeMenu();
        waitress.addIterator(cakeHouseMenu.getIterator());
        waitress.addIterator(dinerMenu.getIterator());
        waitress.addIterator(cafeMenu.getIterator());
        waitress.printMenu();
    }
}
