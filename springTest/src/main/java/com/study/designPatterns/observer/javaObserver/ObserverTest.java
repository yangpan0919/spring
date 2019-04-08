package com.study.designPatterns.observer.javaObserver;

import java.util.Observer;
/**
 * 观察者模式   Java内置的观察者
 */
public class ObserverTest {
 
 
	public static void main(String[] args) {
		Observer observer1 = new Observer1();
		Observer observer2 = new Observer2();

		MySubject mySubject = new MySubject();
		mySubject.addObserver(observer1);
		mySubject.addObserver(observer2);
		mySubject.wantMethod();
		boolean b = mySubject.hasChanged();
		System.out.println(b);
//		sub.add(observer);
//
//		sub.operation();
//		System.out.println("----------------------------------------------------");
//		sub.del(observer);
//		sub.operation();
	}
}