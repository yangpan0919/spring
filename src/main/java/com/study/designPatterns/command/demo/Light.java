package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 */
public class Light {

    String loc = "";
    public Light(String loc){
        this.loc = loc;
    }
    public void on(){
        System.out.println(loc + " On");
    }
    public void off(){
        System.out.println(loc + " Off");
    }
}
