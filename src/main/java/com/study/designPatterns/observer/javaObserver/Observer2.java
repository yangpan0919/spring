package com.study.designPatterns.observer.javaObserver;


import java.util.Observable;
import java.util.Observer;

public class Observer2  implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
		System.out.println("observer2 has received!");
	}
}