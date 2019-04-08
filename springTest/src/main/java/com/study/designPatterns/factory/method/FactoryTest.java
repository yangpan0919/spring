package com.study.designPatterns.factory.method;

import com.study.designPatterns.factory.simple.Sender;

//工厂方法模式   测试：
public class FactoryTest {
 
 
	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produceMail();
		sender.Send();
	}
}