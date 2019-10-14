package com.study.designPatternsDemo.command.command;

import com.study.designPatternsDemo.command.device.Stereo;

/**
 * @author yp
 * @data 2019/3/13 19:42
 */
public class StereoOnCommand implements Command {
    private Stereo stereo;
    public StereoOnCommand(Stereo stereo){
        this.stereo = stereo;
    }
    public void execute() {
        stereo.On();
        stereo.SetCd();
    }

    public void undo() {
        stereo.Off();
    }
}
