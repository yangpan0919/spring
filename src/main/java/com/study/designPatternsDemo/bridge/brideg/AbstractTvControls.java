package com.study.designPatternsDemo.bridge.brideg;

import com.study.designPatternsDemo.bridge.control.Control;

/**
 * @author yp
 * @data 2019/3/27 17:38
 */
public abstract class AbstractTvControls {
    Control control = null;
    AbstractTvControls(Control control) {
        this.control = control;
    }

    /**
     *开关
     */
    public abstract void onOff();

    /**
     * 下一个频道
     */
    public abstract void nextChannel();

    /**
     * 前一个频道
     */
    public abstract void preChannel();
}
