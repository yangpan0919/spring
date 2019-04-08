package com.study.designPatterns.observer;

/**
 * 观察者模式
 */
public class ObserverTest {
 
 
	public static void main(String[] args) {
		Subject sub = new MySubject();
		sub.add(new Observer1());
		Observer observer = new Observer2();
		sub.add(observer);
		
		sub.operation();
		System.out.println("----------------------------------------------------");
		sub.del(observer);
		sub.operation();
	}
}