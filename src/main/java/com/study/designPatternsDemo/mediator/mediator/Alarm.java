package com.study.designPatternsDemo.mediator.mediator;

/**
 * @author yp
 * @data 2019/4/4 17:57
 */
public class Alarm extends AbstractColleague {
    public Alarm(Mediator mediator, String name){
        super(mediator, name);
        mediator.register(name, this);
    }
    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }
    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }
}
