package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 */
public class StereoOnCommand implements Command {

    private Stereo  stereo;

    public StereoOnCommand (Stereo stereo){
        this.stereo = stereo;
    }
    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
