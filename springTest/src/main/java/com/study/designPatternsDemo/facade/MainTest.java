package com.study.designPatternsDemo.facade;

import com.study.designPatternsDemo.facade.hometheater.HomeTheaterFacade;

/**
 * @author yp
 * @data 2019/3/17 16:05
 */
public class MainTest {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
    }
}
