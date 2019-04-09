package com.study.designPatterns.command;

//被调用类：
public class Receiver {
	public void action(){
		System.out.println("command received!");
	}
}