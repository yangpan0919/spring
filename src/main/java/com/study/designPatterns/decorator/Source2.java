package com.study.designPatterns.decorator;

//Source被装饰类：
public class Source2 implements Sourceable {
 
 
	@Override
	public void method() {
		System.out.println("the original method2!");
	}
}