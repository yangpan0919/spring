package com.study.designPatterns.factory.abstractFactory;

//测试：
public class Test {
 
 
	public static void main(String[] args) {
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.Send();
	}
}