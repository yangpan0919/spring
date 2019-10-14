package com.study.designPatternsDemo.facade.hometheater;

/**
 * @author yp
 * @data 2019/3/17 15:48
 */
class Screen {
    private static Screen instance = null;
    private Screen(){

    }
    static Screen getInstance(){
        if (instance == null){
            instance = new Screen();
        }
        return instance;
    }
    void up(){
        System.out.println("Screen up");
    }
    void down(){
        System.out.println("Screen down");
    }
}
