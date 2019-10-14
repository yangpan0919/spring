package com.study.designPatterns.command;

//实现类：
public class MyCommand implements Command {
 
 
	private Receiver receiver;

	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}
 
 
	@Override
	public void exe() {
		receiver.action();
	}
}