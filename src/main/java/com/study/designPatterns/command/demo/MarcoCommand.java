package com.study.designPatterns.command.demo;

/**
 * Created by Administrator on 2019/4/9.
 * 宏命令/组合命令
 */
public class MarcoCommand implements Command {

    private Command[] commands;

    public MarcoCommand(Command[] commands){
        this.commands = commands;
    }
    @Override
    public void execute() {
        for(int i=0,len = commands.length;i<len;i++){
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        for(int i=0,len = commands.length;i<len;i++){
            commands[i].undo();
        }
    }
}
