package com.study.designPatterns.factory.simple;
//一般工厂模式
public class FactoryTest {
 
 
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.Send();
	}
}