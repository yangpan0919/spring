package com.study.designPatterns.factory.simple;
//创建实现类
public class SmsSender implements Sender {
 
 
	@Override
	public void Send() {
		System.out.println("this is sms sender!");
	}
}