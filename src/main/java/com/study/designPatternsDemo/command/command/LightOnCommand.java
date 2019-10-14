package com.study.designPatternsDemo.command.command;

import com.study.designPatternsDemo.command.device.Light;

/**
 * @author yp
 * @data 2019/3/13 19:40
 */
public class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }
    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
