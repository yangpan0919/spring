package com.study.designPatterns.factory.staticFactory;

import com.study.designPatterns.factory.simple.Sender;

//测试：
public class FactoryTest {
 
 
	public static void main(String[] args) {
		Sender sender = SendFactory.produceMail();
		sender.Send();
	}
}