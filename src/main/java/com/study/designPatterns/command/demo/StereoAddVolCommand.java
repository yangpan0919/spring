package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 */
public class StereoAddVolCommand implements Command {

    private Stereo  stereo;

    public StereoAddVolCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        int vol = stereo.getVol();
        if(vol<11){
            stereo.setVol(++vol);
        }
    }

    @Override
    public void undo() {
        int vol = stereo.getVol();
        if(vol>0){
            stereo.setVol(--vol);
        }
    }
}
