package com.study.designPatternsDemo.mediator;

import com.study.designPatternsDemo.mediator.mediator.*;

/**
 * @author yp
 * @data 2019/4/4 18:20
 */
public class MainTest {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Alarm alarm = new Alarm(mediator, "alarm");
        CoffeeMachine coffeeMachine = new CoffeeMachine(mediator, "coffeeMachine");
        Curtains curtains = new Curtains(mediator, "curtains");
        TV tv = new TV(mediator, "tv");
        alarm.sendMessage(0);
        coffeeMachine.finishCoffee();
        alarm.sendMessage(1);
    }
}
