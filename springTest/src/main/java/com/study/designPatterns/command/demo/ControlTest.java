package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 */
public class ControlTest {
    public static void main(String [] args){
        CommandModeControl control = new CommandModeControl();
        MarcoCommand onMarco,offMarco;

        Light bedroomLight = new Light("BedRoom");
        Light kitchLight = new Light("Kitch");
        Stereo stereo = new Stereo();

        LightOnCommand bedroomLightOn = new LightOnCommand(bedroomLight);
        LightOffCommand bedroomLightOff = new LightOffCommand(bedroomLight);
        LightOnCommand kitchLightOn = new LightOnCommand(kitchLight);
        LightOffCommand kitchLightOff = new LightOffCommand(kitchLight);

        Command[] onCommands = {bedroomLightOn,kitchLightOn};
        Command[] offCommands = {bedroomLightOff,kitchLightOff};
        onMarco = new MarcoCommand(onCommands);
        offMarco = new MarcoCommand(offCommands);


        StereoOnCommand stereoOn = new StereoOnCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);
        StereoAddVolCommand stereoAddVol = new StereoAddVolCommand(stereo);
        StereoSubVolCommand stereoSubVol = new StereoSubVolCommand(stereo);

        control.setCommand(0,bedroomLightOn,bedroomLightOff);
        control.setCommand(1,kitchLightOn,kitchLightOff);
        control.setCommand(2,stereoOn,stereoOff);
        control.setCommand(3,stereoAddVol,stereoSubVol);
        control.setCommand(4,onMarco,offMarco);

        control.onButton(0);
        control.undoButton();
//        control.offButton(0);
        control.onButton(1);
        control.offButton(1);
        control.onButton(2);
        control.onButton(3);
        control.offButton(3);
        control.undoButton();

        control.offButton(2);

        control.onButton(4);
        control.offButton(4);




    }
}
