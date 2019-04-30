package com.study.designPatternsDemo.bridge.brideg;

import com.study.designPatternsDemo.bridge.control.Control;

/**
 * @author yp
 * @data 2019/3/27 17:36
 */
public class TvControl extends AbstractTvControls {
    private int ch = 0;
    private boolean isOn = false;

    public TvControl(Control control){
        super(control);
    }
    @Override
    public void onOff(){
        if (isOn){
            isOn = false;
            control.off();
        }else {
            isOn = true;
            control.on();
        }
    }

    @Override
    public void nextChannel() {
        ch++;
        control.setChannel(ch);
    }

    @Override
    public void preChannel() {
        ch--;
        if (ch < 0){
            ch = 200;
        }
        control.setChannel(ch);
    }
}
