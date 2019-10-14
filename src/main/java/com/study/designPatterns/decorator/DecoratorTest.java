package com.study.designPatterns.decorator;

//测试类：
public class DecoratorTest {
 
 
	public static void main(String[] args) {
		Sourceable source = new Source();
		Sourceable obj = new Decorator(source);
		Sourceable obj1 = new Decorator(new Source2());
		obj.method();
		obj1.method();
	}
}