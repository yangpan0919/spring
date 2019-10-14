package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 * 音响
 */
public class Stereo {
    static int volume = 0;

    public void on(){
        System.out.println("Stereo On");
    }
    public void off(){
        System.out.println("Stereo Off");
    }
    public void setCd(){
        System.out.println("Stereo setCd");
    }
    public void setVol(int vol){
        volume = vol;
        System.out.println("Stereo volume = "+vol);
    }

    public int getVol(){
        return  volume;
    }
    public void start(){
        System.out.println("Stereo Start");
    }
}
