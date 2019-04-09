package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 */
public interface Control {
    public void onButton(int slot);
    public void offButton(int slot);

    public void undoButton();//回退
}
